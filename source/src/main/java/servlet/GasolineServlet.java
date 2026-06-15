package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GasolineDAO;
import dto.CompareHistory;

@WebServlet("/GasolineServlet")
public class GasolineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 初期表示
	@Override
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		GasolineDAO dao = new GasolineDAO();

		List<CompareHistory> historyList =
				dao.selectHistory();

		CompareHistory minHistory =
				dao.getMinHistory();

		request.setAttribute(
				"historyList",
				historyList);

		request.setAttribute(
				"minHistory",
				minHistory);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"/WEB-INF/jsp/gasoline.jsp");

		dispatcher.forward(
				request,
				response);
	}

	// 比較処理
	@Override
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String stationname =
				request.getParameter("stationname");

		String priceStr =
				request.getParameter("gasolineprice");

		// スタンド名チェック
		if (stationname == null ||
			stationname.trim().isEmpty()) {

			request.setAttribute(
					"error",
					"スタンド名を入力してください");

			doGet(request, response);
			return;
		}

		// 価格未入力チェック
		if (priceStr == null ||
			priceStr.trim().isEmpty()) {

			request.setAttribute(
					"error",
					"価格を入力してください");

			doGet(request, response);
			return;
		}

		int inputprice;

		try {

			inputprice =
				Integer.parseInt(priceStr);

		} catch (NumberFormatException e) {

			request.setAttribute(
					"error",
					"価格は数値で入力してください");

			doGet(request, response);
			return;
		}

		// 仮の平均価格
		int averageprice = 168;

		String resultMessage;
		int diff;

		if (inputprice < averageprice) {

			diff = averageprice - inputprice;

			resultMessage =
					"+" + diff + "円安い";

		} else if (inputprice > averageprice) {

			diff = inputprice - averageprice;

			resultMessage =
					"-" + diff + "円高い";

		} else {

			diff = 0;

			resultMessage =
					"同じ価格です";
		}

		GasolineDAO dao =
				new GasolineDAO();

		// DB登録
		dao.insertCompare(
				stationname,
				inputprice,
				averageprice,
				resultMessage);

		// 最新履歴取得
		List<CompareHistory> historyList =
				dao.selectHistory();

		// 最安値取得
		CompareHistory minHistory =
				dao.getMinHistory();

		request.setAttribute(
				"stationname",
				stationname);

		request.setAttribute(
				"minPrice",
				minHistory != null
						? minHistory.getInputprice()
						: 0);

		request.setAttribute(
				"avgPrice",
				averageprice);

		request.setAttribute(
				"diff",
				diff);

		request.setAttribute(
				"resultMessage",
				resultMessage);

		request.setAttribute(
				"historyList",
				historyList);

		request.setAttribute(
				"minHistory",
				minHistory);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(
						"/WEB-INF/jsp/gasoline.jsp");

		dispatcher.forward(
				request,
				response);
	}
}