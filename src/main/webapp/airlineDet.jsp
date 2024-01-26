<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Airline Details</title>
<!-- <link rel="stylesheet" type="text/css"
	href="/Airport/css/airlineDet.css"> -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
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
	position: relative;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body>
	<header class="text-center py-3">
		<h1>Airline Details</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>
	<div class="container">
		<h1>Airline Details</h1>
		<hr>
		<div class="airline-info">
			<h2>Airline Information</h2>
			<div class="info-item">
				<strong>Email:</strong> <span>${airline.email}</span>
			</div>
			<div class="info-item">
				<strong>Name:</strong> <span>${airline.naziv}</span>
			</div>
		</div>

		<div class="airplane-list">
			<h2>Airplanes</h2>
			<hr>
			<ul>
				<c:forEach var="airplane" items="${airplaneList}">
					<li>${airplane.tailNumber}-${airplane.tipaviona.naziv}<c:if
							test="${airplane.aviokompanija2!=null}"> - <i>RENTED
								TO</i>: { <strong>${airplane.aviokompanija2.naziv}</strong> }</c:if></li>
				</c:forEach>
			</ul>
		</div>

		<div class="flights-list">
			<h2>Flights</h2>
			<hr>
			<ul>
				<c:forEach var="flight" items="${flightList}">
					<li><h6>
							|<strong>${flight.getFNumber()}</strong>| FROM: <i>${flight.aerodrom1.naziv}</i>
							TO: <i>${flight.aerodrom2.naziv}</i>
						</h6></li>

				</c:forEach>
			</ul>
		</div>

		<div class="pilot-list">
			<h2>Pilots</h2>
			<hr>
			<ul>
				<c:forEach var="pilot" items="${pilotList}">
					<li>${pilot.name}${pilot.surname}</li>
				</c:forEach>
			</ul>
		</div>

		<hr>
		<!-- Button to generate JasperReport -->
		<div class="text-center mt-4">
			<form action="/Airport/avio/generateReport" method="post">
				<input type="hidden" name="airlineId" value="${airline.id}">
				<button type="submit" class="btn btn-primary">Generate
					Report</button>
			</form>
		</div>
	</div>
	<hr>
	<br>

	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>
