<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>

	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
	
	<title>Save To Do</title>
</head>

<body>
	<div class="container">
	
		<p class="h4 mb-4">Save To do</p>
		
		<form:form action="saveUserToDo" modelAttribute="todo" method="POST">
			
			<!-- need to associate this data with todo id -->
			<form:hidden path="id" /> 
			
			<table>
				<tbody>
					<tr>
						<td><label>Activity:</label></td>
						<td><form:input path="activity" /></td>
					</tr>
					<tr>
						<td><label>Status:</label></td>
						<td>
							<form:select path="status">
								<form:option item="Pending" value="Pending"/>
								<form:option item="Ongoing" value="Ongoing"/>
								<form:option item="Finished" value="Finished"/>
							</form:select>
						</td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/todos/userToDos">Back to List</a>
		</p>
	</div>
</body>

</html>