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
<title>My Account</title>
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
<sec:authentication var="pic" property="principal.user.profPicture" />
</head>
<body style="margin: auto;">

	<header class="text-center py-3">
		<h1>My Account</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>

	<section class="container mt-4">
		<div class="account-info">
			<h2>
				Welcome,
				<sec:authentication var="principal" property="principal" />
				<c:choose>
					<c:when
						test="${not empty principal and not empty principal.user and not empty principal.user.profPicture}">
						<sec:authentication property="principal.user.name" />
						
						<img alt="<sec:authentication property="principal.user.name" />"
							src="data:image/*;base64, ${encoder.encode(pic)}"
							style="width: 150px; height: 150px; border: 2px solid #000; text-align: center;">
					</c:when>
					<c:otherwise>
						<sec:authentication property="principal.user.name" />
						<!-- Add a default image if profPicture is null -->
						<img alt="<sec:authentication property="principal.user.name" />"
							src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
							style="width: 150px; height: 150px; border: 2px solid #000; text-align: center;">
					</c:otherwise>
				</c:choose>
			</h2>
			<form action="/Airport/user/change-profile-picture" method="get"
					style="text-align: center;">
					<button type="submit" class="btn btn-primary">Change
						Picture</button>
				</form>
			<p>Your account details:</p>
			<ul>
				<li><strong>Email:</strong> <sec:authentication
						property="principal.user.email" /></li>
				<li><strong>Username:</strong> <sec:authentication
						property="principal.user.username" /></li>
				<li><strong>Phone:</strong> <sec:authentication
						property="principal.user.phone" /></li>
				<li><strong>Country:</strong> <sec:authentication
						property="principal.user.drzavaBean.name" /></li>
			</ul>
		</div>
	</section>
	<section class="container mt-4">
		<div class="account-info">
			<p>Change your details:</p>
			<c:if test="${not empty msg}">
				<p>${msg }
			</c:if>
			<div class="row">
				<div class="col-md-6 mb-3">
					<a href="/Airport/user/change-email">
						<button type="button" class="btn btn-primary btn-block">Change
							Email</button>
					</a>
				</div>
				<div class="col-md-6 mb-3">
					<a href="/Airport/user/change-username">
						<button type="button" class="btn btn-primary btn-block">Change
							Username</button>
					</a>
				</div>
				<div class="col-md-6 mb-3">
					<a href="/Airport/user/change-password">
						<button type="button" class="btn btn-primary btn-block">Change
							Password</button>
					</a>
				</div>
				<div class="col-md-6 mb-3">
					<a href="/Airport/user/change-phone">
						<button type="button" class="btn btn-primary btn-block">Change
							Phone</button>
					</a>
				</div>
				<div class="col-md-6 mb-3">
					<a href="/Airport/user/change-name">
						<button type="button" class="btn btn-primary btn-block">Change
							First Name</button>
					</a>
				</div>
				<div class="col-md-6 mb-3">
					<a href="/Airport/user/change-surname">
						<button type="button" class="btn btn-primary btn-block">Change
							Last Name</button>
					</a>
				</div>
			</div>
		</div>
	</section>




	<footer class="text-center py-2">
		<p>&copy; 2023 Flight Booking</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
