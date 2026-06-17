package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
					"root", "password");

			// SQL文を準備する
			String sql = """
			INSERT INTO Todo
					(todoid, carid, outsidephoto, outsidememo, smell,
					 insideitemmemo, gasolineamount, lostitem,
					 createddate, lostitemmemo, userid)
					VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
					""";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getTodoid());
			pStmt.setInt(2, card.getCarid());
			pStmt.setString(3, card.getOutsidephoto());
			pStmt.setString(4, card.getOutsidememo());
			pStmt.setBoolean(5, card.isSmell());
			pStmt.setString(6, card.getInsideitemmemo());
			pStmt.setString(7, card.getGasolineamount());
			pStmt.setBoolean(8, card.isLostitem());
			pStmt.setObject(9, card.getCreateddate());
			pStmt.setString(10, card.getLostitemmemo());
			//pStmt.setInt(11, card.getUserid());
			
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
