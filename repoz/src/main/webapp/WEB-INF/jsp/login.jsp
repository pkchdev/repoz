<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Repoz login</title>
    </head>
    <body>
    	<c:if test="${param.error ne null}">
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
    </body>
</html>