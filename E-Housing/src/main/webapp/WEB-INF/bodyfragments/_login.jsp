<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="container">
	<div class="row">
		<div class="col-sm-4">
			<div class="card">
				<h5 class="card-header" style="background-color: cadetblue;">Login</h5>
				<div class="card-body">
				<b><%@ include file="businessMessage.jsp"%></b>
					<sf:form method="post"
						action="${pageContext.request.contextPath}/login"
						modelAttribute="form">

						<div class="mb-3">
							<s:bind path="login">
								<label for="exampleInputEmail1" class="form-label">Login Id:</label>
								<sf:input path="${status.expression}" placeholder="Enter Login"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<div class="mb-3">
							<s:bind path="password">
									<label for="pwd">Password:</label>
									<sf:input type="password" path="${status.expression}"
									placeholder="Enter Password" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors path="${status.expression}"/></font>
								</s:bind>
						</div>
						<div class="mb-3 ">
								<input  type="submit" name="operation" class="btn btn-primary pull-right"
								value="SignIn">
								
						</div>
					</sf:form>
				</div>
			</div>
		</div>
		<div class="col-sm-8" style="height: 30%">
		<div class="col-sm">
			<div class="card">
				<h5 class="card-header">Society List</h5>
				<div class="card-body">
					<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="soc" varStatus="society">
						<tr>
							<th scope="row">${society.index+1}</th>
							<td>${soc.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
				</div>
			</div>
		</div>

	</div>
	</div>
	<br>
	<div class="row">
		<div id="carouselExampleControls" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img
							src="${pageContext.request.contextPath}/resources/images/Housing-Society-Bye-laws-and-Member-Rights.jpg"
							height="" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img
							src="${pageContext.request.contextPath}/resources/images/Co-operative-Housing-Societies-in-Maharashtra-now-be-investing-in-mutual-funds-shares.jpg"
							class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img
							src="${pageContext.request.contextPath}/resources/images/hires.jpg"
							class="d-block w-100" alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
</div>


