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
		<h1>My Account</h1>
	</header>
	<nav class="bg-info text-white py-2">
		<div class="container"
			style="display: flex; justify-content: center; align-items: center;">
			<%@include file="nav.jsp"%>
		</div>
	</nav>

	<div class="container mt-4">
		<h1>Change Details</h1>
		<c:if test="${not empty msg}">
			<p>${msg }
		</c:if>
		<c:choose>
			<c:when test="${password}">
				<form action="/Airport/user/change-password-confirm" method="post">
					<div class="mb-3">
						<label for="inputPassword" class="form-label">Password</label> <input
							type="password" class="form-control" id="inputPassword"
							name="inputPassword">
					</div>
					<div class="mb-3">
						<label for="retypePassword" class="form-label">Confirm new
							password</label> <input type="password" class="form-control"
							id="retypePassword" name="retypePassword">
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${email}">
				<form action="/Airport/user/change-email-confirm" method="post">
					<div class="mb-3">
						<label for="inputEmail" class="form-label">Email address</label> <input
							type="email" class="form-control" id="inputEmail"
							aria-describedby="emailHelp" name="inputEmail">
					</div>
					<div class="mb-3">
						<label for="inputEmail" class="form-label">Confirm new
							email address</label> <input type="email" class="form-control"
							id="inputEmail" aria-describedby="emailHelp" name="retypeEmail">
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${username}">
				<form action="/Airport/user/change-username-confirm" method="post">
					<div class="mb-3">
						<label for="inputUsername" class="form-label">Username</label> <input
							type="text" class="form-control" id="inputUsername"
							name="inputUsername">
					</div>
					<div class="mb-3">
						<label for="inputUsername" class="form-label">Confirm new
							username</label> <input type="text" class="form-control"
							id="inputUsername" name="retypeUsername">
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${phone}">
				<form action="/Airport/user/change-phone-confirm" method="post">
					<div class="mb-3">
						<label for="inputPhone" class="form-label">Phone number</label> <input
							type="tel" class="form-control" id="inputPhone" name="inputPhone">
					</div>
					<div class="mb-3">
						<label for="inputPhone" class="form-label">Confirm new
							phone number</label> <input type="tel" class="form-control"
							id="inputPhone" name="retypePhone">
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${name}">
				<form action="/Airport/user/change-name-confirm" method="post">
					<div class="mb-3">
						<label for="inputName" class="form-label">New name</label> <input
							type="text" class="form-control" id="inputName" name="inputName">
					</div>
					<div class="mb-3">
						<label for="retypeName" class="form-label">Confirm new
							name</label> <input type="text" class="form-control" id="retypeName"
							name="retypeName">
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${surname}">
				<form action="/Airport/user/change-surname-confirm" method="post">
					<div class="mb-3">
						<label for="inputSurname" class="form-label">New Last Name</label>
						<input type="text" class="form-control" id="inputSurname"
							name="inputSurname">
					</div>
					<div class="mb-3">
						<label for="retypeSurname" class="form-label">Confirm New
							Last Name</label> <input type="text" class="form-control"
							id="retypeSurname" name="retypeSurname">
					</div>
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</form>
			</c:when>
			<c:when test="${profilePicture}">
				<form action="/Airport/user/change-profile-picture-confirm"
					method="post" enctype="multipart/form-data">
					<div class="mb-3">
						<label for="profilePicture">Choose New Profile Picture (max 50KB):</label> <input
							type="file" id="profilePicture" name="profilePicture"
							accept="image/*">
					</div>
					<button type="submit" class="btn btn-primary">Upload</button>
				</form>
			</c:when>
		</c:choose>
		<form></form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
