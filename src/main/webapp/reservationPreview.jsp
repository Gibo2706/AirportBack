<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Booking - Ticket Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	text-align: center;
	margin: 50px;
}

.ticket-info {
	border: 1px solid #ddd;
	padding: 20px;
	border-radius: 8px;
	margin-bottom: 20px;
}

h2 {
	color: #007bff;
}

button {
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="container">
		<c:forEach items="${karte}" var="karta">
			<div class="ticket-info">
				<h2>Ticket Information</h2>
				<p>
					<strong>Ticket ID:</strong> ${karta.id }
				</p>
				<p>
					<strong>Passenger Name:</strong> ${karta.korisnikBean.name }
				</p>
			</div>
		</c:forEach>
		<form action="/Airport/" method="get">
			<button type="submit" class="btn btn-primary">Back to Main
				Page</button>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
