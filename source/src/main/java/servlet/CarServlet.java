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
import dao.ReserveDAO;
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

//		int carid = Integer.parseInt(request.getParameter("carid"));
		//追加0617
		
		String select = request.getParameter("select");
		// select が null または空ならデフォルト値を設定
		if (select == null || select.isEmpty()) {
		    select = "プリウス";
		}
		ReserveDAO dao1 = new ReserveDAO();

		List<Reserve> carlist = dao1.carList();
		request.setAttribute("carlist", carlist);

		int carid = dao1.findCarid(select);
		
		request.setAttribute("select", select);
		
		CarsDAO dao = new CarsDAO();
		String carCondition = dao.findCondition(carid);

		// JSPに渡す
		request.setAttribute("carCondition", carCondition);
		
		//0618 最新のtodoリストを取り出す
		List<Todo> todoList = dao.findTodoList(carid);
		List<Todo> top1 = todoList.size() > 0 
				? todoList.subList(0,1)
				: todoList;
		// JSPに渡す
		request.setAttribute("top1",top1 );

		List<Reserve> history = dao.findHistory(carid);
		// JSPに渡す
		request.setAttribute("history", history);

		String carImage = dao.findCarimage(carid);
		// JSPに渡す
		request.setAttribute("carImage", carImage);
		
		List<Cars> carList = dao.findAllCars();
		// JSPに渡す
		request.setAttribute("carList", carList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Cars.jsp");
		dispatcher.forward(request, response);

	}
}