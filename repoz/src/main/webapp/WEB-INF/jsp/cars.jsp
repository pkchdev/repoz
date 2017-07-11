<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
	<head>
		<meta charset="utf-8">
		<title>Repoz cars</title> 
		<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
		
	</head>
	<body style="padding-top: 50px; padding-bottom: -20px;">
		<div id="header">
    		<jsp:include page="common/header.jsp"/>
		</div>
		
		<h2>Add new car</h2>
		<form:form method="POST" modelAttribute="car" name="form" id="form" class="form-horizontal">


		<spring:bind path="maker">
			<div class="input-group ${status.error ? 'has-error' : ''} ">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-user"></i>
				</span>
				<form:input type="text" path="maker" required="required"
					class="form-control  ${empty maker ? 'has-error' : '' } "
					placeholder="Maker" autofocus="true"></form:input>
			</div>
		</spring:bind>

		<spring:bind path="model">
			<div class="input-group ${status.error ? 'has-error' : ''} ">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-lock"></i>
				</span>
				<form:input type="text" path="model" required="required"
					class="form-control  ${empty model ? 'has-error' : '' } "
					placeholder="Model"></form:input>
			</div>
		</spring:bind>

		<spring:bind path="date">
			<div class="input-group ${status.error ? 'has-error' : ''} ">
				<span class="input-group-addon"> <i
					class="glyphicon glyphicon-clock"></i>
				</span>
				<form:input type="date" path="date" required="required" 
					class="form-control  ${empty model ? 'has-error' : '' } "
					></form:input>
			</div>
		</spring:bind>

		<button id="loginbox-button" class="btn btn-lg btn-primary btn-block"
			type="submit">Add car</button>
	
		<form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>

	</form:form>
		
		
				
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
