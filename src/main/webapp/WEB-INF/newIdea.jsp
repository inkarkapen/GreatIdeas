<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Idea</title>
</head>
<body>
<h1>Create a new Idea!!</h1>
	<c:if test = "${ error != null }">
		<c:out value = "${ error }"></c:out>
	</c:if>
	
	<form:form method = "POST" action = "/ideas/new" modelAttribute = "newIdea">
	<p>
		<form:label path = "idea">Idea:</form:label>
		<form:input path = "idea"/>
	</p>
	<input type = "submit" value = "Create!">
	</form:form>
	<h3><a href = "/ideas">Go back to ideas</a></h3>
</body>
</html>