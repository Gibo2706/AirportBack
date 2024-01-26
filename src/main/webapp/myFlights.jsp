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
<title>My Flights</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 
<link rel="stylesheet" type="text/css" href="/Airport/css/myFlights.css"> -->
<style>
header {
	background-color: #007bff;
	color: #fff;
	padding: 15px 0;
}

section {
	margin-top: 30px;
}

.flight-list {
	list-style: none;
	padding: 0;
}

.flight-item {
	background-color: #f8f9fa;
	margin-bottom: 15px;
	padding: 15px;
	border-radius: 8px;
}

footer {
	background-color: #007bff;
	color: #fff;
	padding: 10px 0;
	position: relative;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body style="margin: auto;">

	<header class="text-center py-3">
		<h1>My Flights</h1>
	</header>

	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>

	<section class="container mt-4">
		<ul class="flight-list">
			<c:if test="${letovi != null}"></c:if>
			<c:forEach items="${letovi}" var="flight">
				<li class="flight-item">
					<h3>${flight.let.getFNumber()}</h3>
					<h1>${flight.let.aerodrom1.getNaziv()} -->
						${flight.let.aerodrom2.getNaziv()}</h1>
					<h2>Seats: ${flight.sediste.id.row} -
						${flight.sediste.id.column}</h2>
					<h4>Date and Time of departure: ${flight.let.getDatumString() }</h4>
				</li>
			</c:forEach>
		</ul>
	</section>

	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
