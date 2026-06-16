package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Todo;

public class TodoDAO {
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Todo todo) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO Todo(" + "userid, carid, outsidephoto, outsidememo, smell, "
					+ "insideitemmemo, gasolineamount, lostitem, lostitemmemo, equipmentcheck"
					+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, todo.getUserid());
			pStmt.setInt(2, todo.getCarid());
			pStmt.setString(3, todo.getOutsidephoto());
			pStmt.setString(4, todo.getOutsidememo());
			pStmt.setBoolean(5, todo.isSmell());
			pStmt.setString(6, todo.getInsideitemmemo());
			pStmt.setString(7, todo.getGasolineamount());
			pStmt.setBoolean(8, todo.isLostitem());
			pStmt.setString(9, todo.getLostitemmemo());
			pStmt.setBoolean(10, todo.isEquipmentcheck());
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
}
