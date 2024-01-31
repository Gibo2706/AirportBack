<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Panel</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
header {
	background-color: #007bff;
	color: #fff;
	padding: 15px 0;
}

section {
	margin-top: 30px;
}

.account-info {
	background-color: #f8f9fa;
	padding: 20px;
	border-radius: 8px;
}

footer {
	background-color: #007bff;
	color: #fff;
	padding: 10px 0;
	position: fixed;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body>
	<header class="text-center py-3">
		<h1>Admin dashboard</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>
	<div class="container mt-4">
		<h1>Admin Panel</h1>
		<c:if test="${not empty msg}">
			<p>${msg }
		</c:if>
		<div class="mb-3">
			<h3>Add User</h3>
			<form action="add-user" method="post">
				<div class="mb-3">
					<label for="inputUsername" class="form-label">Username</label> <input
						type="text" class="form-control" id="inputUsername"
						name="username">
				</div>
				<div class="mb-3">
					<label for="inputEmail" class="form-label">Email address</label> <input
						type="email" class="form-control" id="inputEmail" name="email">
				</div>
				<div class="mb-3">
					<label for="inputPassword" class="form-label">Password</label> <input
						type="password" class="form-control" id="inputPassword"
						name="password">
				</div>
				<div class="mb-3 form-check">
					<label class="form-check-label">User Role:</label><br> <input
						type="radio" id="normalUser" name="role" value="USER"> <label
						for="normalUser">Normal User</label><br> <input type="radio"
						id="adminUser" name="role" value="ADMIN"> <label
						for="adminUser">Admin</label><br> <input type="radio"
						id="aviationCompany" name="role" value="AVIOCOMPANY"> <label
						for="aviationCompany">Aviation Company</label><br>
				</div>
				<button type="submit" class="btn btn-primary">Add User</button>
			</form>
		</div>

		<div class="mb-3">
			<h3>Add Airline</h3>
			<form action="add-airline" method="post">
				<div class="mb-3">
					<label for="inputAirlineName" class="form-label">Airline
						Name</label> <input type="text" class="form-control" id="inputAirlineName"
						name="airlineName">
				</div>
				<div class="mb-3">
					<label for="airlineEmail" class="form-label">Airline's
						manager email: </label> <input type="text" class="form-control"
						id="airlineEmail" name="airlineEmail">
				</div>
				<button type="submit" class="btn btn-primary">Add Airline</button>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
