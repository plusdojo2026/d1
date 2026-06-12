package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BcDAO;
import dto.Bc;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		int number = 0;

		try {
		    String numberStr = request.getParameter("number");
		    if (numberStr != null && !numberStr.isEmpty()) {
		        number = Integer.parseInt(numberStr);
		    }
		} catch (NumberFormatException e) {
		    number = 0;
		}
		//検索条件取得
		String company = request.getParameter("company");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		//並び替え条件取得
		String sort = request.getParameter("sort");
		//日付取得
		Date exchangeDate = null;
		//JSPから日付取得
		String exchangeDateStr = request.getParameter("exchangedate");
		//空じゃない場合のみDate型に変換
		if (exchangeDateStr != null && !exchangeDateStr.isEmpty()) {
			exchangeDate = Date.valueOf(exchangeDateStr);
		}
		//お気に入り取得　nullじゃなければtrue
		boolean favorite = request.getParameter("favorite") != null;
		// DTO作成
		Bc bc = new Bc();
		// 検索条件をDTOにセット
		bc.setNumber(number);
		bc.setCompany(company);
		bc.setName(name);
		bc.setEmail(email);
		bc.setExchangedate(exchangeDate);
		bc.setFavorite(favorite);
		bc.setSort(sort);
		// 検索処理を行う
		//DAO作成
		BcDAO bDao = new BcDAO();
		//検索実行
		List<Bc> cardList = bDao.select(bc);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);
		//並び順も保存
		request.setAttribute("sort", sort);
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);

	}
}
