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
import dto.Todo;

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
			response.sendRedirect("/d1/LoginServlet");
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
		
		String createddateStr = request.getParameter("createddate");
		java.time.LocalDateTime createddate = null;
		
		String lostitemmemo = request.getParameter("lostitemmemo");
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		 if (createddateStr != null  && !createddateStr.isEmpty()) {
		        createddate = java.time.LocalDateTime.parse(createddateStr);
		    }

		// 登録処理を行う
		Todo todo = new Todo();
	    todo.setTodoid(todoid);
	    todo.setCarid(carid);
	    todo.setOutsidephoto(outsidephoto);
	    todo.setOutsidememo(outsidememo);
	    todo.isSmell(smell);
	    todo.setInsideitemmemo(insideitemmemo);
	    todo.setGasolineamount(gasolineamount);
	    todo.setLostitem(lostitem);
	    todo.setLostitemmemo(lostitemmemo);
	    todo.setUserid(userid);
	    todo.setCreateddate(createddate);
	 
		TodoDAO dao = new TodoDAO();
	    boolean result = dao.insert(Todo);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}
}
