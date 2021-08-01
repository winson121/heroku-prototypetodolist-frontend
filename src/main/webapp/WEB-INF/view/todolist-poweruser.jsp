<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
 
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<title>To do List</title>
</head>
 
<body>
	
	<div class="container">
		<h3>${user.firstName } ${user.lastName}'s To do List</h3>
		<hr>
		
		<!-- add todo list table -->
		<table class="table table-bordered table-striped">
			<tr>
				<th>Activity</th>
				<th>Status</th>
			</tr>
			
			<!-- loop over -->
			<c:forEach var="tempTodo" items="${todos}">
				<tr>
					<td>${tempTodo.activity}</td>
					<td>${tempTodo.status}</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<a href="${pageContext.request.contextPath }/home">Back</a>
		</div>
	</div>
	
	
		
</body>
 
</html>