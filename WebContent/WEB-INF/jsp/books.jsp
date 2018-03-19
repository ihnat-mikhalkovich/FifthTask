<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>books</title>
</head>
<body>
	
	<center>
		<table border="1">
			<thead>
			  	<tr>
			    	<th>ID</th>
				    <th>author</th>
				    <th>title</th>
				    <th>genre</th>
				    <th>price':' $</th>
				    <th>publish_date</th>
			  	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="elem" items="${ requestScope.books }" varStatus="status">
					<tr>
				    	<td><c:out value="${ elem.id }" /></td>
				    	<td><c:out value="${ elem.author }" /></td>
				   		<td><c:out value="${ elem.title }" /></td>
				   		<td><c:out value="${ elem.genre }" /></td>
				   		<td><c:out value="${ elem.price }" /></td>
				   		<td><c:out value="${ elem.publish_date }" /></td>
			 		</tr>
			  </c:forEach>
		  	</tbody>
		</table>
		
		<c:forEach var="elem" items="${ requestScope.paginationList }" varStatus="status">
			<c:set var="intermediateSymbols" value="${ requestScope.intermediateSymbolsOfPagination }" scope="page"/>
			<c:choose>
				<c:when test="${ !(elem eq intermediateSymbols) }">
					<a href="Controller?selectedPage=${ elem }&parserType=${ requestScope.parserType }">${ elem }</a>
					<c:out value=" "/>
				</c:when>
				<c:otherwise>
					<c:out value="${ requestScope.intermediateSymbolsOfPagination }"/>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</center>
</body>
</html>