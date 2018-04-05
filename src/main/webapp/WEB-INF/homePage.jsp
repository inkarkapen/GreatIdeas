<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<style>
	th,td{
	border: 1px black solid;
	padding: 10px;
	}
	div {
	margin: 15px;
	}
</style>
<body>
	<div>
	<h1>Welcome, <c:out value = "${currentUser.username}"/></h1>
	<form id="logoutForm" method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	  	<input type="submit" value="Logout!" />
	</form>
	<h3><a href = "/ideas/new">Create a new idea</a></h3>
	<a href = "/ideas/ascOrder">Low Likes</a>
	<a href = "/ideas/descOrder">High Likes</a>
	<table>
		<tr>
			<th>Ideas</th>
			<th>Created By</th>
			<th>Likes</th>
			<th>Actions</th>
		</tr>
		<c:forEach items = "${ ideas }" var = "idea">
			<tr>
				<td><a href = "/ideas/${ idea.id }"><c:out value = "${ idea.idea }"/></a></td>
				<td><c:out value = "${ idea.user.username }"/></td>
				<td><c:out value = "${ idea.liked_users.size() }"/></td>
				<c:choose>
					<c:when test="${currentUser.liked_ideas.contains(idea)}">
						<td><a href = "/unlike/${ idea.id }">Unlike</a></td>
					</c:when>
					<c:otherwise>
						<td><a href = "/like/${ idea.id }">Like</a></td>
  					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>