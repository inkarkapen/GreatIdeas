<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit</title>
</head>
<body>
	<h1><c:out value = "${ updateIdea.idea }"/></h1>
	<h2>Edit Idea</h2>
    <p><form:errors path="idea.*"/></p>
    <c:if test = "${ error != null }">
		<c:out value = "${ error }"></c:out>
	</c:if>
    <form:form method="POST" action="/update/${updateIdea.id}" modelAttribute="updateIdea">
        <p>
            <form:label path="idea">Idea:</form:label>
            <form:input path="idea"/>
        </p>
        <input type="submit" value="Edit Idea"/>
    </form:form>
    <h3><a href = "/delete/${updateIdea.id}">Delete</a></h3>
    <h3><a href = "/ideas">Go back</a></h3>
</body>
</html>