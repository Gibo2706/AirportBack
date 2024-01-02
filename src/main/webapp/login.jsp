<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="/Airport/css/login.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="card col-md-6 offset-md-3 bg-white p-4 border rounded">
			<div class="card-body">
				<form method="post"
					action="${pageContext.request.contextPath}/login">
					<h2 class="text-center mb-4">Login</h2>
					<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
						<div class="error">
							<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
						</div>
					</c:if>
					<div class="form-group">
						<label for="username">Username:</label> <input name="username"
							class="form-control" id="username" required="required" />
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							name="password" class="form-control" id="password"
							required="required" />
					</div>
					<button type="submit" class="btn btn-primary btn-block">Login</button>
					<hr class="my-4">

					<a href="oauth2/authorization/github"
						class="btn btn-dark btn-block">Login with GitHub</a>
				</form>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
		integrity="sha384-XehGiCTtjhGC+2rL9JS7nptPyGnvD3p5s5qz7L7gCxpbmyQpZTQ9zmeQFHEBM2Hx"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-b4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+WyEqFSQr9tcx4PNIgMXkkoqXv4s5eS9qDj"
		crossorigin="anonymous"></script>
</body>
</html>
