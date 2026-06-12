package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Cars;
import dto.Reserve;
import dto.Todo;

public class CarsDAO {
	public String findCondition(int carid) {
		Connection conn = null;
		String status = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL文を準備する
			String sql = "SELECT carstatus FROM Cars WHERE carid = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, carid);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				status = rs.getString("carstatus");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return status;
	}

	public String findCarimage(int carid) {
		Connection conn = null;
		String image = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL文を準備する
			String sql = "SELECT carimage FROM Cars WHERE carid = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, carid);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				image = rs.getString("carimage");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return image;
	}

	public List<Cars> findAllCars() {
		Connection conn = null;
		List<Cars> carList = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d1?" + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"root", "password");

			String sql = "SELECT carid, carname FROM Cars";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Cars car = new Cars();

				car.setCarid(rs.getInt("carid"));
				car.setCarname(rs.getString("carname"));

				carList.add(car);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return carList;
	}

	public List<Todo> findTodoList(int carid) {
		Connection conn = null;
		List<Todo> todoList = new ArrayList<>();
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL文を準備する
			String sql = "SELECT outsidememo,smell,insideitemmemo,gasolineamount,lostitemmemo FROM Todo WHERE carid = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, carid);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Todo todo = new Todo();

				todo.setOutsidememo(rs.getString("outsidememo"));
				todo.setSmell(rs.getBoolean("smell"));
				todo.setInsideitemmemo(rs.getString("insideitemmemo"));
				todo.setGasolineamount(rs.getString("gasolineamount"));
				todo.setLostitemmemo(rs.getString("lostitemmemo"));

				todoList.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return todoList;
	}

	public List<Reserve> findHistory(int carid) {
		Connection conn = null;
		List<Reserve> historyList = new ArrayList<>();
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL文を準備する
			String sql = "SELECT Reserve.sdate,Reserve.fdate,Idpw.username FROM Reserve "
					+ "INNER JOIN Idpw ON Reserve.userid = Idpw.userid WHERE Reserve.carid = ? ORDER BY Reserve.sdate DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, carid);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Reserve reserve = new Reserve();

				reserve.setSdate(rs.getTimestamp("sdate").toLocalDateTime());

				reserve.setFdate(rs.getTimestamp("fdate").toLocalDateTime());

				reserve.setUsername(rs.getString("username"));

				historyList.add(reserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return historyList;
	}

}
