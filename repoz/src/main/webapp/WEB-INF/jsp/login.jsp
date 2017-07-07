<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Repoz login</title>
<link rel='stylesheet' type="text/css" href='/css/style.css'>
</head>
<body>

	<div class="container">


		<div id="login-box">
			<div class="logo">
				<img src="http://lorempixel.com/output/people-q-c-100-100-1.jpg"
					class="img img-responsive img-circle center-block" />
				<h1 class="logo-caption">
					<span class="tweak">L</span>ogin
				</h1>
			</div>
			<!-- /.logo -->
			 <form method="POST" action="/login" class="form-signin">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span> <input name="username" type="text"
					class="form-control" placeholder="Username" /> <input
					name="password" type="password" class="form-control"
					placeholder="Password" /> <span>${error}</span> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				<button class="btn btn-lg btn-primary btn-block" type="submit">Log
					In</button>
				<h4 class="text-center">
					<a href="/registration">Create an account</a>
				</h4>
			</div>

		</form>
			<!-- <div class="controls">
				<input type="text" name="username" placeholder="Username"
					class="form-control" /> <input type="text" name="username"
					placeholder="Password" class="form-control" />
				<button type="button" class="btn btn-default btn-block btn-custom">Login</button>
			</div> -->
			<!-- /.controls -->
		</div>
		<!-- /#login-box -->

		<div id="particles-js"></div>
		<!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>-->
		<%-- <form method="POST" action="/login" class="form-signin">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span> <input name="username" type="text"
					class="form-control" placeholder="Username" /> <input
					name="password" type="password" class="form-control"
					placeholder="Password" /> <span>${error}</span> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				<button class="btn btn-lg btn-primary btn-block" type="submit">Log
					In</button>
				<h4 class="text-center">
					<a href="/registration">Create an account</a>
				</h4>
			</div>

		</form> --%>

	</div>






	<!--     	<c:if test="${param.error ne null}">
        	<div>Invalid username and password.</div>
     	</c:if>
        <c:if test="${param.logout ne null}">
        	<div>You have been logged out.</div>
      	</c:if>
		<form action="/login" method="post">
          	<div><label> User Name : <input type="text" name="username"/> </label></div>
          	<div><label> Password: <input type="password" name="password"/> </label></div>
          	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          	<div><input type="submit" value="Sign In"/></div>
      	</form>
      	-->

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
	<script src="/js/test.js"></script>
</body>

</html>