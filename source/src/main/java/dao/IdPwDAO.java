package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.IdPw;

public class IdPwDAO {
	//データベースの情報を格納するフィールド(他のDAOにもコピペで使用)
	private final String URL = "jdbc:mysql://localhost:3306/meisi?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
	private final String USER = "root";
	private final String PASS = "password";
	
	
	// 引数で指定されたidpwでログイン成功ならtrueを返す
	public boolean isLoginOK(IdPw idpw) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		//ログイン結果を格納する
		boolean loginResult = false;

		try {
			// SELECT文を準備する
			String sql = "SELECT count(*) FROM IdPw WHERE id=? AND pw=?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, idpw.getId());
			pStmt.setString(2, idpw.getPw());

			// SELECT文を実行し、結果表を取得する
			rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		} finally {
			closeAll(conn,rs,pStmt);
		}

		// 結果を返す
		return loginResult;
	}
	
	//以下二つのメソッドも他のDAOにコピペで利用
		//接続を行うメソッド
		private Connection getConnection() {
			Connection con = null;
			try {
				//JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				//データベースに接続する
				con = DriverManager.getConnection(URL, USER, PASS);
				
			//例外処理
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			return con;
		}
			
		//切断を行うメソッド
		private void closeAll(Connection con, ResultSet rs, PreparedStatement pStmt) {
			
			//Connection切断
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//ResultSet切断
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//PreparedStatement切断
			if(pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
