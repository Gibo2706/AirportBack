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
	background-color: #1a1a1a;
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
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et
			diam ac libero lobortis suscipit. Nulla facilisi. Vestibulum congue,
			sem eu tincidunt posuere, est velit lacinia sem, vel ultricies turpis
			dolor ut nunc. Duis ac lacus vel lacus viverra laoreet. Vestibulum
			ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia
			Curae; Integer euismod magna a tellus cursus, sit amet tristique nisl
			fermentum.</p>
		<p>Fusce volutpat justo vel odio pellentesque, sit amet dictum sem
			fermentum. Nam aliquam justo ut turpis dignissim, at sagittis nisl
			tincidunt. Nunc ullamcorper neque ac velit mattis, et hendrerit
			libero interdum. Phasellus ut augue ac justo cursus elementum vel vel
			justo. Ut hendrerit tempus ex, sit amet eleifend turpis blandit et.</p>
	</section>


	<footer class="bg-info text-white text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofEEx4Xl0MeS4SgA6owVIfYItQF2gR8Q2n"
		crossorigin="anonymous"></script>
</body>
</html>
