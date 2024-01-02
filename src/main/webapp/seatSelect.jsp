<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Seat Selection</title>
<link rel="stylesheet" type="text/css"
	href="/Airport/css/seatSelect.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
header {
	background-color: #007bff;
	color: #fff;
	padding: 15px 0;
}

section {
	margin-top: 30px;
}

.row-container {
	display: flex;
	flex-wrap: wrap;
}

.seat {
	width: 30px;
	height: 30px;
	margin: 5px;
	background-color: #6c757d; /* Gray color for available seats */
	border: 1px solid #495057;
	border-radius: 5px;
	display: flex;
	align-items: center;
	justify-content: center;
	cursor: pointer;
}

.break {
	flex-basis: 100%;
	height: 0;
}

.seat.selected {
	background-color: #007bff; /* Blue color for selected seats */
	color: #fff;
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
		<h1>Seat Selection</h1>
	</header>

	<section class="container mt-4">
		<p>Select your seat</p>

		<h2>Seat Chart</h2>
		<div id="seatChart" class="row-container">
			<c:if test="${!empty sedista}">
				<c:forEach items="${sedista}" var="s" varStatus="status">
					<c:if test="${status.index - 1 > 0}">
						<c:if test="${sedista.get(status.index - 1).id.row != s.id.row}">
							<div class="break"></div>
						</c:if>
					</c:if>
					<span class="seat">${s.id.row}-${s.id.column}</span>
				</c:forEach>
			</c:if>
		</div>

		<button id="confirmButton" class="btn btn-primary mt-3">Confirm
			Selection</button>
	</section>

	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofEEx4Xl0MeS4SgA6owVIfYItQF2gR8Q2n"
		crossorigin="anonymous"></script>

	<script>
    document.addEventListener("DOMContentLoaded", function() {
        var seats = document.querySelectorAll('.seat');
        var confirmButton = document.getElementById('confirmButton');
        var flightNumber = "${fNumber}"; // Use JSTL to include the flight number

        seats.forEach(function(seat) {
            seat.addEventListener('click', function() {
                seat.classList.toggle('selected');
            });
        });

        confirmButton.addEventListener('click', function() {
            var selectedSeats = document.querySelectorAll('.seat.selected');
            var selectedSeatNumbers = Array.from(selectedSeats).map(seat => seat.textContent);
			
            fetch('/Airport/booking/confirmSelection', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    selectedSeats: selectedSeatNumbers,
                    flightNumber: flightNumber,
                }),
            })
            .then(response => response.text()) // Assuming the response is text (HTML)
            .then(htmlContent => {
                // Replace the current page content with the received HTML content
                document.documentElement.innerHTML = htmlContent;
            })
            .catch(error => {
                console.error('Error:', error);
                // Handle errors as needed
            });
        });
    });
</script>

</body>
</html>
