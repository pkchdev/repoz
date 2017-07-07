<html>
	<head>
		<meta charset="utf-8">
		<title>Repoz</title> 
		<link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
	</head>
	<body style="padding-top: 50px; padding-bottom: -20px;">
		<div id="header">
    		<jsp:include page="common/header.jsp"/>
		</div>
		
		<h2>Hello ${name} Repoz ;)</h2>
		<form action="/logout" method="post">
           	<div><input type="submit" value="Sign Out"/></div>
           	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       	</form>
       	
       	AAAAA = ${users}
				
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
