package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GasolineDAO;
import dto.Gasoline;

@WebServlet("/GasolineServlet")
public class GasolineServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        GasolineDAO dao =
                new GasolineDAO();

        List<Gasoline> gasolineList =
                dao.selectAll();

        Gasoline minGasoline =
                dao.getMinPrice();

        request.setAttribute(
                "gasolineList",
                gasolineList);

        request.setAttribute(
                "minGasoline",
                minGasoline);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/WEB-INF/jsp/gasoline.jsp");

        dispatcher.forward(
                request,
                response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String stationname =
                request.getParameter(
                        "stationname");

        String priceStr =
                request.getParameter(
                        "gasolineprice");

        if(stationname == null ||
           stationname.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "スタンド名を入力してください");

            doGet(request,response);
            return;
        }

        if(priceStr == null ||
           priceStr.trim().isEmpty()) {

            request.setAttribute(
                    "error",
                    "価格を入力してください");

            doGet(request,response);
            return;
        }

        int gasolineprice;

        try {

            gasolineprice =
                    Integer.parseInt(
                            priceStr);

        } catch(NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "価格は数値で入力してください");

            doGet(request,response);
            return;
        }

        int avgPrice = 168;

        int diff;
        String resultMessage;

        if(gasolineprice < avgPrice) {

            diff =
                avgPrice - gasolineprice;

            resultMessage =
                diff + "円安い";

        } else if(gasolineprice > avgPrice) {

            diff =
                gasolineprice - avgPrice;

            resultMessage =
                diff + "円高い";

        } else {

            diff = 0;

            resultMessage =
                "同じ価格です";
        }

        Gasoline gasoline =
                new Gasoline(
                        0,
                        1,
                        stationname,
                        gasolineprice,
                        LocalDateTime.now());

        GasolineDAO dao =
                new GasolineDAO();

        dao.insert(gasoline);

        List<Gasoline> gasolineList =
                dao.selectAll();

        Gasoline minGasoline =
                dao.getMinPrice();

        request.setAttribute(
                "stationname",
                stationname);

        request.setAttribute(
                "avgPrice",
                avgPrice);

        request.setAttribute(
                "diff",
                diff);

        request.setAttribute(
                "resultMessage",
                resultMessage);

        request.setAttribute(
                "gasolineList",
                gasolineList);

        request.setAttribute(
                "minGasoline",
                minGasoline);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(
                        "/WEB-INF/jsp/gasoline.jsp");

        dispatcher.forward(
                request,
                response);
    }
}