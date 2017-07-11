<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Repoz Login</title>
<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
<link rel='stylesheet' type="text/css" href='/css/repoz.css'>
</head>
<body >
	<div class="container" >

		<div id="loginbox" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
			<div class="panel panel-default">

				<div class="panel-heading">
					<div class="panel-title text-center">Repoz</div>
				</div>

				<div class="panel-body">
					<form name="form" id="form" class="form-horizontal" action="/login" method="POST">
						<div class="input-group ${not empty error ? 'has-error' : ''}">
						  	<span class="input-group-addon">
						  		<i class="glyphicon glyphicon-user"></i>
						  	</span>
							<input name="username" type="text" class="form-control" placeholder="Username" required="required"/>
						</div>
						<div class="input-group ${not empty error ? 'has-error' : ''}">
						  	<span class="input-group-addon">
						  		<i class="glyphicon glyphicon-lock"></i>
						  	</span>
							<input name="password" type="password" class="form-control" placeholder="Password" required="required" />
						</div>

						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
						<button id="loginbox-button" class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
						<a id="loginbox-button" class="btn btn-lg btn-warning btn-block" href="/registration" >Create an account</a>
						
						<c:if test = "${error != null}">
							<div id="loginbox-alert" class="alert alert-danger" role="alert">${error}</div>
						</c:if>
						
						<c:if test = "${message != null}">
							<div id="loginbox-alert" class="alert alert-success" role="alert">${message}</div>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/js/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>