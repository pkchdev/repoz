<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<div class="panel-title text-center">All cars
					(${fn:length(cars)})</div>
			</div>

			<div class="panel-body">
				<c:if test="${fn:length(cars) == 0}">
					<h4 class="text-center">No car available</h4>
				</c:if>
				<c:forEach items="${cars}" var="car">
					<div class="media">
						<div class="media-left top-middle">
							<img class="media-object" src="/images/empty-image.gif"
								height="64px" width="64px" alt="/images/empty-image.gif">
						</div>

						<div class="media-body">
							<h4 class="media-heading">${car.maker}${car.model}</h4>
							<p style="word-break: break-all;">
								<strong>${car.date} :</strong>${car.description}</p>

							<button class="btn btn-danger pull-right" aria-label="Left Align"
								data-toggle="modal" data-target="#modalConfirmDelete${car.id}">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								Delete
							</button>

							<!-- Modal -->
							<div id="modalConfirmDelete${car.id}" class="modal fade"
								role="dialog">
								<div class="modal-dialog modal-sm">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Are you sure ?</h4>
										</div>
										<div class="modal-body">
											<p>Delete cars ${car.model} ${car.maker} ?</p>
											<form:form action="/cars/delete" modelAttribute="id">
												<input type="hidden" name="id" value="${car.id}" />

												<button type="submit" class="btn btn-danger  btn-block"
													aria-label="Left Align">
													<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
													Yes, delete !
												</button>

											</form:form>
										</div>

									</div>

								</div>
							</div>
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


					<button class="btn btn-lg btn-primary btn-block" type="submit">Add
						car</button>

					<form:errors path="*" element="div" cssClass="alert alert-danger"></form:errors>

				</form:form>
			</div>
		</div>
	</div>


	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

	<script>
		function popup() {
			var txt;
			if (confirm("Press a button!") == true) {
				txt = "Delete this car ?";
			} else {
				txt = "You pressed Cancel!";
			}
			document.getElementById("demo").innerHTML = txt;
		}
	</script>

</body>
</html>
