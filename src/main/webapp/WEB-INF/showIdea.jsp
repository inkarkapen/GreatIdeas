<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Idea</title>
</head>
<style>
	td, th{
		border: 1px black solid;
		padding: 10px;
	}
	table{
		width: 100%;
		text-align: left;
	}
	div {
		width: 980px;
		margin: 30px;
	}
</style>
<body>
	<div>
	<h1><c:out value = "${ idea.idea }"/></h1>
	<h3>Created By: <c:out value = "${ idea.user.username }"/></h3>
	<h2>User who liked the idea</h2>
	<table>
		<tr><td>User's name</td></tr>
		<c:forEach items = "${ idea.liked_users }" var = "user">
			<tr><th><c:out value = "${ user.username }"/></th></tr>
		</c:forEach>
	</table>
	<h3><a href = "/ideas/edit/${ idea.id }">Edit</a></h3>
	<h3><a href = "/ideas">Go back to ideas</a></h3>
	</div>
</body>
</html>