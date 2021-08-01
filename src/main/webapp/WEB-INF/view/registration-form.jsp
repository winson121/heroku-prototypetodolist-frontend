<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Register New User Form</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style>.error {color:red}</style>
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
			
			<!-- Login - form -->
				<form:form action="${pageContext.request.contextPath }/register/processRegistrationForm" 
							modelAttribute="ToDoUser"
							class="login100-form validate-form once-only"
							method="POST">
					<span class="login100-form-title p-b-26">
						Register
					</span>
					<span class="login100-form-title p-b-48">
						<i class="zmdi">User</i>
					</span>
					
					<!-- Place for messages: error, alert, etc ... -->
					<div class="form-group">
						<div class="col-xs-15">
							<div>
								
									<!-- Check for registration error -->
									
									<c:if test="${registrationError != null}">
										<div class="alert alert-danger col-xs-offset-1 col-xs-10">
											${registrationError }
										</div>
									</c:if>
					
							</div>
						</div>
					</div>
					

										
					<!-- User name -->
					<div class="wrap-input100 ">
							<form:errors path="userName" cssClass="error" />
							<form:input path="userName" placeholder="username (*)" class="form-control"/>
					</div>
					
					<!-- Password -->
					<div class="wrap-input100">
							
							<form:errors path="password" cssClass="error" />
							<form:password path="password" placeholder="Password (*)" class="form-control"/>
					</div>
					
					<!-- Confirm Password -->
					<div class="wrap-input100">
						<form:errors path="matchingPassword" cssClass="error" />
						<form:password path="matchingPassword" placeholder="Confirm password (*)" class="form-control"/>
					</div>
					
					<!-- First name -->
					<div class="wrap-input100 ">
							<form:errors path="firstName" cssClass="error" />
							<form:input path="firstName" placeholder="First name (*)" class="form-control"/>
					</div>
					
					<!-- Last name -->
					<div class="wrap-input100 ">
							<form:errors path="lastName" cssClass="error" />
							<form:input path="lastName" placeholder="Last name (*)" class="form-control"/>
					</div>
					
					<!-- Email -->
					<div class="wrap-input100 ">
							<form:errors path="email" cssClass="error" />
							<form:input path="email" placeholder="email (*)" class="form-control"/>
					</div>
					
					<!-- Register Button -->
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button id="saveBtn" type="submit" class="login100-form-btn" onclick="disableBtn()">
								Register
							</button>
							<script>$(document).ready(function(){
								$('.once-only').submit(function(){
									$(this).children('button').prop('disabled', true);
								});
							});
							</script>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/vendor/daterangepicker/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>