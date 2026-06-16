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
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
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

		int carid = Integer.parseInt(request.getParameter("carid"));

		String outsidephoto = request.getParameter("outsidephoto");
		String outsidememo = request.getParameter("outsidememo");
		boolean smell = request.getParameter("smell") != null;
		String insideitemmemo = request.getParameter("insideitemmemo");
		boolean equipmentcheck = request.getParameter("equipmentcheck") != null;
		boolean lostitem = request.getParameter("lostitem") != null;
		String lostitemmemo = request.getParameter("lostitemmemo");
		String gasolineamount = request.getParameter("gasolineamount");
		String userid = (String) session.getAttribute("userid");

		// 登録処理を行う
		Todo todo = new Todo();
		todo.setCarid(carid);
		todo.setOutsidephoto(outsidephoto);
		todo.setOutsidememo(outsidememo);
		todo.setSmell(smell);
		todo.setInsideitemmemo(insideitemmemo);
		todo.setGasolineamount(gasolineamount);
		todo.setEquipmentcheck(equipmentcheck);
		todo.setLostitem(lostitem);
		todo.setLostitemmemo(lostitemmemo);
		todo.setUserid(userid);

		TodoDAO dao = new TodoDAO();
		boolean insertresult = dao.insert(todo);

		request.setAttribute("insertresult", insertresult);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}
}
