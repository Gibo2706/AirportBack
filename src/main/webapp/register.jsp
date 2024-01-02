<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="/Airport/css/register.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>

	<div class="container">
		<div class="card">
			<div class="card-header">
				<h3>Register</h3>
			</div>
			<c:if test="${not empty error}">
				<div class="error-message">${error}</div>
			</c:if>
			<div class="card-body">
				<form action="registerUser" method="post">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" id="username" name="username" required>
					</div>
					<div class="form-group">
						<label for="username">Email</label> <input type="email"
							class="form-control" id="email" name="email" required>
					</div>
					<div class="form-group">
						<label for="username">First Name</label> <input type="text"
							class="form-control" id="fName" name="fName" required>
					</div>
					<div class="form-group">
						<label for="username">Last Name</label> <input type="text"
							class="form-control" id="lName" name="lName" required>
					</div>
					<div class="form-group">
						<label for="country">Country</label>
						<select class="form-control" id="country" name="country" required>
						<option value="" disabled selected>Select country</option>
							<c:forEach items="${drzave}" var="d">
								<option value="${d.code}">${d.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="username">Phone</label> <input type="tel"
							class="form-control" id="phone" name="phone" required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" name="password" required>
					</div>
					<div class="form-group">
						<label for="confirmPassword">Confirm Password</label> <input
							type="password" class="form-control" id="confirmPassword"
							name="confirmPassword" required>
					</div>
					<button type="submit" class="btn btn-primary">Register</button>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofEEx4Xl0MeS4SgA6owVIfYItQF2gR8Q2n"
		crossorigin="anonymous"></script>
</body>

</html>
