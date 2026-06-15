package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Gasoline;

public class GasolineDAO {

    private final String URL =
        "jdbc:mysql://localhost:3306/d1?characterEncoding=UTF-8";

    private final String USER = "root";
    private final String PASS = "password";

    // 登録
    public boolean insert(Gasoline gasoline) {

        try(Connection conn =
                DriverManager.getConnection(
                        URL, USER, PASS)) {

            String sql =
                "INSERT INTO gasoline "
              + "(userid, stationname, gasolineprice, createddate) "
              + "VALUES (?, ?, ?, ?)";

            PreparedStatement pStmt =
                    conn.prepareStatement(sql);

            // idpwテーブルに存在するuseridを指定
            pStmt.setString(1, "admin");

            pStmt.setString(
                    2,
                    gasoline.getStationname());

            pStmt.setInt(
                    3,
                    gasoline.getGasolineprice());

            pStmt.setTimestamp(
                    4,
                    java.sql.Timestamp.valueOf(
                            gasoline.getCreateddate()));

            int result =
                    pStmt.executeUpdate();

            System.out.println(
                    "登録件数=" + result);

            return result == 1;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // 一覧取得
    public List<Gasoline> selectAll() {

        List<Gasoline> list =
                new ArrayList<>();

        try(Connection conn =
                DriverManager.getConnection(
                        URL, USER, PASS)) {

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

        Gasoline gasoline = null;

        try(Connection conn =
                DriverManager.getConnection(
                        URL, USER, PASS)) {

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