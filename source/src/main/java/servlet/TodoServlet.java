package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ReserveDAO;
import dao.TodoDAO;
import dto.Reserve;
import dto.Todo;

/**
 * Servlet implementation class TodoServlet
 */

@WebServlet("/TodoServlet")
@MultipartConfig
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("LoginServlet");
//			return;
//		}
		String select = request.getParameter("select");//車種別からの遷移時にcarnameもらう


		ReserveDAO dao = new ReserveDAO();
		
		List<Reserve> carlist = dao.carList();
		request.setAttribute("carlist", carlist);

		// select が null または空ならデフォルト値を設定
		if (select == null || select.isEmpty()) {
		    select = "プリウス";
		}

		// 選択された車種で検索
		List<Reserve> list = dao.Select(select);

		request.setAttribute("list", list);
		request.setAttribute("select", select);

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("LoginServlet");
//			return;
//		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		//int todoid = Integer.parseInt(request.getParameter("todoid"));
		//int carid = Integer.parseInt(request.getParameter("carid"));
		
		String outsidephoto = request.getParameter("outsidephoto");
		Part photo = request.getPart("outsidephoto");
        String fileName = photo.getSubmittedFileName();
        String uploadPath =
        	    getServletContext().getRealPath("/upload");
        	File uploadDir = new File(uploadPath);
        	if (!uploadDir.exists()) {
        	    uploadDir.mkdirs();
        	}
        	photo.write(uploadPath + File.separator + fileName);
		
		String outsidememo = request.getParameter("outsidememo");
		boolean smell = request.getParameter("smell") != null;
		String insideitemmemo = request.getParameter("insideitemmemo");
		String gasolineamount = request.getParameter("gasolineamount");
		boolean lostitem = request.getParameter("lostitem") != null;
		String select = request.getParameter("select");

		String createddateStr = request.getParameter("createddate");
		java.time.LocalDateTime createddate = null;
		
		String lostitemmemo = request.getParameter("lostitemmemo");
		//String userid = Integer.parseInt(request.getParameter("userid"));
		String userid = "user01";
		 if (createddateStr != null  && !createddateStr.isEmpty()) {
		        createddate = java.time.LocalDateTime.parse(createddateStr);
		    }
		 ReserveDAO dao = new ReserveDAO();
			int carid = dao.findCarid(select);
			
		// 登録処理を行う
		Todo todo = new Todo();
	    //todo.setTodoid(todoid);
	    todo.setCarid(carid);
	    todo.setOutsidephoto(fileName);
	    todo.setOutsidememo(outsidememo);
	    todo.setSmell(smell);
	    todo.setInsideitemmemo(insideitemmemo);
	    todo.setGasolineamount(gasolineamount);
	    todo.setLostitem(lostitem);
	    todo.setLostitemmemo(lostitemmemo);
	    todo.setUserid(userid);
	    //todo.setCreateddate(createddate);
	 
		TodoDAO dao1 = new TodoDAO();
	    dao1.insert(todo);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}
}
