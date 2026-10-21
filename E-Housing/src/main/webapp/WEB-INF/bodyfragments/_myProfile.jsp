<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">My Profile</li>
	</ol>
</nav>

<div class="card col-8">
	<h5 class="card-header" style="background-color: cadetblue;">My Profile</h5>
	<div class="card-body" style="background-color: mintcream;">
	<b><%@ include file="businessMessage.jsp"%></b>
	<br>
		<sf:form method="post" class="row g-3"
			action="${pageContext.request.contextPath}/profile" 
			modelAttribute="form">

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
							placeholder="Enter Last Name" class="form-control" />
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
					<label for="inputEmail4" class="form-label">Mobile</label>
					<s:bind path="mobileNo">
						<sf:input path="${status.expression}"
							placeholder="Enter Mobile No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>

			
			<div class="col-12">
				<input type="submit" class="btn btn-primary" name="operation"
					value="Save" />
			</div>
		</sf:form>
	</div>
</div>
