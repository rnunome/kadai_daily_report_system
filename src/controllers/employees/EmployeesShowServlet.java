package controllers.employees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;
/**
 * Servlet implementation class EmployeesShowServlet
 */
//自動生成のクラス
@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    //自動生成のコンストラクタ
    public EmployeesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //リソースの詳細な情報を画面に表示するためのメソッド
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DBに接続
        EntityManager em = DBUtil.createEntityManager();
        //employeeクラスの情報を表示
        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
        //DBとの接続を閉じる
        em.close();
        //viewにデータを送るための命令
        request.setAttribute("employee", e);
        //サーブレットからJSPを呼び出すための命令
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);
    }

}
