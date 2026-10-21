<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Change Password</li>
	</ol>
</nav>

<div class="card col-8">
	<h5 class="card-header" style="background-color: cadetblue;">Change Password</h5>
	<div class="card-body" style="background-color: mintcream;">
	<b><%@ include file="businessMessage.jsp"%></b>
	<br>
		<sf:form method="post" class="row g-3"
			action="${pageContext.request.contextPath}/changepassword" 
			modelAttribute="form">

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Old Password</label>
					<s:bind path="oldPassword">
							<sf:input type="password" path="${status.expression}"
								placeholder="Enter Old Password" class="form-control" />
							<font color="red"><sf:errors path="${status.expression}"
									cssClass="help-block" /></font>
						</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">New Password</label>
					<s:bind path="newPassword">
							<sf:input type="password" path="${status.expression}"
								placeholder="Enter New Password" class="form-control" />
							<font color="red"><sf:errors path="${status.expression}"
									cssClass="help-block" /></font>
						</s:bind>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Confirm Password</label>
					<s:bind path="confirmPassword">
							<sf:input type="password" path="${status.expression}"
								placeholder="Enter Confirm Password" class="form-control" />
							<font color="red"><sf:errors path="${status.expression}"
									cssClass="help-block" /></font>
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
