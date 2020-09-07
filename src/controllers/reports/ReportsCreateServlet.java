package controllers.reports;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/reports/create")
public class ReportsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    //新規登録処理（INSERT）
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //処理が正しく画面遷移できるか証明するためにデータを送る命令
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
           //DBに接続
            EntityManager em = DBUtil.createEntityManager();
            //インスタンス化
            Report r = new Report();
            //入力した従業員の処理
            r.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            //インスタンス化
            Date report_date = new Date(System.currentTimeMillis());
            //ブラウザからリクエストされた際に送られる日報日時の情報
            String rd_str = request.getParameter("report_date");
            if(rd_str != null && !rd_str.equals("")) {
                report_date = Date.valueOf(request.getParameter("report_date"));
            }
            //日報を作成した日を表示する
            r.setReport_date(report_date);
            //ブラウザからリクエストされた際に送られてくるタイトルと日報の内容情報を処理
            r.setTitle(request.getParameter("title"));
            r.setStarted_work_at(request.getParameter("started_work_at"));
            r.setFinished_work_at(request.getParameter("finished_work_at"));
            r.setContent(request.getParameter("content"));
            //ブラウザからリクエストされた際に送られてくる作成日時と更新日時の処理
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdated_at(currentTime);
            //エラーが有った場合
            List<String> errors = ReportValidator.validate(r);
            if(errors.size() > 0) {
                //接続を閉じる
                em.close();
                //正しく画面が遷移できるか証明するためのデータを送るための命令
                request.setAttribute("_token", request.getSession().getId());
                //日報とエラーのデータをviewに送るための命令
                request.setAttribute("report", r);
                request.setAttribute("errors", errors);
                //サーブレットからJSPを呼び出すおまじない
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
                rd.forward(request, response);
            } else {//入力情報反映の登録の処理?
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                //接続を閉じる
                em.close();
                //フラッシュメッセージを出す
                request.getSession().setAttribute("flush", "登録が完了しました。");
                //report/indexの画面に戻る
                response.sendRedirect(request.getContextPath() + "/reports/index");
            }
        }
    }

}