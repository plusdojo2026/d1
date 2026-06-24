package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}

		ReserveDAO rdao = new ReserveDAO();
		List<Reserve> reserveList = rdao.selectAll();

		LocalDateTime now = LocalDateTime.now();
		LocalDate today = LocalDate.now();

		List<Reserve> useList = new ArrayList<>();
		List<Reserve> todayList = new ArrayList<>();
		StringBuilder notice = new StringBuilder();

		for (Reserve reserve : reserveList) {

			LocalDate reserveDate = reserve.getSdate().toLocalDate();

//			// 今日の予約
//			if (reserveDate.equals(today)) {
//				todayList.add(reserve);
//			}

			// 利用中
			if (reserve.getStatusid() == 2) {
				useList.add(reserve);
			}

			// 開始遅れ
			if (now.isAfter(reserve.getSdate()) && now.isBefore(reserve.getFdate()) && reserve.getStatusid() == 1) {

				notice.append("⚠ ").append(reserve.getCarname()).append(" は開始ボタンを押していません<br>");
			}
		}

		CarsDAO dao = new CarsDAO();
		List<Cars> homeList = dao.findhome();

		request.setAttribute("useList", useList);
		request.setAttribute("todayList", todayList);
		request.setAttribute("notice", notice.toString());
		request.setAttribute("homeList", homeList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}
