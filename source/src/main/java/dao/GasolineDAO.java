package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.CompareHistory;

public class GasolineDAO {

    private final String URL =
        "jdbc:mysql://localhost:3306/d1?characterEncoding=UTF-8";

    private final String USER = "root";
    private final String PASS = "password";

    // 比較履歴登録
    public boolean insertCompare(
            String stationname,
            int inputprice,
            int averageprice,
            String resultmessage) {

        try(Connection conn =
                DriverManager.getConnection(
                        URL, USER, PASS)) {

            String sql =
                "INSERT INTO GasolineCompare "
              + "(stationname,inputprice,"
              + "averageprice,resultmessage,"
              + "createddate)"
              + " VALUES(?,?,?,?,NOW())";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            pStmt.setString(1, stationname);
            pStmt.setInt(2, inputprice);
            pStmt.setInt(3, averageprice);
            pStmt.setString(4, resultmessage);

            return pStmt.executeUpdate() == 1;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // 履歴取得
    public List<CompareHistory> selectHistory() {

        List<CompareHistory> list =
                new ArrayList<>();

        try(Connection conn =
                DriverManager.getConnection(
                        URL, USER, PASS)) {

            String sql =
                "SELECT * "
              + "FROM GasolineCompare "
              + "ORDER BY createddate DESC";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pStmt.executeQuery();

            while(rs.next()) {

                list.add(
                    new CompareHistory(
                        rs.getInt("compareid"),
                        rs.getString("stationname"),
                        rs.getInt("inputprice"),
                        rs.getInt("averageprice"),
                        rs.getString("resultmessage"),
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
    public CompareHistory getMinHistory() {

        CompareHistory history = null;

        try(Connection conn =
                DriverManager.getConnection(
                        URL, USER, PASS)) {

            String sql =
                "SELECT * "
              + "FROM GasolineCompare "
              + "ORDER BY inputprice ASC "
              + "LIMIT 1";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pStmt.executeQuery();

            if(rs.next()) {

                history =
                    new CompareHistory(
                        rs.getInt("compareid"),
                        rs.getString("stationname"),
                        rs.getInt("inputprice"),
                        rs.getInt("averageprice"),
                        rs.getString("resultmessage"),
                        rs.getTimestamp("createddate")
                          .toLocalDateTime()
                    );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return history;
    }
}