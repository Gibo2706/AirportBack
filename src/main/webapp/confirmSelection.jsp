<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Confirm Selection</title>
<link rel="stylesheet" type="text/css" href="/Airport/css/confirmSelection.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<header class="bg-primary text-white text-center py-3">
		<h1>Confirm Selection</h1>
	</header>

	<section class="container mt-4">
		<c:if test="${empty passengers}">
			<h2>Passenger Details</h2>
			<form id="passengerDetailsForm"
				action="/Airport/booking/addPassenger" method="post">
				<input type="hidden" name="flightNumber" value="${flightNumber}">
				<input type="hidden" name="numberSeats" value="${numberSeats}">
				<c:forEach begin="1" end="${numberSeats}" var="seatNumber">
					<div class="mb-3">
						<label for="passengerName${seatNumber}" class="form-label">Passenger
							${seatNumber} Name</label> <input type="text" class="form-control"
							id="passengerName${seatNumber}" name="passengerNames" required>
					</div>
				</c:forEach>
				<button type="submit" class="btn btn-primary">Add Passenger
					Details</button>
			</form>
		</c:if>
		<c:if test="${not empty passengers}">
			<div class="mt-4">
				<h2>Reservation Preview</h2>
				<p>Flight Number: ${fNumber}</p>
				<p>Selected Seats: 
					<c:forEach items="${sedista}" var="s">
						${s.id.row}-${s.id.column}
					</c:forEach>
				</p>

				<h3>Passenger Details</h3>
				<ul>
					<c:forEach items="${passengers}" var="passenger" varStatus="status">
						<li>Passenger ${status.index + 	1 }: ${passenger}</li>
					</c:forEach>
				</ul>

				<form action="/Airport/booking/confirmReservation" method="post">
					<input type="hidden" name="fNumber" value="${fNumber}">
					<input type="hidden" name="seats" value="${sedista}">
					<button type="submit" class="btn btn-success">Confirm
						Reservation</button>
				</form>
			</div>
		</c:if>
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