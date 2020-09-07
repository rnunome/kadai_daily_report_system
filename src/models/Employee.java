package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//employeesというデータの入った表からデータを取得する
@Table(name = "employees")
@NamedQueries({
    @NamedQuery(//すべての従業員の情報を取得するためにgetAllEmployeesという名前をつける
        name = "getAllEmployees",
        query = "SELECT e FROM Employee AS e ORDER BY e.id DESC"
        ),
    @NamedQuery(//従業員情報の全件数を取得するためにgetEmployeesCountという名前をつける
            name = "getEmployeesCount",
            query = "SELECT COUNT(e) FROM Employee AS e"
            ),
    @NamedQuery(//指定された社員番号がすでにデータベースに存在しているか調べるためにcheckRegisterCodeという名前をつける
            name = "checkRegisteredCode",
            query = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :code"
            ),
    @NamedQuery(//従業員がログインする際のパスワードが正しいか調べるためにcheckLoginCodeAndPasswordという名前をつける
            name = "checkLoginCodeAndPassword",
            query = "SELECT e FROM Employee AS e WHERE e.delete_flag = 0 AND e.code = :code AND e.password = :pass"
            )
})
@Entity
public class Employee {
    @Id
    @Column(name = "id")//テーブルから自動に振られるidを作成
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)//社員番号
    private String code;

    @Column(name = "name", nullable = false)//テーブルから取得従業員名を入力
    private String name;

    @Column(name = "password", length = 64, nullable = false)//テーブルからパスワードを入力
    private String password;

    @Column(name = "admin_flag", nullable = false)//管理者かどうかを入力
    private Integer admin_flag;

    @Column(name = "created_at", nullable = false)//作成日時を入力
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)//更新時間を入力
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)//削除された人かを入力
    private Integer delete_flag;
//Privateの値を変えられるように値をセット・取得する操作（セッターとゲッター）
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdmin_flag() {
        return admin_flag;
    }

    public void setAdmin_flag(Integer admin_flag) {
        this.admin_flag = admin_flag;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
