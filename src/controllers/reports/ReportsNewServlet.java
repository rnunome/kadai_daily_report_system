package controllers.reports;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/reports/new")
public class ReportsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //新しいリソースの入力画面（フォーム）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //リクエストスコープで正しく画面遷移がなされているかを証明するためのデータをJSPに送る
        request.setAttribute("_token", request.getSession().getId());
        //インスタンス化
        Report r = new Report();
        //新規日報の作成日時を設定？
        r.setReport_date(new Date(System.currentTimeMillis()));
        //JSPにリクエストスコープで日報情報のデータを送る
        request.setAttribute("report", r);
        //サーブレットからJSPを呼び出すおまじない
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
        rd.forward(request, response);
    }

}