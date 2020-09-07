package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//reportsというデータの入った表からデータを取得する
@Table(name="reports")
@NamedQueries({
    @NamedQuery(//日報情報を取得するためにgetAllReportsという名前をつける
            name = "getAllReports",
            query = "SELECT r FROM Report AS r ORDER BY r.id DESC"
            ),
    @NamedQuery(//日報情報の全件数を取得するためにgetReportsCountという名前をつける
            name = "getReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r"
            ),
    @NamedQuery(//自分の日報情報を取得するためにgetMyAllReportsという名前をつける
            name = "getMyAllReports",
            query = "SELECT r FROM Report AS r WHERE r.employee = :employee ORDER BY r.id DESC"
            ),
    @NamedQuery(//自分の日報情報の全件数を取得するためにgetMyReportsCountという名前をつける
            name = "getMyReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :employee"
            )
})
@Entity
public class Report {
    @Id
    @Column(name = "id")//テーブルから自動に振られるidを作成
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne//1対多の関係をつくる
    @JoinColumn(name = "employee_id", nullable = false)//テーブルから従業員idを入力
    private Employee employee;

    @Column(name = "report_date", nullable = false)//日報日時を入力
    private Date report_date;

    @Column(name = "title", length = 255, nullable = false)//タイトルを入力
    private String title;

    @Column(name = "started_work_at", length = 255, nullable = false)
    private String started_work_at;

    @Column(name = "finished_work_at", length = 255, nullable = false)
    private String finished_work_at;

    @Lob//改行もデータベースに保存するためのアノテーション
    @Column(name ="content", nullable = false)//日報の内容を入力
    private String content;

    @Column(name = "created_at", nullable = false)//作成日時を入力
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)//更新日時を入力
    private Timestamp updated_at;
  //Privateの値を変えられるように値をセット・取得する操作（セッターとゲッター）
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStarted_work_at(){
        return started_work_at;
    }

    public void setStarted_work_at(String started_work_at){
        this.started_work_at = started_work_at;
    }

    public String getFinished_work_at(){
        return finished_work_at;
    }

    public void setFinished_work_at(String finished_work_at){
        this.finished_work_at = finished_work_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
