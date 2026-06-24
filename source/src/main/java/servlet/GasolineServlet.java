package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.GasolineDAO;
import dto.Gasoline;

@WebServlet("/GasolineServlet")
public class GasolineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private int getAveragePriceFromApi() {

	    int avgPrice = 0;

	    try {

	        URL url =
	            new URL(
	              "http://localhost:8080/d1/GetGasolineApi");

	        HttpURLConnection con =
	            (HttpURLConnection) url.openConnection();

	        con.setRequestMethod("GET");

	        BufferedReader br =
	            new BufferedReader(
	                new InputStreamReader(
	                    con.getInputStream(),
	                    "UTF-8"));

	        StringBuilder sb =
	            new StringBuilder();

	        String line;

	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }

	        br.close();

	        JSONObject json =
	            new JSONObject(sb.toString());

	        avgPrice =
	            json.getInt("averagePrice");

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return avgPrice;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
				HttpSession session = request.getSession();
				if (session.getAttribute("userid") == null) {
					response.sendRedirect("/d1/LoginServlet");
					return;
				}
		GasolineDAO dao = new GasolineDAO();

		List<Gasoline> gasolineList = dao.selectAll();
		int avgPrice = getAveragePriceFromApi();

		for (Gasoline gasoline : gasolineList) {
			int diff = gasoline.getGasolineprice() - avgPrice;

			if (diff > 0) {
				gasoline.setResultMessage("+" + diff);
			} else if (diff < 0) {
				gasoline.setResultMessage(String.valueOf(diff));
			} else {
				gasoline.setResultMessage("±0");
			}
		}
		Gasoline minGasoline = dao.getMinPrice();
		request.setAttribute("avgPrice", avgPrice);
		request.setAttribute("gasolineList", gasolineList);

		request.setAttribute("minGasoline", minGasoline);
		System.out.println("件数=" + gasolineList.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gasoline.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String stationname = request.getParameter("stationname");
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		System.out.println("userid=" + userid);
		if (userid == null) {
			System.out.println("ログインしていない（userid null）");
			response.sendRedirect("login.jsp");
			return;
		}
		String priceStr = request.getParameter("gasolineprice");

		int gasolineprice;

		gasolineprice = Integer.parseInt(priceStr);

		Gasoline gasoline = new Gasoline(0, userid, stationname, gasolineprice, LocalDateTime.now());

		GasolineDAO dao = new GasolineDAO();

		boolean result = dao.insert(gasoline);
		System.out.println("登録結果=" + result);
		
		// JSONレスポンスを返す
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write("{\"success\": " + result + "}");
	}
}