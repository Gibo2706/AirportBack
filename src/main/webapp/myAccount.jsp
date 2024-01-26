<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Account</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">


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
<body style="margin: auto;">

	<header class="text-center py-3">
		<h1>My Account</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>

	<section class="container mt-4">
		<div class="account-info">
			<h2>
				Welcome,
				<sec:authentication property="principal.user.name" />
			</h2>
			<p>Your account details:</p>
			<ul>
				<li><strong>Email:</strong> <sec:authentication
						property="principal.user.email" /></li>
				<li><strong>Username:</strong> <sec:authentication
						property="principal.user.username" /></li>
				<%-- <li><strong>Name:</strong> <sec:authentication
						property="principal.name" /></li> --%>
			</ul>
		</div>
	</section>

	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
