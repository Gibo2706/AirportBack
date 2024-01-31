<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rent Airplanes</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
</head>
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
<body>
	<header class="text-center py-3">
		<h1>Rent Plane</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>
	<div class="container mt-4">
		<h1>Rent Airplanes</h1>
		<c:if test="${not empty msg}">
			<p>${msg }
		</c:if>
		<div class="rental-form">
			<form action="/Airport/avio/rentPlane" method="post">
				<div class="mb-3">
					<label for="airplane">Select Airplane:</label> <select
						class="form-select" id="airplane" name="tailNumber">
						<c:forEach var="airplane" items="${airplaneList}">
							<c:if test="${airplane.aviokompanija2 == null}">
								<option value="${airplane.tailNumber}">${airplane.tailNumber}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="mb-3">
					<label for="renter">Renter's email:</label> <input type="text"
						class="form-control" id="renter" name="renter">
				</div>
				<button type="submit" class="btn btn-primary">Rent</button>
			</form>
		</div>
	</div>
	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

</body>
</html>
