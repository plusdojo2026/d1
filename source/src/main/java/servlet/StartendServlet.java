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

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}
		
		String userid = (String) session.getAttribute("userid");

		TodoDAO todoDao = new TodoDAO();
		boolean hasTodo = todoDao.hasTodo(userid);
		request.setAttribute("hasTodo", hasTodo);

		ReserveDAO reserveDao = new ReserveDAO();
		
		
		List<Reserve> starts = reserveDao.reserveAllk(userid);
		request.setAttribute("starts", starts);
		
		List<Reserve> top1 = reserveDao.reserveAlls(userid);
		request.setAttribute("top1", top1);
		
		List<Reserve> reservelist = reserveDao.reserveAll(userid);
		request.setAttribute("reservelist", reservelist);
		
		
		// 追加
		boolean hasReserve = !reservelist.isEmpty();
		request.setAttribute("hasReserve", hasReserve);
		boolean canStart = false;
		boolean canEnd = false;
		boolean showQR = false;
		LocalDateTime now = LocalDateTime.now();
		if (!reservelist.isEmpty()) {

			Reserve reserve = reservelist.get(0);

			if (reserve.getStatusid()==1 && !now.isBefore(reserve.getSdate())
					&& now.isBefore(reserve.getFdate())) {
				canStart = true;
			}

			if (reserve.getStatusid()==2 && hasTodo) {
				canEnd = true;
			}
			if (reserve.getStatusid()==3 ){
		        showQR = true;
		    }
		}
		request.setAttribute("showQR", showQR);
		request.setAttribute("canStart", canStart);
		request.setAttribute("canEnd", canEnd);
		
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
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String userid = (String) session.getAttribute("userid");

		int reservenumber = Integer.parseInt(request.getParameter("reservenumber"));

		ReserveDAO dao = new ReserveDAO();


		if ("start".equals(action)) {

			dao.updateStatus(reservenumber);
			List<Reserve> top1 = dao.reserveAlls(userid);
			request.setAttribute("top1", top1);
			List<Reserve> reservelist = dao.reserveAll(userid);
			request.setAttribute("reservelist", reservelist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/startend.jsp");
			dispatcher.forward(request, response);

		} else if ("end".equals(action)) {

			dao.updateStatus1(reservenumber);
		}

		response.sendRedirect(request.getContextPath() + "/StartendServlet");
	}

}