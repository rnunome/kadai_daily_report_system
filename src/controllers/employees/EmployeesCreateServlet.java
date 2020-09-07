package controllers.employees;

import java.io.IOException;
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
import models.validators.EmployeeValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesCreateServlet
 */
@WebServlet("/employees/create")
public class EmployeesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    //新規登録処理（INSERT）
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //正しく画面が遷移するか確認
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            //DBとの接続
            EntityManager em = DBUtil.createEntityManager();
            //インスタンス化
            Employee e = new Employee();
            //ブラウザからリクエストされた際に送られてくる社員番号・名前・パスワード情報
            e.setCode(request.getParameter("code"));
            e.setName(request.getParameter("name"));
            e.setPassword(
                    EncryptUtil.getPasswordEncrypt(//パスワード解読？
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("pepper")//pepper文字列とは？
                            )
                    );
            e.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));//権限があるか
            //作成・更新時間の登録？
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);
            e.setDelete_flag(0);

            //パスワードの入力値チェックと社員番号の重複チェック
            List<String> errors = EmployeeValidator.validate(e, true, true);
            if(errors.size() > 0) {
                em.close();
                //viewに遷移・従業員・エラーデータを送るための命令
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employee", e);
                request.setAttribute("errors", errors);
                //ServletからJSPを呼び出すための命令
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/new.jsp");
                rd.forward(request, response);
            } else {//??
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                em.close();
                //フラッシュメッセージを表示
                request.getSession().setAttribute("flush", "登録が完了しました。");
                //employees/index画面に戻る
                response.sendRedirect(request.getContextPath() + "/employees/index");
            }
        }
    }
}

