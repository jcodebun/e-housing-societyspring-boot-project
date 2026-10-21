<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">House
			Detail</li>
	</ol>
</nav>

<div class="row">
	<div class="col-6">
		<h5 class="card-header" style="background-color: cadetblue;">House
			Detail</h5>
		<div class="card mb-3" style="max-width: 100%;">
			<div class="row g-0">
				<div class="col-md-6">
					<img
						src="${pageContext.request.contextPath}/ctl/society/getImage/${allocate.house.society.id}"
						alt="..." height="200px" width="200px">
				</div>
				<div class="col-md-6">
					<div class="card-body">
						<h5 class="card-title">Block No : ${allocate.house.blockNo}</h5>
						<p class="card-text">House Type : ${allocate.house.houseType}</p>
						<p class="card-text">Detail : ${allocate.house.detail}</p>
						<p class="card-text">Society Name :
							${allocate.house.society.name}</p>
						<p class="card-text">House No : ${allocate.house.name}</p>
						<p class="card-text">Address :
							${allocate.house.society.address}</p>
						<p class="card-text">City : ${allocate.house.society.city}</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-6">
		<h5 class="card-header" style="background-color: cadetblue;">House
			Owner Detail</h5>
		<div class="card mb-3" style="max-width: 100%;">
			<div class="row g-0">
				<div class="col-md-6">
					<img
						src="${pageContext.request.contextPath}/ctl/allocate/getImage/${allocate.id}"
						alt="..." height="200px" width="200px">
				</div>
				<div class="col-md-6">
					<div class="card-body">
						<h5 class="card-title">Name : ${allocate.firstName}
							${allocatelastName}</h5>
						<p class="card-text">Email : ${allocate.email}</p>
						<p class="card-text">Mobile No : ${allocate.mobileNo}</p>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<div class="row">
	<sf:form method="post" 
		action="${pageContext.request.contextPath}/update/society/image"
		enctype="multipart/form-data" modelAttribute="form">
		<div class="col-6">
			<h5 class="card-header" style="background-color: cadetblue;">Update
				House Image</h5>
			<div class="card-body" style="background-color: mintcream;">
				<b><%@ include file="businessMessage.jsp"%></b>
				<div class="row">
					<div class="col-md-8">
						<label for="inputEmail4" class="form-label">Image</label>
						<s:bind path="image">
							<input name="image" type="file" class="form-control"
								required="required" />
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

	<sf:form method="post" 
		action="${pageContext.request.contextPath}/update/allocate/image"
		enctype="multipart/form-data" modelAttribute="form">
		<div class="col-6">
			<h5 class="card-header" style="background-color: cadetblue;">Update
				Owner Image</h5>
			<div class="card-body" style="background-color: mintcream;">
				<b><%@ include file="businessMessage.jsp"%></b>
				<div class="row">
					<div class="col-md-8">
						<label for="inputEmail4" class="form-label">Image</label>
						<s:bind path="image">
							<input name="image" type="file" class="form-control"
								required="required" />
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
</div>

