package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Gasoline;

public class GasolineDAO {

    // 登録
    public boolean insert(Gasoline gasoline) {

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/d1?"
                + "characterEncoding=utf8&useSSL=false"
                + "&serverTimezone=GMT%2B9"
                + "&rewriteBatchedStatements=true",
                "root",
                "password");

            String sql =
                "INSERT INTO gasoline "
              + "(userid, stationname, gasolineprice, createddate) "
              + "VALUES (?, ?, ?, ?)";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            pStmt.setString(1, "admin");
            pStmt.setString(2, gasoline.getStationname());
            pStmt.setInt(3, gasoline.getGasolineprice());

            pStmt.setTimestamp(
                4,
                java.sql.Timestamp.valueOf(
                    gasoline.getCreateddate()));

            int result =
                    pStmt.executeUpdate();

            return result == 1;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    // 一覧取得
    public List<Gasoline> selectAll() {
    	Connection conn = null;
        List<Gasoline> list =
                new ArrayList<>();
     
        try{// JDBCドライバを読み込む
     			Class.forName("com.mysql.cj.jdbc.Driver");
     			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "password");

            String sql =
                "SELECT * FROM gasoline "
              + "ORDER BY createddate DESC";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pStmt.executeQuery();

            while(rs.next()) {

                list.add(
                    new Gasoline(
                        rs.getInt("gasolineid"),
                        0,
                        rs.getString("stationname"),
                        rs.getInt("gasolineprice"),
                        rs.getTimestamp("createddate")
                          .toLocalDateTime()
                    )
                );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // 最安値取得
    public Gasoline getMinPrice() {

        Connection conn = null;

        Gasoline gasoline = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/d1?"
                + "characterEncoding=utf8&useSSL=false"
                + "&serverTimezone=GMT%2B9"
                + "&rewriteBatchedStatements=true",
                "root",
                "password");

            String sql =
                "SELECT * FROM gasoline "
              + "ORDER BY gasolineprice ASC "
              + "LIMIT 1";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pStmt.executeQuery();

            if(rs.next()) {

                gasoline =
                    new Gasoline(
                        rs.getInt("gasolineid"),
                        0,
                        rs.getString("stationname"),
                        rs.getInt("gasolineprice"),
                        rs.getTimestamp("createddate")
                          .toLocalDateTime()
                    );
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return gasoline;
    	}
}