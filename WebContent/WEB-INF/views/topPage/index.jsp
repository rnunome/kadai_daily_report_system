<%--スクリプト式からJSTLへ--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--JSPの設定を記載 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--app.jspのテンプレートを使用 --%>
<c:import url="../layout/app.jsp">
    <%--以下はテンプレとは異なる内容 --%>
    <c:param name="content">
        <%--もしnullじゃなかったらフラッシュメッセージを表示? --%>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>日報管理システムへようこそ</h2>
        <h3>【自分の日報　一覧】</h3>
        <%--日報のテーブル --%>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="report_title">${report.title}</td>
                        <td class="report_action"><a href="<c:url value='/reports/show?id=${report.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <%--日報数を表示 --%>
        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <%--1-14件までの日報を表示? --%>
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                    <%--ページを表示--%>
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <%--次のページに行くリンクを表示 --%>
                    <c:otherwise>
                        <a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <%--日報の登録画面へ飛ぶリンク --%>
        <p><a href="<c:url value='/reports/new' />">新規日報の登録</a></p>
    </c:param>
</c:import>