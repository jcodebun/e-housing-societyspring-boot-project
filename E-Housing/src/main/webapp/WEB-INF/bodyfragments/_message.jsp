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
	action="${pageContext.request.contextPath}/message"
	modelAttribute="form">

	<sf:hidden path="id" />
	<sf:hidden path="rent"/>
	<div class="row">
		<div class=" col-6">
			<h5 class="card-header" style="background-color: cadetblue;">House
				Detail</h5>
			<div class="card mb-3" style="max-width: 100%;">
				<div class="row g-0">
					<div class="col-md-6">
						<img
							src="${pageContext.request.contextPath}/ctl/society/getImage/${form.rent.house.society.id}"
							alt="..." height="200px" width="200px">
					</div>
					<div class="col-md-6">
						<div class="card-body">
							<h5 class="card-title">Block No :
								${form.rent.house.blockNo}</h5>
							<p class="card-text">House Type :
								${form.rent.house.houseType}</p>
							<p class="card-text">Detail :
								${form.rent.house.detail}</p>
								<p class="card-text">Society Name :
								${form.rent.house.society.name}</p>	
							<p class="card-text">House No : ${form.rent.house.name}</p>
							<p class="card-text">Address :
								${form.rent.house.society.address}</p>
							<p class="card-text">City :
								${form.rent.house.society.city}</p>
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
							src="${pageContext.request.contextPath}/ctl/allocate/getImage/${form.rent.allocate.id}"
							alt="..." height="200px" width="200px">
					</div>
					<div class="col-md-6">
						<div class="card-body">
							<h5 class="card-title">Name :
								${form.rent.allocate.firstName} ${form.rent.allocate.lastName}</h5>
							<p class="card-text">Email : ${form.rent.allocate.email}</p>
							<p class="card-text">Mobile No :
								${form.rent.allocate.mobileNo}</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="card col-8">
		<h5 class="card-header" style="background-color: cadetblue;">Add
			Message</h5>
		<div class="card-body" style="background-color: mintcream;">
			<b><%@ include file="businessMessage.jsp"%></b>

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Name</label>
					<s:bind path="name">
						<sf:input path="${status.expression}"
							placeholder="Enter  Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Subject</label>
					<s:bind path="subject">
						<sf:input path="${status.expression}"
							placeholder="Enter  Subject" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Contact No</label>
					<s:bind path="contactNo">
						<sf:input path="${status.expression}"
							placeholder="Enter  Contact No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Message</label>
					<s:bind path="message">
						<sf:textarea path="${status.expression}" rows="3" cols="4"
							placeholder="Enter  Message" class="form-control" />
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