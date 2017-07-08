<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
	<title>Registration Repoz</title>
	<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
	<link rel='stylesheet' type="text/css" href='/css/repoz.css'>
</head>

<body>
	<div class="container">

		<div id="loginbox" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
			<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title text-center">Registration Repoz</div>
				</div>

				<div id="loginbox-panel" class="panel-body">
					<form:form method="POST" modelAttribute="user" name="form" id="form" class="form-horizontal">
						
						<spring:bind path="username">
							<div class="input-group">
							  	<span class="input-group-addon">
							  		<i class="glyphicon glyphicon-user"></i>
							  	</span>
							  	<form:input  type="text" path="username" class="form-control  ${empty username ? 'has-error' : '' } " placeholder="Username" autofocus="true"></form:input>
							</div>
						</spring:bind>
					
						<spring:bind path="password">
							<div class="input-group">
							  	<span class="input-group-addon">
							  		<i class="glyphicon glyphicon-lock"></i>
							  	</span>
								<form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
							</div>
						</spring:bind>
					
						<spring:bind path="passwordConfirm">
							<div class="input-group">
							  	<span class="input-group-addon">
							  		<i class="glyphicon glyphicon-lock"></i>
							  	</span>
								<form:input type="password" path="passwordConfirm" class="form-control" placeholder="Confirm your password"></form:input>
							</div>
						</spring:bind>
					
						<button id="loginbox-button" class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
						<a id="loginbox-button" class="btn btn-lg btn-warning btn-block" href="/login">Login page</a>
						
						<form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>

					</form:form>		
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>