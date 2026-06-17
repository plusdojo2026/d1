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
import dto.Result;

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

		// JSP へフォワード
		RequestDispatcher dispatcher =
		    request.getRequestDispatcher("/WEB-INF/jsp/reserve.jsp");
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
		String select = request.getParameter("select");

		if (select == null || select.isEmpty()) {
		    select = "プリウス";  // 初期値
		}

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
	    
	    String noStr = request.getParameter("reservenumber");
	    int reservenumber = -1;
	    if (noStr != null && noStr.matches("\\d+")) {
	        reservenumber = Integer.parseInt(noStr);
	    }
		
		ReserveDAO dao = new ReserveDAO();
		int carid = dao.findCarid(select);

		
		if ("登録".equals(request.getParameter("regist"))) {
			if (dao.insert(new Reserve(userid,sdate,fdate,carid,purpose))) { // 登録成功
				request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "d1/ReserveServlet"));
			} else { // 登録失敗
				request.setAttribute("result", new Result("登録失敗！", "レコードを登録できませんでした。", "/d1/HomeServlet"));
			}
		} else if ("更新".equals(request.getParameter("submit"))) {

		    if (dao.update(new Reserve(reservenumber,sdate,fdate,purpose))){  
		        // 既存予約の場合のみ UPDATE
		        request.setAttribute("result", new Result("更新成功！", "レコードを更新しました。", "/d1/ReserveServlet"));
			} else { // 更新失敗
				request.setAttribute("result", new Result("更新失敗！", "レコードを更新できませんでした。", "/d1/HomeServlet"));
			}
		} else if("削除".equals(request.getParameter("submit"))){
			if (dao.delete(reservenumber)) { // 削除成功
				request.setAttribute("result", new Result("削除成功！", "レコードを削除しました。", "/d1/ReserveServlet"));
			} else { // 削除失敗
				request.setAttribute("result", new Result("削除失敗！", "レコードを削除できませんでした。", "/d1/HomeServlet"));
			}
		}
		
	
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}
}