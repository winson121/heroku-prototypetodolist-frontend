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
		
		<!-- Add a button -->
		
		<input type="button" value="Add To do"
			   onclick="window.location.href='showFormForAdd'; return false"
			   class="btn btn-primary btn-sm mb-3"/>
		
		<!-- add todo list table -->
		<table class="table table-bordered table-striped">
			<tr>
				<th>Activity</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
			
			<!-- loop over -->
			<c:forEach var="tempTodo" items="${todos}">
				
				<!-- update link with todo id -->
				<c:url var="updateLink" value="/todos/showFormForUpdate">
					<c:param name="todoId" value="${tempTodo.id}"/>
				</c:url>
				
				<!-- construct delete link with todoId -->
				<c:url var="deleteLink" value="/todos/delete">
					<c:param name="todoId" value="${tempTodo.id }"/>
				</c:url>
				
				<tr>
					<td>${tempTodo.activity}</td>
					<td>${tempTodo.status}</td>
					<td>
						<!-- display update link -->
						<a href="${updateLink}" class="btn btn-info btn-sm">Update</a>
						
						<!-- display delete link -->
						<a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this todo?'))) return false"
							class="btn btn-info btn-sm">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<a href="${pageContext.request.contextPath }/home">Back</a>
		</div>
	</div>
	

		
</body>
 
</html>