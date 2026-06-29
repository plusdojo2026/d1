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
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}

		String select = request.getParameter("select");// 車種別からの遷移時にcarnameもらう

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
		String userid = (String) request.getSession().getAttribute("userid");
		String select = request.getParameter("select");

		if (select == null || select.isEmpty()) {
			select = "プリウス"; // 初期値
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
		// ここから追加
		if ("登録".equals(request.getParameter("regist")) || "更新".equals(request.getParameter("submit"))) {

			LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);

			// 開始日時が現在より前
			if (sdate == null || sdate.isBefore(now)) {
				request.setAttribute("result", new Result("登録失敗！", "開始日時は現在日時より後を指定してください。", "/d1/ReserveServlet"));
				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}

			// 終了日時が開始日時以前
			if (fdate == null || !fdate.isAfter(sdate)) {
				request.setAttribute("result", new Result("登録失敗！", "終了日時は開始日時より後を指定してください。", "/d1/ReserveServlet"));
				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}
		}
		// ここまで追加
//		// 同じ車両の重複予約チェック
//		if ("登録".equals(request.getParameter("regist"))) {
//
//			if (dao.existsCarReserve(carid, sdate, fdate)) {
//
//				request.setAttribute("result", new Result("登録失敗！", "その車両は既に予約されています。", "/d1/ReserveServlet"));
//
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
//
//				dispatcher.forward(request, response);
//				return;
//			}
//		}

		if ("登録".equals(request.getParameter("regist"))) {

			// 同じ車の重複チェック
			if (dao.existsCarReserve(carid, sdate, fdate)) {

				request.setAttribute("result", new Result("登録失敗！", "その車両は既に予約されています。", "/d1/ReserveServlet"));

				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}

			// 同じユーザーの時間重複チェック
			if (dao.existsUserReserve(userid, sdate, fdate)) {

				request.setAttribute("result", new Result("登録失敗！", "同じ時間帯に既に予約があります。", "/d1/ReserveServlet"));

				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}

			// 登録処理
			if (dao.insert(new Reserve(userid, sdate, fdate, carid, purpose))) {

				request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/d1/ReserveServlet"));

			} else {

				request.setAttribute("result", new Result("登録失敗！", "レコードを登録できませんでした。", "/d1/ReserveServlet"));
			}

		} else if ("更新".equals(request.getParameter("submit"))) {
			// 同じ車の重複チェック
			if (dao.existsCarReserve1(carid, sdate, fdate, reservenumber)) {
				request.setAttribute("result", new Result("更新失敗！", "その車両は既に予約されています。", "/d1/ReserveServlet"));
				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}
			// 同じユーザーの時間重複チェック
			if (dao.existsUserReserve1(userid, sdate, fdate, reservenumber)) {
				request.setAttribute("result", new Result("更新失敗！", "同じ時間帯に既に予約があります。", "/d1/ReserveServlet"));
				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}

			if (dao.update(new Reserve(reservenumber, sdate, fdate, purpose))) {

				request.setAttribute("result", new Result("更新成功！", "レコードを更新しました。", "/d1/ReserveServlet"));

			} else {

				request.setAttribute("result", new Result("更新失敗！", "レコードを更新できませんでした。", "/d1/ReserveServlet"));
			}

		} else if ("削除".equals(request.getParameter("submit"))) {
			// statuscheck
			if (dao.existsCarReserve2(reservenumber)) {

				request.setAttribute("result", new Result("削除失敗！", "その予約は予約開始中または予約終了後です。", "/d1/ReserveServlet"));

				request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
				return;
			}
			if (dao.delete(reservenumber)) {

				request.setAttribute("result", new Result("削除成功！", "レコードを削除しました。", "/d1/ReserveServlet"));

			} else {

				request.setAttribute("result", new Result("削除失敗！", "レコードを削除できませんでした。", "/d1/ReserveServlet"));
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}
}
