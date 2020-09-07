<%--スクリプト式からJSTLへ変換される--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--JSPの設定を記載 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--画面の配置テンプレ --%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>日報管理システム</title>
        <%--CSSのリンクを記載 --%>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
<div id="wrapper">
    <div id="header">
        <div id="header_menu">
        <h1><a href ="<c:url value='/'/>">日報管理システム</a></h1>&nbsp;&nbsp;&nbsp;
        <%--権限がある場合は従業員管理が表示される --%>
        <c:if test="${sessionScope.login_employee != null}">
            <c:if test="${sessionScope.login_employee.admin_flag ==1 }">
                <a href="<c:url value='/employees/index'/>">従業員管理</a>&nbsp;
        </c:if>
        <a href="<c:url value='/reports/index' />">日報管理</a>&nbsp;
        </c:if>
        </div>
        <%--ログイン情報が正しければログインした人の名前を表示 --%>
        <c:if test="${sessionScope.login_employee != null}">
            <div id="employee_name">
                <c:out value="${sessionScope.login_employee.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
               <%--ログアウトのためのリンク --%>
                <a href="<c:url value='/logout' />">ログアウト</a>
    </div>
    </c:if>
    </div>
    <div id="content">
    <%--テンプレとは異なる内容 --%>
        ${param.content}
    </div>
    <div id="footer">
        by Taro Kitameki.
    </div>
   </div>
 </body>
</html>