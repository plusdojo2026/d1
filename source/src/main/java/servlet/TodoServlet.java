package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoDAO;
import dto.Todo;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
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
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/d1/LoginServlet");
			return;
		}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("//LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int todoid = Integer.parseInt(request.getParameter("todoid"));
		int carid = Integer.parseInt(request.getParameter("carid"));
		String outsidephoto = request.getParameter("outsidephoto");
		String outsidememo = request.getParameter("outsidememo");
		String smell = request.getParameter("smell");
		String insideitemmemo = request.getParameter("insideitemmemo");
		String gasolineamount = request.getParameter("gasolineamount");
		String lostitem = request.getParameter("lostitem");
		String createddate = request.getParameter("createddate");
		String lostitemmemo = request.getParameter("lostitemmemo");
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		// 登録処理を行う
		TodoDAO tDao = new TodoDAO();
		public boolean insert(Todo card) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");

				// SQL文を準備する
				String sql = "INSERT INTO Bc VALUES (?, 0, 0, ?, ?, ?, ?, ?, ?, ?, 0)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getTodoid() != null) {
					pStmt.setString(1, card.getTodoid());
				} else {
					pStmt.setString(1, "");
				}
				if (card.getCardid() != null) {
					pStmt.setString(2, card.getCardid());
				} else {
					pStmt.setString(2, "");
				}
				if (card.getOutsidephoto() != null) {
					pStmt.setString(3, card.getOutsidephoto());
				} else {
					pStmt.setString(3, "");
				}
				if (card.getOutsidememo() != null) {
					pStmt.setString(4, card.getOutsidememo());
				} else {
					pStmt.setString(4, "");
				}
				if (card.getInsideitemmemo() != null) {
					pStmt.setString(5, card.getInsideitemmemo());
				} else {
					pStmt.setString(5, "");
				}
				if (card.getGasolineamount() != null) {
					pStmt.setString(6, card.getGasolineamount());
				} else {
					pStmt.setString(6, "");
				}
				if (card.getLostitem() != null) {
					pStmt.setString(7, card.getLostitem());
				} else {
					pStmt.setString(7, "");
				}
				if (card.getCreateddate() != null) {
					pStmt.setString(8, card.getCreateddate());
				} else {
					pStmt.setString(8, "");
				}
				if (card.getLostitemmemo() != null) {
					pStmt.setString(9, card.getLostitemmemo());
				} else {
					pStmt.setString(9, "");
				}
				if (card.getUserid() != null) {
					pStmt.setString(10, card.getUserid());
				} else {
					pStmt.setString(10, "");
				}

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			// 結果を返す
			return result;
		
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}
}
