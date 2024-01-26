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
<link rel="stylesheet" type="text/css"
	href="/Airport/css/confirmSelection.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<header class="bg-primary text-white text-center py-3">
		<h1>Confirm Selection</h1>
	</header>

	<section class="container mt-4">
		<div class="mt-4">
			<h2>Reservation Preview</h2>
			<p>Flight Number: ${fNumber}</p>
			<p>
				Selected Seats:
				<c:forEach items="${sedista}" var="s">
						${s.id.row}-${s.id.column}
					</c:forEach>
			</p>

			<h3>Passenger Details</h3>
			<li>Passenger: ${pass}</li>

			<form action="/Airport/booking/confirmReservation" method="post">
				<input type="hidden" name="fNumber" value="${fNumber}"> <input
					type="hidden" name="seats" value="${sedista}">
				<button type="submit" class="btn btn-success">Confirm
					Reservation</button>
			</form>
		</div>
	</section>

	<footer class="bg-info text-white text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
