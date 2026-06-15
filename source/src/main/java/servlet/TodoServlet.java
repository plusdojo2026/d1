package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoDAO;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("//LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int todoid = Integer.parseInt(request.getParameter("todoid"));
		int carid = Integer.parseInt(request.getParameter("carid"));
		String outsidephoto = request.getParameter("outsidephoto");
		String outsidememo = request.getParameter("outsidememo");
		String smell = request.getParameter("smell");
		String insideitemmemo = request.getParameter("insideitemmemo");
		String gasolineamount = request.getParameter("gasolineamount");
		String lostitem = request.getParameter("lostitem");
		String createddate = request.getParameter("createddate");
		String lostitemmemo = request.getParameter("lostitemmemo");
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		// 登録処理を行う
		  if (request.getParameter("createddate") != null) {
		        TodoDAO.setCreateddate(java.time.LocalDateTime.parse(request.getParameter("createddate")));
		  }
		TodoDAO dao = new TodoDAO();
		boolean result = dao.insert(Todo);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}
}
