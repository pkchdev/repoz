<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta charset="utf-8">
<title>Repoz cars</title>
<link rel='stylesheet' type="text/css"
	href='/webjars/bootstrap/css/bootstrap.min.css'>
<link rel='stylesheet' type="text/css" href='/css/repoz.css'>
</head>
<body style="padding-top: 50px; padding-bottom: -20px;">
	<div id="header" style="margin-bottom: 50px;">
		<jsp:include page="common/header.jsp" />
	</div>

	<div class="col-md-8 col-sm-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title text-center">All cars (${fn:length(cars)})</div>
			</div>

			<div class="panel-body">
				<c:forEach items="${cars}" var="car">
					<div class="media">
						<div class="media-left top-middle">
							<a href="#"> <img class="media-object"
								src="/images/background.png" height="64px" width="64px"
								alt="..."></a>
						</div>
						<div class="media-body">
							<h4 class="media-heading">${car.maker} - ${car.model}</h4>
							${car.date} : ${car.description}
						</div>



					</div>
				</c:forEach>
			</div>
		</div>
	</div>



	<div class="col-md-4 col-sm-4">
		<div class="panel panel-default">

			<div class="panel-heading">
				<div class="panel-title text-center">Create new car</div>
			</div>

			<div class="panel-body">

				<form:form method="POST" modelAttribute="car" name="form" id="form"
					class="form-horizontal">


					<spring:bind path="maker">
						<div class="input-group ${status.error ? 'has-error' : ''} ">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-wrench"></i>
							</span>
							<form:input type="text" path="maker" required="required"
								class="form-control  ${empty maker ? 'has-error' : '' } "
								placeholder="Maker" autofocus="true"></form:input>
						</div>
					</spring:bind>

					<spring:bind path="model">
						<div class="input-group ${status.error ? 'has-error' : ''} ">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-tag"></i>
							</span>
							<form:input type="text" path="model" required="required"
								class="form-control  ${empty model ? 'has-error' : '' } "
								placeholder="Model"></form:input>
						</div>
					</spring:bind>

					<spring:bind path="date">
						<div class="input-group ${status.error ? 'has-error' : ''} ">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-calendar"></i>
							</span>
							<form:input type="date" path="date" required="required"
								class="form-control  ${empty model ? 'has-error' : '' } "></form:input>
						</div>
					</spring:bind>


					<spring:bind path="description">
						<form:textarea type="text" path="description" required="required"
							rows="5" maxlength="500"
							class="form-control ${empty description ? 'has-error' : '' } "
							placeholder="Description"></form:textarea>
					</spring:bind>

					<button id="loginbox-button"
						class="btn btn-lg btn-primary btn-block" type="submit">Add
						car</button>

					<form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>

				</form:form>
			</div>
		</div>
	</div>


	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
