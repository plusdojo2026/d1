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

import dao.ReserveDAO;
import dto.Reserve;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("userid") == null) {
//			response.sendRedirect("/d1/LoginServlet");
//			return;
//		}
//		
//		String userid = (String) request.getSession().getAttribute("userid");
		String select = request.getParameter("select");//車種別からの遷移時にcarnameもらう
//		int carid = Integer.parseInt(request.getParameter("carid"));
		int carid = 1;

		
		ReserveDAO dao = new ReserveDAO();
		List<Reserve> list;
		
	    if("プリウス".equals(select)) {
	    	list = dao.Select(select);
		    request.setAttribute("list", list);
	    }
	    
	    else if("アクア".equals(select)) {	  
	    	list = dao.Select(select);
		    request.setAttribute("list", list);
	    }
	    
	    else if("ヤリス".equals(select)) {	    
	    	list = dao.Select(select);
		    request.setAttribute("list", list);
	    }
	    else {
	    	// sort が null のときのデフォルト
	    	list = dao.Select(select);
		    request.setAttribute("list", list);
	    }
	    
		
		// ReserveServletにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reserve.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
//		String userid = (String) request.getSession().getAttribute("userid");
		String userid = "user01";
		String purpose = request.getParameter("purpose");
		LocalDateTime sdate = null;
		String sdateStr = request.getParameter("sdate");
		if (sdateStr != null && !sdateStr.isEmpty()) {
		    sdate = LocalDateTime.parse(sdateStr);
		}
		LocalDateTime fdate = null;
		String fdateStr = request.getParameter("fdate");
		if (fdateStr != null && !fdateStr.isEmpty()) {
		    fdate = LocalDateTime.parse(fdateStr);
		}
		int carid = 1;
		
		ReserveDAO dao = new ReserveDAO();

		Reserve dao1 = new Reserve(userid,sdate,fdate,carid,purpose);
		int reservenumber = dao.insert(dao1);

//		if (reservenumber == -1) {
//		    request.setAttribute("result", new Result("登録失敗！", "会社情報の登録に失敗しました。", "/d1/ReserveServlet"));
//		    return;
//		}
		
		


		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/d1/ReserveServlet");
		dispatcher.forward(request, response);
	}
}