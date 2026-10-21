<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Allocate</li>
	</ol>
</nav>

<div class="card col-8">
	<h5 class="card-header" style="background-color: cadetblue;">Allocate</h5>
	<div class="card-body" style="background-color: mintcream;">
	<b><%@ include file="businessMessage.jsp"%></b>
	<br>
		<sf:form method="post" class="row g-3"
			action="${pageContext.request.contextPath}/ctl/allocate"  enctype="multipart/form-data"
			modelAttribute="form">
<sf:hidden path="id" />
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">First Name</label>
					<s:bind path="firstName">
						<sf:input path="${status.expression}"
							placeholder="Enter First Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Last Name</label>
					<s:bind path="lastName">
						<sf:input path="${status.expression}"
							placeholder="Enter last Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Email</label>
					<s:bind path="email">
						<sf:input path="${status.expression}"
							placeholder="Enter Email" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Mobile No</label>
					<s:bind path="mobileNo">
						<sf:input path="${status.expression}"
							placeholder="Enter Mobile No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Date Of Birth</label>
					<s:bind path="dob">
						<sf:input path="${status.expression}"
							placeholder="Enter Date Of birth" id="datepicker" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Total Member</label>
					<s:bind path="totalMember">
						<sf:input path="${status.expression}"
							placeholder="Enter Total Member" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Society Name</label>
					<s:bind path="societyId">
						<sf:select style="margin-bottom: 10px" class="form-control"
								path="${status.expression}">
								<sf:option value="-1" label="Select" />
								<sf:options itemLabel="name" itemValue="id" items="${societyList}" />
							</sf:select>
							<font color="red"><sf:errors path="${status.expression}"
									cssClass="help-block" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">House Name</label>
					<s:bind path="houseId">
						<sf:select style="margin-bottom: 10px" class="form-control"
								path="${status.expression}">
								<sf:option value="-1" label="Select" />
								<sf:options itemLabel="name" itemValue="id" items="${houseList}" />
							</sf:select>
							<font color="red"><sf:errors path="${status.expression}"
									cssClass="help-block" /></font>
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
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">UserName</label>
					<s:bind path="userName">
						<sf:input path="${status.expression}"
							placeholder="Enter User Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Password</label>
					<s:bind path="password">
						<sf:input type="password" path="${status.expression}"
							placeholder="Enter Password" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
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
