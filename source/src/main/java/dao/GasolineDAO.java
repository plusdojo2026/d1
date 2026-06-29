package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Gasoline;

public class GasolineDAO {

	// 登録
	public boolean insert(Gasoline gasoline) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?" + "characterEncoding=utf8&useSSL=false"
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "d1", "6xdXyRxWhU3jddz5");

			// SQL文を準備する
			String sql = "INSERT INTO gasoline " + "(userid, stationname, gasolineprice, createddate) "
					+ "VALUES (?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, gasoline.getUserid());
			pStmt.setString(2, gasoline.getStationname());
			pStmt.setInt(3, gasoline.getGasolineprice());
			pStmt.setTimestamp(4, java.sql.Timestamp.valueOf(gasoline.getCreateddate()));

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

		return result;
	}

	// 一覧取得
	public List<Gasoline> selectAll() {
		Connection conn = null;
		List<Gasoline> list = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?" + "characterEncoding=utf8&useSSL=false"
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "d1", "6xdXyRxWhU3jddz5");

			// SQL文を準備する
			String sql = "SELECT * FROM gasoline ORDER BY createddate DESC LIMIT 20;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = pStmt.executeQuery();

			// 結果を格納
			while (rs.next()) {
				list.add(new Gasoline(rs.getInt("gasolineid"), rs.getString("userid"), rs.getString("stationname"),
						rs.getInt("gasolineprice"), rs.getTimestamp("createddate").toLocalDateTime()));
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

		return list;
	}

	

	// 最安値取得
	public Gasoline getMinPrice() {
		Connection conn = null;
		Gasoline gasoline = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?" + "characterEncoding=utf8&useSSL=false"
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "d1", "6xdXyRxWhU3jddz5");

			// SQL文を準備する
			String sql = "SELECT * FROM gasoline " + "ORDER BY gasolineprice ASC LIMIT 1";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				gasoline = new Gasoline(rs.getInt("gasolineid"), rs.getString("userid"), rs.getString("stationname"),
						rs.getInt("gasolineprice"), rs.getTimestamp("createddate").toLocalDateTime());
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

		return gasoline;
	}

	public boolean existsStation(String stationname) {
		Connection conn = null;
		boolean exists = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?" + "characterEncoding=utf8&useSSL=false"
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "d1", "6xdXyRxWhU3jddz5");

			String sql = "SELECT COUNT(*) FROM gasoline WHERE stationname = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, stationname);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				exists = rs.getInt(1) > 0;
			}

		} catch (Exception e) {
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

		return exists;
	}

	public boolean update(Gasoline gasoline) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?" + "characterEncoding=utf8&useSSL=false"
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "d1", "6xdXyRxWhU3jddz5");

			String sql = "UPDATE gasoline " + "SET gasolineprice = ?, " + "userid = ?, " + "createddate = ? "
					+ "WHERE stationname = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, gasoline.getGasolineprice());
			pStmt.setString(2, gasoline.getUserid());
			pStmt.setTimestamp(3, java.sql.Timestamp.valueOf(gasoline.getCreateddate()));
			pStmt.setString(4, gasoline.getStationname());

			result = pStmt.executeUpdate() > 0;

		} catch (Exception e) {
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

		return result;
	}
}
