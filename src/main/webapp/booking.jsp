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
<title>Book a Flight</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/Airport/css/landing.css">
<style>
/* Custom animations */
@
keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
section {
	animation: fadeIn 1.5s ease-in-out;
}

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
		<h2>Book a Flight</h2>

		<form action="/Airport/booking/showFlights" method="post">
			<div class="mb-3">
				<label for="departureAirport" class="form-label">Departure
					Airport</label> <select class="form-select" id="departureAirport"
					name="departureAirport" required>
					<c:forEach items="${airports}" var="it">
						<option value="${it.id}">${it.naziv}</option>
					</c:forEach>
				</select>
			</div>

			<div class="mb-3">
				<label for="arrivalAirport" class="form-label">Arrival
					Airport</label> <select class="form-select" id="arrivalAirport"
					name="arrivalAirport" required>
					<c:forEach items="${airports}" var="it">
						<option value="${it.id}">${it.naziv}</option>
					</c:forEach>
				</select>
			</div>

			<div class="mb-3">
				<label for="departureDate" class="form-label">Departure Date</label>
				<input type="date" class="form-control" id="departureDate"
					name="departureDate" required>
			</div>
			<button type="submit" class="btn btn-primary">Book Now</button>
		</form>
	</section>

	<c:if test="${!empty letovi}">
		<form action="/Airport/booking/seatSelection" method="post"
			id="flightForm">
			<div class="table-responsive">
				<table class="table table-bordered">
					<thead class="table-dark">
						<tr>
							<th>Flight Number</th>
							<th>Departure Airport</th>
							<th>Arrival Airport</th>
							<th>Departure Time</th>
							<th>Select</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="flight" items="${letovi}">
							<tr>
								<td>${flight.getFNumber()}</td>
								<td>${flight.aerodrom1.naziv}</td>
								<td>${flight.aerodrom2.naziv}</td>
								<td>${flight.datum.getHours()}:${flight.datum.getMinutes()}</td>
								<td>
									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="selectedFlight" value="${flight.getFNumber()}">
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<button type="submit" class="btn btn-primary">Confirm
				selected flight</button>
		</form>
	</c:if>
	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
