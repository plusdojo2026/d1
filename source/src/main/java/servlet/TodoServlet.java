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
import javax.servlet.http.HttpSession;
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
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("LoginServlet");
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

		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		String userid = (String) session.getAttribute("userid");
		request.setCharacterEncoding("UTF-8");

		
		Part photo = request.getPart("outsidephoto");
		String fileName = (photo != null) ? photo.getSubmittedFileName() : null;

		String outsidememo = request.getParameter("outsidememo");
		String smellStr = request.getParameter("smell");
		String insideitemmemo = request.getParameter("insideitemmemo");
		String gasolineamount = request.getParameter("gasolineamount");
		String lostitemStr = request.getParameter("lostitem");
		String lostitemmemo = request.getParameter("lostitemmemo");
		String select = request.getParameter("select");

		//入力チェック
		if (fileName == null || fileName.isEmpty() || smellStr == null || lostitemStr == null || gasolineamount == null
				|| gasolineamount.isEmpty()) {

			request.setAttribute("error", "必須項目が入力されていません");

			request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp").forward(request, response);
			return;
		}

		//変換 
		boolean smell = "1".equals(smellStr);
		boolean lostitem = true; // チェックされている前提

		//carid取得
		ReserveDAO dao = new ReserveDAO();
		int carid = dao.findCarid(select);

		//ファイル保存
		String uploadPath = "C:/pleiades/workspace/d1/src/main/webapp/img/";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		if (photo != null) {
			photo.write(uploadPath + fileName);
		}

		//DTO
		Todo todo = new Todo();
		todo.setCarid(carid);
		todo.setOutsidephoto(fileName);
		todo.setOutsidememo(outsidememo);
		todo.setSmell(smell);
		todo.setInsideitemmemo(insideitemmemo);
		todo.setGasolineamount(gasolineamount);
		todo.setLostitem(lostitem);
		todo.setLostitemmemo(lostitemmemo);
		todo.setUserid(userid);

		//DAO
		TodoDAO dao1 = new TodoDAO();
		boolean result = dao1.insert(todo);

		System.out.println("insert result=" + result);
		System.out.println("carid=" + carid);
		System.out.println("select=" + select);
		System.out.println("userid=" + userid);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}
}
