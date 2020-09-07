package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
//自動生成されたクラス
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    //自動生成されたコンストラクタ
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //リソースの詳細な情報を画面に表示するためのメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //DBとの接続
        EntityManager em = DBUtil.createEntityManager();
        //日報ID情報を取得？
        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        //DBとの接続を閉じる
        em.close();
        //JSPに日報情報とセッションIDをリクエストスコープに登録
        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());
        //サーブレットからJSPを呼び出すための命令
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}