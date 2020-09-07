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
 * Servlet implementation class EmployeesEditServlet
 */
@WebServlet("/employees/edit")
public class EmployeesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //既存のリソースの編集画面（フォーム）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DBと接続
        EntityManager em = DBUtil.createEntityManager();
        //引数にあるidを文字列から数値に置き換える
        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));
        //DB接続を閉じる
        em.close();
        //リクエストスコープで従業員データと正しく画面遷移が行われているか証明するためのデータをJSPに送る
        request.setAttribute("employee", e);
        request.setAttribute("_token", request.getSession().getId());
        //リクエストスコープで従業員idデータをJSPに送る
        request.getSession().setAttribute("employee_id", e.getId());
        //サーブレットからJSPを呼び出すためのおまじない2行
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/edit.jsp");
        rd.forward(request, response);
    }

}