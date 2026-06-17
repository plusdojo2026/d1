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
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "root", "password");

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
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM gasoline ORDER BY createddate DESC";
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
					+ "&serverTimezone=GMT%2B9" + "&rewriteBatchedStatements=true", "root", "password");

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
}