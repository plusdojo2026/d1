package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReserveDAO;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String userid = (String) request.getSession().getAttribute("userid");
		String userid = "user01";
		
		ReserveDAO dao = new ReserveDAO();
		
		List<Reserve> reservelist = dao.reserveAll(userid);
		request.setAttribute("reservelist",reservelist );
		
		RequestDispatcher dispatcher =
			    request.getRequestDispatcher("/WEB-INF/jsp/startend.jsp");
			dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
