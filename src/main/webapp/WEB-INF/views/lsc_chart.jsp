<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차트</title>
<style>
 #header {
 	display: flex;
 	justify-content: center;
 }
 
 #menu-wrapper {
 	width: 100%;
 	display: flex;
 	justify-content: center;
 }
 
 #menu {
 	display: flex;
 }
 .genre-btn {
 	margin: 20px;
 }
</style>
</head>
<body>
	<div id="header">
		<h1>차트</h1>
		<a href="//localhost:8081/main">메인</a>
	</div>
	<div id="menu-wrapper">
		<div id="menu">
			<a href="#" class="genre-btn">TOP100</a>
			<a href="#" class="genre-btn">팝</a>
			<a href="#" class="genre-btn">발라드</a>
			<a href="#" class="genre-btn">록</a>
			<a href="#" class="genre-btn">포크</a>
			<a href="#" class="genre-btn">인디</a>
			<a href="#" class="genre-btn">댄스</a>
			<a href="#" class="genre-btn">클래식</a>
		</div>
	</div>
	<hr>
</body>
</html>