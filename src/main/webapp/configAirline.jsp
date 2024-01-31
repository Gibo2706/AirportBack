<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Change Details</title>
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
<body style="margin: auto;">

	<header class="text-center py-3">
		<h1>Airline config</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>

	<div class="container mt-4">
		<h1>Config Airline</h1>
		<c:if test="${not empty msg}">
			<p>${msg }
		</c:if>
		<c:choose>
			<c:when test="${plane}">
				<form action="/Airport/avio/add-plane-confirm" method="post">
					<label class="form-label">Select plane type:</label> <select
						name="tipAviona" class="form-control">
						<c:forEach var="it" items="${tipovi}">
							<option value="${it.id}">${it.naziv}</option>
						</c:forEach>
					</select> <label class="form-label"> Enter plane's tail number: </label><input
						type="text" name="tailNumber" class="form-control"> <br>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${pilot}">
				<form action="/Airport/avio/add-pilot-confirm" method="post">
					<label class="form-label"> Enter pilot's name: </label><input
						type="text" name="name" class="form-control"> <label
						class="form-label"> Enter pilot's last name: </label><input
						type="text" name="lastName" class="form-control"> <br>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${flight}">
				<form action="/Airport/avio/add-flight-confirm" method="post">
					<label class="form-label"> Enter flight number: </label><input
						type="text" name="fNumber" class="form-control"> <label
						class="form-label">Select airplane:</label> <select
						name="airplane" class="form-control">
						<c:forEach var="it" items="${avioni}">
							<option value="${it.tailNumber}">${it.tailNumber}</option>
						</c:forEach>
					</select>
					<div class="mb-3">
						<label for="arrivalAirport" class="form-label">Departure
							Airport</label> <select class="form-select" id="arrivalAirport"
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
						<label for="departureDate" class="form-label">Departure
							Date</label> <input type="datetime-local" class="form-control" id="departureDate"
							name="departureDate" required>
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${seats}">
			<form action="/Airport/avio/add-seats-confirm" method="post">
			<label class="form-label">Select Flight:</label> <select
						name="fNumber" class="form-control">
						<c:forEach var="it" items="${flights}">
							<option value="${it.fNumber}">${it.fNumber} - ${it.aerodrom1.naziv} --> ${it.aerodrom2.naziv}</option>
						</c:forEach>
					</select>
					<label class="form-label"> Enter number of rows: </label><input
						type="number" name="rows" class="form-control"> <label
						class="form-label"> Enter number of columns: </label><input
						type="number" name="columns" class="form-control"> <br>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
		</c:choose>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
