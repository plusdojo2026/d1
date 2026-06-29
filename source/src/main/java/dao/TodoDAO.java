package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Todo;

public class TodoDAO {
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Todo card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"d1", "6xdXyRxWhU3jddz5");

			// SQL文を準備する
			String sql = """
					INSERT INTO todo
							(carid, outsidephoto, outsidememo, smell,
							 insideitemmemo, gasolineamount, lostitem,
							 lostitemmemo, equipmentcheck, userid)
							VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
					""";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getCarid());
			pStmt.setString(2, card.getOutsidephoto());
			pStmt.setString(3, card.getOutsidememo());
			pStmt.setBoolean(4, card.isSmell());
			pStmt.setString(5, card.getInsideitemmemo());
			pStmt.setString(6, card.getGasolineamount());
			pStmt.setBoolean(7, card.isLostitem());
			pStmt.setString(8, card.getLostitemmemo());

			pStmt.setBoolean(9, card.isEquipmentcheck());

			pStmt.setString(10, card.getUserid());

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
	}

	public boolean hasTodo(String userid) {
		Connection conn = null;
		boolean exists = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/d1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "d1",
					"6xdXyRxWhU3jddz5");

			String sql = "SELECT COUNT(*) FROM todo inner join reserve on todo.carid=reserve.carid WHERE todo.userid = ? "
					+ "AND reserve.statusid=2 AND reserve.sdate < todo.createddate AND todo.createddate<reserve.fdate";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, userid);

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
				} catch (Exception e) {
				}
			}
		}

		return exists;
	}
}
