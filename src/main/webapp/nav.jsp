<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<style>
nav {
	background-color: #1a1a1a; /* Dark Gray */
	color: #fff;
	padding: 10px 0;
	display: flex;
	justify-content: space-around;
}

nav ul {
	list-style: none;
	padding: 0;
	margin: 0;
	display: flex;
}

nav ul li {
	margin: 0 20px;
}

nav a {
	text-decoration: none;
	color: #ffff;
	font-weight: 500;
	transition: color 0.3s ease-in-out;
}

nav a:hover {
	color: #ffcc29;
}

/* Responsive styles */
@media ( max-width : 767px) {
	nav {
		flex-direction: column;
		align-items: center;
	}
	nav ul {
		margin-top: 10px;
	}
	nav ul li {
		margin: 10px 0; /* Adjusted margin for smaller screens */
	}
}
</style>

<ul class="nav">
	<li class="nav-item"><a class="nav-link" href="/Airport/">Home</a></li>

	<li class="nav-item"><a class="nav-link"
		href="/Airport/booking/getData">Book Flight</a></li>

	<sec:authorize access="!isAuthenticated()">
		<li class="nav-item"><a class="nav-link" href="/Airport/log">Login</a></li>
		<li class="nav-item"><a class="nav-link" href="/Airport/register">Register</a></li>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<li class="nav-item"><a class="nav-link"
			href="/Airport/user/my-account"> <sec:authentication
					property="principal.user.name" /> <sec:authentication
					property="principal.user.surname" />
		</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/Airport/user/my-flights">My Flights</a></li>

		<sec:authorize access="hasAnyRole('ADMIN','AVIOCOMPANY')">
			<li class="nav-item"><a class="nav-link"
				href="/Airport/avio/details">Airline details</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/Airport/avio/rent">Rent</a></li>
		</sec:authorize>
		<li class="nav-item"><a class="nav-link" href ="/Airport/logout">Logout</a></li>
	</sec:authorize>
</ul>