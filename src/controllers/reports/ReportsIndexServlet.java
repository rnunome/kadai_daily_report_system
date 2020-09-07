package controllers.reports;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ReportsIndexServlet
 */
//自動生成のクラス
@WebServlet("/reports/index")
public class ReportsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
  //自動生成のコンストラクタ
    public ReportsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
  //登録済みのリソースをJSP画面に表示するためのメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //データベースに接続
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{//エラーが起きたときにキャッチ
            page = Integer.parseInt(request.getParameter("page"));//Integer.parseIntは引数にある文字列を数値に変える、getParameterは文字列を返す
        } catch(Exception e) {
            page = 1;//エラーが起きたらここに飛ぶ
        }
        List<Report> reports = em.createNamedQuery("getAllReports", Report.class)
                                  .setFirstResult(15 * (page - 1))//日報件数最初の14件表示
                                  .setMaxResults(15)
                                  .getResultList();
      //日報数を数える？
        long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                                     .getSingleResult();
      //DBとの接続を閉じる
        em.close();
        //viewに日報・日報数・ページ数？のデータを送るための命令
        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        //サーブレットからJSPを呼び出すためのおまじないの2行
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);
    }

}