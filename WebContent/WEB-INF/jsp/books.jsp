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
		
		<script src="https://code.jquery.com/jquery-2.1.0.js"></script>
		
		<script type="text/javascript">
			function pagination(current, last, intermediateSymbols) {
			    var delta = 2,
			        left = current - delta,
			        right = current + delta + 1,
			        range = [],
			        rangeWithIntermediateSymbols = [],
			        l;
	
			    for (let i = 1; i <= last; i++) {
			        if (i == 1 || i == last || i >= left && i < right) {
			            range.push(i);
			        }
			    }
	
			    for (let i of range) {
			        if (l) {
			            if (i - l === 2) {
			            	rangeWithIntermediateSymbols.push(l + 1);
			            } else if (i - l !== 1) {
			            	rangeWithIntermediateSymbols.push(intermediateSymbols);
			            }
			        }
			        rangeWithIntermediateSymbols.push(i);
			        l = i;
			    }
	
			    return rangeWithIntermediateSymbols;
			}
		</script>
				
		<script type="text/javascript">
			var intermediateSymbols = "...";
			var paginationList = pagination(${ requestScope.currentPage }, ${ requestScope.pagesAmount }, intermediateSymbols);
			for (var i = 0; i < paginationList.length; i++) {
				if (!(paginationList[i] == intermediateSymbols)) {
					$("center").append("<a href=\"Controller?selectedPage=" + paginationList[i] + "&parserType=" + "${ requestScope.parserType }" + "\">" + paginationList[i] + "</a>&nbsp;");	
				} else {
					$("center").append(intermediateSymbols + "&nbsp;");
				}
			}
		</script>
		
	</center>
</body>
</html>