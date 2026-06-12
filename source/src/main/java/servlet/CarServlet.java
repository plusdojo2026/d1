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

import dao.CarsDAO;
import dto.Cars;
import dto.Reserve;
import dto.Todo;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/webapp/LoginServlet");
			return;
		}

		int carid = Integer.parseInt(request.getParameter("carid"));
		CarsDAO dao = new CarsDAO();
		String carCondition = dao.findCondition(carid);

		// JSPに渡す
		request.setAttribute("carCondition", carCondition);
		List<Todo> todoList = dao.findTodoList(carid);
		// JSPに渡す
		request.setAttribute("todoList", todoList);

		List<Reserve> history = dao.findHistory(carid);
		// JSPに渡す
		request.setAttribute("history", history);

		String carImage = dao.findCarimage(carid);
		// JSPに渡す
		request.setAttribute("carImage", carImage);
		
		List<Cars> carList = dao.findAllCars();
		// JSPに渡す
		request.setAttribute("carList", carList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/car.jsp");
		dispatcher.forward(request, response);

	}
}
