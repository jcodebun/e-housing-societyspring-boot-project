<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Society</li>
	</ol>
</nav>

<div class="card col-8">
	<h5 class="card-header" style="background-color: cadetblue;">Society</h5>
	<div class="card-body" style="background-color: mintcream;">
	<b><%@ include file="businessMessage.jsp"%></b>
	<br>
		<sf:form method="post" class="row g-3"
			action="${pageContext.request.contextPath}/ctl/society" enctype="multipart/form-data"
			modelAttribute="form">
			<sf:hidden path="id"/>
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Society Name</label>
					<s:bind path="name">
						<sf:input path="${status.expression}"
							placeholder="Enter Society Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">No Of House</label>
					<s:bind path="noOfHouse">
						<sf:input path="${status.expression}"
							placeholder="Enter No of House" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">City</label>
					<s:bind path="city">
						<sf:input path="${status.expression}" placeholder="Enter City"
							class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Pin Code</label>
					<s:bind path="pincode">
						<sf:input path="${status.expression}" placeholder="Enter PinCode"
							class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Address</label>
					<s:bind path="address">
						<sf:textarea path="${status.expression}" placeholder="Enter PinCode"
							class="form-control" rows="4" cols="4" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
				<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Image</label>
					<s:bind path="image">
						<input name="image" type="file"
							class="form-control" required="required" />
					</s:bind>
				</div>
			</div>

			<div class="col-12">
				<input type="submit" class="btn btn-primary" name="operation"
					value="Save" />or <input type="submit" class="btn btn-primary"
					name="operation" value="Reset" />
			</div>
		</sf:form>
	</div>
</div>
