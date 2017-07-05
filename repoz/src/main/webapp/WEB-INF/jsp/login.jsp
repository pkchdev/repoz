<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Repoz login</title>
</head>
<body>

	<div class="container">

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
</body>

</html>