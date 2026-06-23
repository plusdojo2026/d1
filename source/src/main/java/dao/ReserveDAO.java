package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.Reserve;

public class ReserveDAO {
	// データベースの情報を格納するフィールド(他のDAOにもコピペで使用)
	private final String URL = "jdbc:mysql://localhost:3306/d1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
	private final String USER = "root";
	private final String PASS = "password";

//		public List<Reserve> Select(int carid) {
//			Connection conn = getConnection();
//			ResultSet rs = null;
//			PreparedStatement pStmt = null;
//			List<Reserve> cardList = new ArrayList<>();
//			
//			try {
//				// SQL文を準備する
////				String sql = "SELECT * FROM reserve INNER JOIN IdPw ON reserve.userid = IdPw.userid WHERE carid LIKE ? AND Companies.company LIKE ? ORDER BY Bc_number";
//				
//				String sql = "SELECT Idpw.username,Reserve.carid,reservenumber , sdate, fdate,purpose "
//				        + "FROM Reserve INNER JOIN Idpw ON Reserve.userid = Idpw.userid "
//				        + "WHERE carid LIKE ? "
//				        + "AND fdate >= DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m-01')\r\n "
//				        + "AND fdate <  DATE_FORMAT(CURRENT_DATE + INTERVAL 2 MONTH, '%Y-%m-01') " 
//				        + "ORDER BY Reserve.reservecreated desc";
//				pStmt = conn.prepareStatement(sql);
//
//				// SQL文を完成させる
//				pStmt.setInt(1, carid);
//
//				// SQL文を実行し、結果表を取得する
//				rs = pStmt.executeQuery();
//
//				// 結果表をコレクションにコピーする
//				while (rs.next()) {
//
//				    java.sql.Timestamp sqlsDate = rs.getTimestamp("sdate");
//				    java.sql.Timestamp sqlfDate = rs.getTimestamp("fdate");
//
//				    LocalDateTime sdate = (sqlsDate != null) ? sqlsDate.toLocalDateTime() : null;
//				    LocalDateTime fdate = (sqlfDate != null) ? sqlfDate.toLocalDateTime() : null;
//
//					 Reserve bc = new Reserve(
//							rs.getInt("reservenumber"),
//							sdate,
//							fdate,
//						    rs.getString("purpose"),
//					        rs.getString("username")
//					    );
//					cardList.add(bc);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				cardList = null;
//			} finally {
//				closeAll(conn,rs,pStmt);
//			}
//			// 結果を返す
//			return cardList;	
//		}

	public List<Reserve> Select(String carname) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		List<Reserve> cardList = new ArrayList<>();

		try {
			// SQL文を準備する
//				String sql = "SELECT * FROM reserve INNER JOIN IdPw ON reserve.userid = IdPw.userid WHERE carid LIKE ? AND Companies.company LIKE ? ORDER BY Bc_number";

			String sql = "SELECT Idpw.userid,Idpw.username,Reserve.carid,reservenumber , sdate, fdate,purpose "
					+ "FROM Reserve INNER JOIN Idpw ON Reserve.userid = Idpw.userid "
					+ "LEFT JOIN Cars ON Reserve.carid = Cars.carid " + "WHERE carname LIKE ? "
					+ "AND fdate >= DATE_FORMAT(CURRENT_DATE - INTERVAL 1 MONTH, '%Y-%m-01') "
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

				Reserve bc = new Reserve(rs.getInt("reservenumber"), sdate, fdate, rs.getString("purpose"),
						rs.getString("username"), rs.getString("userid"));
				cardList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			closeAll(conn, rs, pStmt);
		}
		// 結果を返す
		return cardList;
	}

	public List<Reserve> carList() {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		List<Reserve> cardList = new ArrayList<>();

		try {
			// SQL文を準備する
//				String sql = "SELECT * FROM reserve INNER JOIN IdPw ON reserve.userid = IdPw.userid WHERE carid LIKE ? AND Companies.company LIKE ? ORDER BY Bc_number";

			String sql = "SELECT DISTINCT carid,carname " + "FROM Cars";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			// SQL文を実行し、結果表を取得する
			rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Reserve bc = new Reserve(rs.getInt("carid"), rs.getString("carname"));
				cardList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			closeAll(conn, rs, pStmt);
		}
		// 結果を返す
		return cardList;
	}

	public int findCarid(String carname) {

		String sql = "SELECT carid " + "FROM Cars " + "WHERE carname LIKE ? ";

		try (Connection conn = getConnection(); PreparedStatement pStmt = conn.prepareStatement(sql)) {

			// 会社名（部分一致）
			pStmt.setString(1, "%" + carname.trim() + "%");

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("carid");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1; // 見つからなかった
	}

	public boolean update(Reserve card) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// SQL文を準備する
//				String sql = "SELECT * FROM reserve INNER JOIN IdPw ON reserve.userid = IdPw.userid WHERE carid LIKE ? AND Companies.company LIKE ? ORDER BY Bc_number";

			String sql = "UPDATE Reserve SET sdate=?, fdate=?, purpose=? " + "WHERE reservenumber =? ";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setTimestamp(1, Timestamp.valueOf(card.getSdate()));
			pStmt.setTimestamp(2, Timestamp.valueOf(card.getFdate()));
			pStmt.setString(3, card.getPurpose());
			pStmt.setInt(4, card.getReservenumber());

			// SQL文を実行し、結果表を取得する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}
		// 結果を返す
		return result;
	}

	public boolean updateStatus(int reservenumber, String status) {

		Connection conn = getConnection();
		PreparedStatement pStmt = null;

		try {

			String sql = "UPDATE Reserve " + "SET status=? " + "WHERE reservenumber=?";

			pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, status);
			pStmt.setInt(2, reservenumber);

			return pStmt.executeUpdate() == 1;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			closeAll(conn, null, pStmt);
		}

		return false;
	}

	public boolean delete(int reservenumber) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// SQL文を準備する
			String sql = "DELETE FROM Reserve WHERE reservenumber =?";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, reservenumber);

			// SQL文を実行する
			int rows = pStmt.executeUpdate();
			System.out.println("削除件数 rows = " + rows);

			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		// 結果を返す
		return result;
	}

	public boolean existsCarReserve(int carid, LocalDateTime sdate, LocalDateTime fdate) {

		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(*) cnt " + "FROM Reserve " + "WHERE carid=? " + "AND sdate < ? "
					+ "AND fdate > ?";

			pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, carid);
			pStmt.setTimestamp(2, java.sql.Timestamp.valueOf(fdate));
			pStmt.setTimestamp(3, java.sql.Timestamp.valueOf(sdate));

			rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt") > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		return false;
	}

	public boolean existsUserReserve(String userid, LocalDateTime sdate, LocalDateTime fdate) {

		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(*) cnt " + "FROM Reserve " + "WHERE userid = ? " + "AND sdate < ? "
					+ "AND fdate > ?";

			pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, userid);
			pStmt.setTimestamp(2, java.sql.Timestamp.valueOf(fdate));
			pStmt.setTimestamp(3, java.sql.Timestamp.valueOf(sdate));

			rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt") > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		return false;
	}

	public boolean insert(Reserve card) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// SQL文を準備する
			String sql = "INSERT INTO Reserve (userid,sdate,fdate,carid,purpose) " + "VALUES (?, ?, ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getUserid() != null) {
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
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		return result;
	}

	public List<Reserve> selectAll() {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		List<Reserve> reserveList = new ArrayList<>();

		try {
			String sql = "SELECT Reserve.sdate, Reserve.fdate, Cars.carname " + "FROM Reserve "
					+ "INNER JOIN Cars ON Reserve.carid = Cars.carid";
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Reserve reserve = new Reserve();

				reserve.setSdate(rs.getTimestamp("sdate").toLocalDateTime());
				reserve.setFdate(rs.getTimestamp("fdate").toLocalDateTime());
				reserve.setCarname(rs.getString("carname"));

				reserveList.add(reserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		return reserveList;
	}

	public List<Reserve> reserveAll(String userid) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		List<Reserve> reserveList = new ArrayList<>();

		try {
			String sql = "SELECT Reserve.reservenumber, Reserve.sdate, Reserve.fdate, "
					+ "Cars.carname, purpose, status " + "FROM Reserve "
					+ "INNER JOIN Cars ON Reserve.carid = Cars.carid " + "WHERE userid = ? " + "AND NOW() <= fdate "
					+ "ORDER BY sdate " + "LIMIT 1";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);

			rs = pStmt.executeQuery();

			while (rs.next()) {
				Reserve reserve = new Reserve();

				reserve.setSdate(rs.getTimestamp("sdate").toLocalDateTime());
				reserve.setFdate(rs.getTimestamp("fdate").toLocalDateTime());
				reserve.setCarname(rs.getString("carname"));
				reserve.setPurpose(rs.getString("purpose"));
				reserve.setReservenumber(rs.getInt("reservenumber"));

				reserve.setStatus(rs.getString("status"));
				reserveList.add(reserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		return reserveList;
	}

	public List<Reserve> reserveAlls(String userid) {
		Connection conn = getConnection();
		ResultSet rs = null;
		PreparedStatement pStmt = null;
		List<Reserve> reserveList = new ArrayList<>();

		try {
			String sql = "SELECT Reserve.fdate " + "FROM Reserve " + "INNER JOIN Cars ON Reserve.carid = Cars.carid "
					+ "WHERE userid LIKE ? AND CURRENT_TIMESTAMP < fdate ORDER BY sdate LIMIT 1";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + userid.trim() + "%");

			rs = pStmt.executeQuery();

			while (rs.next()) {
				Reserve reserve = new Reserve();

				reserve.setFdate(rs.getTimestamp("fdate").toLocalDateTime());

				reserveList.add(reserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, rs, pStmt);
		}

		return reserveList;
	}

	// 以下二つのメソッドも他のDAOにコピペで利用
	// 接続を行うメソッド
	private Connection getConnection() {
		Connection con = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続する
			con = DriverManager.getConnection(URL, USER, PASS);

			// 例外処理
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return con;
	}

	// 切断を行うメソッド
	private void closeAll(Connection con, ResultSet rs, PreparedStatement pStmt) {

		// Connection切断
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ResultSet切断
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// PreparedStatement切断
		if (pStmt != null) {
			try {
				pStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
