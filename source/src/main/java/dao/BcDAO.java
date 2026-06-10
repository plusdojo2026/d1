package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BcCompany;

public class BcDAO {
	//データベースの情報を格納するフィールド(他のDAOにもコピペで使用)
	private final String URL = "jdbc:mysql://localhost:3306/meisi?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
	private final String USER = "root";
	private final String PASS = "password";
	// 引数card指定された項目で検索して、取得されたデータのリストを返す
	public List<BcCompany> select(BcCompany card) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		List<BcCompany> cardList = new ArrayList<>();
		

		try {
			// SQL文を準備する
//			String sql = "SELECT * FROM main INNER JOIN companies ON main.company_id = companies.company_id WHERE name LIKE ? AND companies.company LIKE ? ORDER BY number";
			
			String sql = "SELECT name, company, url, zipcode, address, kuni, fax, "
			        + "number, userID, day, department, phone, position, email, remark, stars_id "
			        + "FROM main INNER JOIN companies ON main.company_id = companies.company_id "
			        + "WHERE company LIKE ? AND department LIKE ? AND position LIKE ? "
			        + "AND name LIKE ? AND zipcode LIKE ? AND address LIKE ? AND phone LIKE ? "
			        + "AND fax LIKE ? AND email LIKE ? AND remark LIKE ? "
			        + "ORDER BY number";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCompany() != null) {
				pStmt.setString(1, "%" + card.getCompany() + "%");
			} else {
				pStmt.setString(1, "%");
			}
			if (card.getDepartment() != null) {
				pStmt.setString(2, "%" + card.getDepartment() + "%");
			} else {
				pStmt.setString(2, "%");
			}
			if (card.getPosition() != null) {
				pStmt.setString(3, "%" + card.getPosition() + "%");
			} else {
				pStmt.setString(3, "%");
			}
			if (card.getName() != null) {
				pStmt.setString(4, "%" + card.getName() + "%");
			} else {
				pStmt.setString(4, "%");
			}
			if (card.getZipcode() != null) {
				pStmt.setString(5, "%" + card.getZipcode() + "%");
			} else {
				pStmt.setString(5, "%");
			}
			if (card.getAddress() != null) {
				pStmt.setString(6, "%" + card.getAddress() + "%");
			} else {
				pStmt.setString(6, "%");
			}
			if (card.getPhone() != null) {
				pStmt.setString(7, "%" + card.getPhone() + "%");
			} else {
				pStmt.setString(7, "%");
			}
			if (card.getFax() != null) {
				pStmt.setString(8, "%" + card.getFax() + "%");
			} else {
				pStmt.setString(8, "%");
			}
			if (card.getEmail() != null) {
				pStmt.setString(9, "%" + card.getEmail() + "%");
			} else {
				pStmt.setString(9, "%");
			}
			if (card.getRemarks() != null) {
				pStmt.setString(10, "%" + card.getRemarks() + "%");
			} else {
				pStmt.setString(10, "%");
			}

			// SQL文を実行し、結果表を取得する
			rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				BcCompany bc = new BcCompany(
				        rs.getString("name"),
				        rs.getInt("company_id"),
				        rs.getString("company"),
				        rs.getString("url"),
				        rs.getString("zipcode"),
				        rs.getString("address"),
				        rs.getInt("kuni"),
				        rs.getString("fax"),
				        rs.getInt("number"),
				        rs.getInt("userID"),
				        rs.getDate("day").toLocalDate(),
				        rs.getString("department"),
				        rs.getString("phone"),
				        rs.getString("position"),
				        rs.getString("email"),
				        rs.getString("remark"),
				        rs.getInt("stars_id")
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

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(BcCompany card) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// SQL文を準備する
			String sql = "INSERT INTO Bc VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getName() != null) {
				pStmt.setString(1, card.getName());
			} else {
				pStmt.setString(1, "");
			}
			if (card.getCompany_id() != 0) {
				pStmt.setInt(2, card.getCompany_id());
			} else {
				pStmt.setInt(2, java.sql.Types.INTEGER);
			}
			if (card.getCompany() != null) {
				pStmt.setString(3, card.getCompany());
			} else {
				pStmt.setString(3, "");
			}
			if (card.getComurl() != null) {
				pStmt.setString(4, card.getComurl());
			} else {
				pStmt.setString(4, "");
			}
			if (card.getZipcode() != null) {
				pStmt.setString(5, card.getZipcode());
			} else {
				pStmt.setString(5, "");
			}
			if (card.getAddress() != null) {
				pStmt.setString(6, card.getAddress());
			} else {
				pStmt.setString(6, "");
			}
			if (card.getKuni() != 0) {
				pStmt.setInt(7, card.getKuni());
			} else {
				pStmt.setInt(7, java.sql.Types.INTEGER);
			}
			if (card.getFax() != null) {
				pStmt.setString(8, card.getFax());
			} else {
				pStmt.setString(8, "");
			}
			if (card.getNumber() != 0) {
				pStmt.setInt(9, card.getNumber());
			} else {
				pStmt.setInt(9, java.sql.Types.INTEGER);
			}
			if (card.getUserID() != 0) {
				pStmt.setInt(10, card.getUserID());
			} else {
				pStmt.setInt(10, java.sql.Types.INTEGER);
			}
			if (card.getDay() != null) {
			    pStmt.setDate(11, java.sql.Date.valueOf(card.getDay()));
			} else {
			    pStmt.setNull(11, java.sql.Types.DATE);
			}
			if (card.getDepartment() != null) {
				pStmt.setString(12, card.getDepartment());
			} else {
				pStmt.setString(12, "");
			}
			if (card.getPhone() != null) {
				pStmt.setString(13, card.getPhone());
			} else {
				pStmt.setString(13, "");
			}
			if (card.getPosition() != null) {
				pStmt.setString(14, card.getPosition());
			} else {
				pStmt.setString(14, "");
			}
			if (card.getEmail() != null) {
				pStmt.setString(15, card.getEmail());
			} else {
				pStmt.setString(15, "");
			}
			if (card.getRemarks() != null) {
				pStmt.setString(16, card.getRemarks());
			} else {
				pStmt.setString(16, "");
			}
			if (card.getStars_id() != 0) {
				pStmt.setInt(17, card.getStars_id());
			} else {
				pStmt.setInt(17, java.sql.Types.INTEGER);
			}
			
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn,rs,pStmt);
		}

		// 結果を返す
		return result;
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(BcCompany card) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// SQL文を準備する
			String sql = "UPDATE BcCompany SET name=?, company_id=?, company=?, url=?, zipcode=?, address=?, kuni=?, fax=?, number=?, userID=?, day=?, department=?, phone=?, position=?, email=?, remark=?, stars_id=?,"
					+ "FROM main INNER JOIN companies ON main.company_id = companies.company_id "
					+ "WHERE number=?";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getName() != null) {
				pStmt.setString(1, card.getName());
			} else {
				pStmt.setString(1, "");
			}
			if (card.getCompany_id() != 0) {
				pStmt.setInt(2, card.getCompany_id());
			} else {
				pStmt.setInt(2, java.sql.Types.INTEGER);
			}
			if (card.getCompany() != null) {
				pStmt.setString(3, card.getCompany());
			} else {
				pStmt.setString(3, "");
			}
			if (card.getComurl() != null) {
				pStmt.setString(4, card.getComurl());
			} else {
				pStmt.setString(4, "");
			}
			if (card.getZipcode() != null) {
				pStmt.setString(5, card.getZipcode());
			} else {
				pStmt.setString(5, "");
			}
			if (card.getAddress() != null) {
				pStmt.setString(6, card.getAddress());
			} else {
				pStmt.setString(6, "");
			}
			if (card.getKuni() != 0) {
				pStmt.setInt(7, card.getKuni());
			} else {
				pStmt.setInt(7, java.sql.Types.INTEGER);
			}
			if (card.getFax() != null) {
				pStmt.setString(8, card.getFax());
			} else {
				pStmt.setString(8, "");
			}
			if (card.getNumber() != 0) {
				pStmt.setInt(9, card.getNumber());
			} else {
				pStmt.setInt(9, java.sql.Types.INTEGER);
			}
			if (card.getUserID() != 0) {
				pStmt.setInt(10, card.getUserID());
			} else {
				pStmt.setInt(10, java.sql.Types.INTEGER);
			}
			if (card.getDay() != null) {
			    pStmt.setDate(11, java.sql.Date.valueOf(card.getDay()));
			} else {
			    pStmt.setNull(11, java.sql.Types.DATE);
			}
			if (card.getDepartment() != null) {
				pStmt.setString(12, card.getDepartment());
			} else {
				pStmt.setString(12, "");
			}
			if (card.getPhone() != null) {
				pStmt.setString(13, card.getPhone());
			} else {
				pStmt.setString(13, "");
			}
			if (card.getPosition() != null) {
				pStmt.setString(14, card.getPosition());
			} else {
				pStmt.setString(14, "");
			}
			if (card.getEmail() != null) {
				pStmt.setString(15, card.getEmail());
			} else {
				pStmt.setString(15, "");
			}
			if (card.getRemarks() != null) {
				pStmt.setString(16, card.getRemarks());
			} else {
				pStmt.setString(16, "");
			}
			if (card.getStars_id() != 0) {
				pStmt.setInt(17, card.getStars_id());
			} else {
				pStmt.setInt(17, java.sql.Types.INTEGER);
			}
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			closeAll(conn,rs,pStmt);
		}

		// 結果を返す
		return result;
	}

	// 引数cardで指定された番号のレコードを削除し、成功したらtrueを返す
	public boolean delete(BcCompany card) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// SQL文を準備する
			String sql = "DELETE FROM main INNER JOIN companies ON main.company_id = companies.company_id WHERE number=?";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, card.getNumber());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn,rs,pStmt);
		}

		// 結果を返す
		return result;
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
