<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>Prototype ToDo List Home Page</title>
</head>

<body>
	<h2>Prototype ToDo List Home Page</h2>
	<hr>
	
	<div>
		
		<div>
			<a href="${pageContext.request.contextPath }/todos/userToDos">Go to ToDo List page</a>
		</div>
		<hr>
		
		<!-- display user name and role -->
		
		<p>
			Current User: <security:authentication property="principal.username" />
			<br><br>
			Role(s): <security:authentication property="principal.authorities" />
			<br><br>
			First name: ${user.firstName}, Last name: ${user.lastName}, Email: ${user.email}
		</p>
		
		<security:authorize access="hasRole('PowerUser')">
		
			<!-- Add a link to point to /todos/powerusers ... this is for the PowerUser -->
			
			<p>
				<a href="${pageContext.request.contextPath}/todos/poweruser">PowerUsers ToDo list page</a>
				(Can access everyone's ToDo List)
			</p>
			
		</security:authorize>	
		
		<security:authorize access="hasRole('Admin')">
			<!-- Add a link to point to /todos/admin -->
			<p>
				<a href="${pageContext.request.contextPath}/todos/admin">Admin page</a>
				(Can delete user)
			</p>
		</security:authorize>
		<hr>
	</div>	
	
	<div>
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath}/logout" 
				   method="POST">
		
			<input type="submit" value="Logout" />
		
		</form:form>
	</div>
</body>

</html>



