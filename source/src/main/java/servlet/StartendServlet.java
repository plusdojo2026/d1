package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReserveDAO;
import dao.TodoDAO;
import dto.Reserve;

/**
 * Servlet implementation class StartendServlet
 */
@WebServlet("/StartendServlet")
public class StartendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartendServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("carid=" + request.getParameter("carid"));
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}
		
		int carid = Integer.parseInt(request.getParameter("carid"));

		String userid = (String) request.getSession().getAttribute("userid");

		ReserveDAO dao = new ReserveDAO();
		TodoDAO dao1 = new TodoDAO();
		boolean hasTodo = dao1.hasTodo(carid, userid);

		request.setAttribute("hasTodo", hasTodo);
		request.setAttribute("carid", carid);
		List<Reserve> reservelist = dao.reserveAll(userid);
		request.setAttribute("reservelist", reservelist);

		List<Reserve> reservelists = dao.reserveAlls(userid, carid);
		List<Reserve> top1 = reservelists.size() > 0 ? reservelists.subList(0, 1) : reservelists;
		request.setAttribute("top1", top1);
		System.out.println("carid=" + carid);
		System.out.println("userid=" + userid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/startend.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
