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
			String sql = "INSERT INTO Todo VALUES (0, 0, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getTodoid() != 0) {
				pStmt.setInt(1, card.getTodoid());
			} else {
				pStmt.setInt(1, card.getTodoid());
			}
			if (card.getCarid() != 0) {
				pStmt.setInt(2, card.getCarid());
			} else {
				pStmt.setInt(2, card.getCarid());
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
			if (card.isSmell() != true) {
				pStmt.setBoolean(5, card.isSmell());
			} else {
				pStmt.setBoolean(5, card.isSmell());
			}
			if (card.getInsideitemmemo() != null) {
				pStmt.setString(6, card.getInsideitemmemo());
			} else {
				pStmt.setString(6, "");
			}
			if (card.getGasolineamount() != null) {
				pStmt.setString(7, card.getGasolineamount());
			} else {
				pStmt.setString(7, "");
			}
			if (card.isLostitem() != true) {
				pStmt.setBoolean(8, card.isLostitem());
			} else {
				pStmt.setBoolean(8, card.isLostitem());
			}
			if (card.getCreateddate() != null) {
				pStmt.setObject(9, card.getCreateddate());
			} else {
				pStmt.setString(9, "");
			}
			if (card.getLostitemmemo() != null) {
				pStmt.setString(10, card.getLostitemmemo());
			} else {
				pStmt.setString(10, "");
			}
			if (card.getUserid() != 0) {
				pStmt.setInt(11, card.getUserid());
			} else {
				pStmt.setInt(11, card.getUserid());
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
	}	
}
