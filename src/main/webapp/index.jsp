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
<title>Welcome to Flight Booking</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/landing.css">
<style>
body {
	margin-bottom: 56px;
}

footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #007bff;
	color: #fff;
	text-align: center;
	padding: 20px 0;
}
</style>
</head>
<body>

	<header class="bg-primary text-white text-center py-3">
		<h1>Welcome to Flight Booking</h1>
	</header>

	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>

	<section class="container mt-4">
		<h2>About Us</h2>
		<p>We strive to provide you with the best flight booking
			experience possible. Whether you're traveling for business or
			leisure, our platform offers a seamless booking process and a wide
			range of options to suit your needs.
	</section>


	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
