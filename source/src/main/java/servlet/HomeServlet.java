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

import dao.CarsDAO;
import dao.ReserveDAO;
import dto.Cars;
import dto.Reserve;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}

	
		
		ReserveDAO rdao = new ReserveDAO();
		List<Reserve> reserveList = rdao.selectAll();

		LocalDateTime now = LocalDateTime.now();

		String notice = "";

		for (Reserve reserve : reserveList) {
		    if (reserve.getSdate().isBefore(now)
		            && reserve.getFdate().isAfter(now)) {

		        notice += reserve.getCarname()
		                + " の予約開始時間を過ぎています<br>";
		    }
		}
		
		//追加0617
		CarsDAO dao = new CarsDAO();
		List<Cars> homeList = dao.findhome();
		request.setAttribute("homeList", homeList);


		
		request.setAttribute("notice", notice);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

	}
}
