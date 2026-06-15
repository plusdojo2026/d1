	package dao;

	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.Reserve;
	
	
	public class ReserveDAO {
		//データベースの情報を格納するフィールド(他のDAOにもコピペで使用)
		private final String URL = "jdbc:mysql://localhost:3306/d1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
		private final String USER = "root";
		private final String PASS = "password";
		
		public List<Reserve> Select(int carid) {
			Connection conn = getConnection();
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			List<Reserve> cardList = new ArrayList<>();
			
			try {
				// SQL文を準備する
//				String sql = "SELECT * FROM reserve INNER JOIN IdPw ON reserve.userid = IdPw.userid WHERE carid LIKE ? AND Companies.company LIKE ? ORDER BY Bc_number";
				
				String sql = "SELECT Idpw.username,Reserve.carid,reservenumber , sdate, fdate,purpose "
				        + "FROM Reserve INNER JOIN Idpw ON Reserve.userid = Idpw.userid "
				        + "WHERE carid LIKE ? "
				        + "AND fdate >= DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m-01')\r\n "
				        + "AND fdate <  DATE_FORMAT(CURRENT_DATE + INTERVAL 2 MONTH, '%Y-%m-01') " 
				        + "ORDER BY Reserve.reservecreated desc";
				pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, carid);

				// SQL文を実行し、結果表を取得する
				rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {

				    java.sql.Timestamp sqlsDate = rs.getTimestamp("sdate");
				    java.sql.Timestamp sqlfDate = rs.getTimestamp("fdate");

				    LocalDateTime sdate = (sqlsDate != null) ? sqlsDate.toLocalDateTime() : null;
				    LocalDateTime fdate = (sqlfDate != null) ? sqlfDate.toLocalDateTime() : null;

					 Reserve bc = new Reserve(
							rs.getInt("reservenumber"),
							sdate,
							fdate,
						    rs.getString("purpose"),
					        rs.getString("username")
					    );
					cardList.add(bc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			} finally {
				closeAll(conn,rs,pStmt);
			}
			// 結果を返す
			return cardList;	
		}
		
		public List<Reserve> Select(String carname) {
			Connection conn = getConnection();
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			List<Reserve> cardList = new ArrayList<>();
			
			try {
				// SQL文を準備する
//				String sql = "SELECT * FROM reserve INNER JOIN IdPw ON reserve.userid = IdPw.userid WHERE carid LIKE ? AND Companies.company LIKE ? ORDER BY Bc_number";
				
				String sql = "SELECT Idpw.username,Reserve.carid,reservenumber , sdate, fdate,purpose "
				        + "FROM Reserve INNER JOIN Idpw ON Reserve.userid = Idpw.userid "
				        + "WHERE carname LIKE ? "
				        + "AND fdate >= DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m-01')\r\n "
				        + "AND fdate <  DATE_FORMAT(CURRENT_DATE + INTERVAL 2 MONTH, '%Y-%m-01') " 
				        + "ORDER BY Reserve.reservecreated desc";
				pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, carname);

				// SQL文を実行し、結果表を取得する
				rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {

				    java.sql.Timestamp sqlsDate = rs.getTimestamp("sdate");
				    java.sql.Timestamp sqlfDate = rs.getTimestamp("fdate");

				    LocalDateTime sdate = (sqlsDate != null) ? sqlsDate.toLocalDateTime() : null;
				    LocalDateTime fdate = (sqlfDate != null) ? sqlfDate.toLocalDateTime() : null;

					 Reserve bc = new Reserve(
							rs.getInt("reservenumber"),
							sdate,
							fdate,
						    rs.getString("purpose"),
					        rs.getString("username")
					    );
					cardList.add(bc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			} finally {
				closeAll(conn,rs,pStmt);
			}
			// 結果を返す
			return cardList;	
		}
		
		
		public boolean delete(Reserve card) {
			Connection conn = getConnection();
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			boolean result = false;

			try {
				// SQL文を準備する
				String sql = "DELETE FROM Reserve WHERE reservenumber?";
				pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, card.getReservenumber());

				// SQL文を実行する
				pStmt.executeUpdate();
				pStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeAll(conn,rs,pStmt);
			}
	
			// 結果を返す
			return result;
		}
		
		public int insert(Reserve card) {
			Connection conn = getConnection();
			ResultSet rs = null;
			PreparedStatement pStmt = null;
			int reservenumber = -1;

			try {
				// SQL文を準備する
				String sql = "INSERT INTO Reserve (userid,sdate,fdate,carid,purpose) "
						+ "VALUES (?, ?, ?, ?, ?)";
				pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


				// SQL文を完成させる
				if (card.getUserid() !=null) {
					pStmt.setString(1, card.getUserid());
				} else {
					pStmt.setString(1, "");
				}
				if (card.getSdate() != null) {
				    pStmt.setTimestamp(2, java.sql.Timestamp.valueOf(card.getSdate()));
				} else {
				    pStmt.setNull(2, java.sql.Types.TIMESTAMP);
				}
				if (card.getFdate() != null) {
				    pStmt.setTimestamp(3, java.sql.Timestamp.valueOf(card.getFdate()));
				} else {
				    pStmt.setNull(3, java.sql.Types.TIMESTAMP);
				}
				if (card.getCarid() != 0) {
					pStmt.setInt(4, card.getCarid());
				} else {
					pStmt.setInt(4, java.sql.Types.INTEGER);
				}
				if (card.getPurpose() != null) {
					pStmt.setString(5, card.getPurpose());
				} else {
					pStmt.setString(5, "");
				}


				// SQL文を実行する
				int rows = pStmt.executeUpdate();
			        if (rows == 1) {
			            rs = pStmt.getGeneratedKeys();
			            if (rs.next()) {
			                reservenumber = rs.getInt(1);  // ← これが AUTO_INCREMENT の reservenumber
			            }
			        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        closeAll(conn, rs, pStmt);
		    }

		    return reservenumber;
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
