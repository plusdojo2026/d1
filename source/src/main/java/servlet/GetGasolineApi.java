package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/GetGasolineApi")
public class GetGasolineApi extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // JSONオブジェクト作成
        JSONObject json = new JSONObject();

        // 合計価格と件数
        int total = 0;
        int count = 0;

        // CSVファイルの場所
        String csvFile =
        	    "C:/pleiades/workspace/d1/src/main/webapp/gasoline.csv";

        File file = new File(csvFile);

        System.out.println("存在=" + file.exists());
        System.out.println("パス=" + file.getAbsolutePath());
        System.out.println("サイズ=" + file.length());
        try {

            // CSVファイルを開く
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(csvFile));

            String line;

            // ヘッダー行(date,price)を読み飛ばす
            br.readLine();

            // 2行目以降を読み込む
            while ((line = br.readLine()) != null) {

                System.out.println("行=" + line);

                String[] data = line.split(",");

                int price =
                        Integer.parseInt(data[1]);

                total += price;

                count++;
            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        // 平均価格計算
        int avgPrice = 0;

        if (count > 0) {

            avgPrice = total / count;
        }

        // JSONへ格納
        json.put("averagePrice", avgPrice);
        System.out.println(avgPrice);
        json.put("dataCount", count);

        // レスポンス設定
        response.setContentType(
                "application/json;charset=UTF-8");

        response.setCharacterEncoding("UTF-8");

        // JSON出力
        PrintWriter out =
                response.getWriter();

        out.print(json);
    }
}