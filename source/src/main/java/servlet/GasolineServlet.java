package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GasolineDAO;
import dto.Gasoline;

@WebServlet("/GasolineServlet")
public class GasolineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}
		GasolineDAO dao = new GasolineDAO();

		List<Gasoline> gasolineList = dao.selectAll();
		int avgPrice = 168;

		for (Gasoline gasoline : gasolineList) {
			int diff = gasoline.getGasolineprice() - avgPrice;

			if (diff > 0) {
				gasoline.setResultMessage("+" + diff);
			} else if (diff < 0) {
				gasoline.setResultMessage(String.valueOf(diff));
			} else {
				gasoline.setResultMessage("±0");
			}
		}
		Gasoline minGasoline = dao.getMinPrice();
		request.setAttribute("avgPrice", avgPrice);
		request.setAttribute("gasolineList", gasolineList);

		request.setAttribute("minGasoline", minGasoline);
		System.out.println("件数=" + gasolineList.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gasoline.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost実行");
		request.setCharacterEncoding("UTF-8");

		String stationname = request.getParameter("stationname");
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		System.out.println("userid=" + userid);
		if (userid == null) {
			System.out.println("ログインしていない（userid null）");
			response.sendRedirect("login.jsp");
			return;
		}
		String priceStr = request.getParameter("gasolineprice");

		int gasolineprice;

		gasolineprice = Integer.parseInt(priceStr);

		int avgPrice = 168;

		int diff;
		String resultMessage;

		if (gasolineprice < avgPrice) {

			diff = avgPrice - gasolineprice;

			resultMessage = "-" + diff;

		} else if (gasolineprice > avgPrice) {

			diff = gasolineprice - avgPrice;

			resultMessage = "+" + diff;

		} else {

			diff = 0;

			resultMessage = "±0";
		}

		Gasoline gasoline = new Gasoline(0, userid, stationname, gasolineprice, LocalDateTime.now());

		GasolineDAO dao = new GasolineDAO();

		System.out.println("登録開始");
		boolean result;

		if (dao.existsStation(stationname)) {
			result = dao.update(gasoline);
		} else {
			result = dao.insert(gasoline);
		}
		System.out.println("登録終了");
		System.out.println("登録結果=" + result);
//		List<Gasoline> gasolineList = dao.selectAll();
//		for (Gasoline gasoline1 : gasolineList) {
//			int diff1 = gasoline1.getGasolineprice() - avgPrice;
//
//			if (diff1 > 0) {
//				gasoline1.setResultMessage("+" + diff1);
//			} else if (diff1 < 0) {
//				gasoline1.setResultMessage(String.valueOf(diff1));
//			} else {
//				gasoline1.setResultMessage("±0");
//			}
//		}
//
//		Gasoline minGasoline = dao.getMinPrice();
//
//		request.setAttribute("stationname", stationname);
//
//		request.setAttribute("avgPrice", avgPrice);
//
//		request.setAttribute("diff", diff);
//
//		request.setAttribute("resultMessage", resultMessage);
//
//		request.setAttribute("gasolineList", gasolineList);
//
//		request.setAttribute("minGasoline", minGasoline);

		response.sendRedirect(request.getContextPath() + "/GasolineServlet");
		return;
	}
}