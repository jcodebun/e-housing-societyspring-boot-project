<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Rent</li>
	</ol>
</nav>

<sf:form method="post"
	action="${pageContext.request.contextPath}/ctl/rentSell/rent"
	modelAttribute="form">

	<sf:hidden path="id" />
	<sf:hidden path="allocate" />
	<sf:hidden path="house" />

	<div class="card col-8">
		<h5 class="card-header" style="background-color: cadetblue;">House
			Detail</h5>
		<div class="card mb-3" style="max-width: 100%;">
			<div class="row g-0">
				<div class="col-md-6">
					<img
						src="${pageContext.request.contextPath}/ctl/society/getImage/${form.allocate.society.id}"
						alt="..." height="200px" width="200px">
				</div>
				<div class="col-md-6">
					<div class="card-body">
						<h5 class="card-title">Society Name :
							${form.allocate.society.name}</h5>
						<p class="card-text">House No : ${form.allocate.house.name}</p>
						<p class="card-text">Block No : ${form.allocate.house.blockNo}</p>
						<p class="card-text">House Type :
							${form.allocate.house.houseType}</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="card col-5">
		<h5 class="card-header" style="background-color: cadetblue;">Add
			To Rent</h5>
		<div class="card-body" style="background-color: mintcream;">
			<b><%@ include file="businessMessage.jsp"%></b>
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Price</label>
					<s:bind path="price">
						<sf:input path="${status.expression}" placeholder="Enter Price"
							class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			<br>
			<div class="col-12">
				<input type="submit" class="btn btn-primary" name="operation"
					value="Save" />
			</div>
		</div>
	</div>
</sf:form>