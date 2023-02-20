<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <%
 	/*
 	1.Locale객체얻기,생성
 	2.ResourceBundle 의 properties파일지정
 	*/
 %> 
<%--1. Locale 객체 설정(JSTL) --%>
<fmt:setLocale  value="${pageContext.request.locale}"/>
<fmt:setLocale value="${param.lang}"/>
<%--2. ResourceBundle의 properties 파일 지정(JSTL) --%>
<fmt:setBundle basename="com/itwill/i18n/guest"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSTL I18N[Internationalization] 국제화</h1><hr>
<a href="9.jstl_fmt_i18n.jsp?lang=en">영어</a>
<a href="9.jstl_fmt_i18n.jsp?lang=ja">일어</a>
<a href="9.jstl_fmt_i18n.jsp?lang=zh">중국어</a>
<a href="9.jstl_fmt_i18n.jsp?lang=ko">한국어</a>
<ol>
	<li><fmt:message key="title.main"></fmt:message></li>
	<li><fmt:message key="title.list"></fmt:message></li>
	<li><fmt:message key="title.view"></fmt:message></li>
	<li><fmt:message key="title.write"></fmt:message></li>
	<li><fmt:bundle basename="com/itwill/i18n/user"></li>
		<li><fmt:message key="page.title"></fmt:message></li>
		<li><fmt:message key="page.login.header"></fmt:message></li>
		<li><fmt:message key="page.view.header"></fmt:message></li>
		<li><fmt:message key="page.modify.header"></fmt:message></li>
		<li><fmt:message key="page.write.header"></fmt:message></li>
		<li><fmt:message key="button.login"></fmt:message></li>
		</fmt:bundle>
</ol>
</body>
</html>