<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Student Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<p>hello!!</p>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand">
					Student Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Students</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				
					<form action="insert" method="post">
			
				<c:if test="${student != null}">
					<input type="hidden" name="id"
						value="<c:out value='${student.id}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Student Name</label> <input type="text"
						value="<c:out value='${student.name}' />" class="form-control"
						name="name" required="required">

				</fieldset>
				<fieldset class="form-group">
					<label>Student Email</label> <input type="email"
						value="<c:out value='${student.email}'/>" class="form-control"
						name="email" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Student Standard</label> <input type="text"
						value="<c:out value='${student.standard}'/>" class="form-control"
						name="standard" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Student Gender</label> <input type="text"
						value="<c:out value='${student.gender}'/>" class="form-control"
						name="gender" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
					</form>
				
			</div>
		</div>
	</div>

</body>
</html>
