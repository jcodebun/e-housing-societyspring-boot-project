<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">House</li>
	</ol>
</nav>

<div class="card col-8">
	<h5 class="card-header" style="background-color: cadetblue;">House</h5>
	<div class="card-body" style="background-color: mintcream;">
	<b><%@ include file="businessMessage.jsp"%></b>
	<br>
		<sf:form method="post" class="row g-3"
			action="${pageContext.request.contextPath}/ctl/house" 
			modelAttribute="form">
<sf:hidden path="id" />
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
					<s:bind path="name">
						<sf:input path="${status.expression}"
							placeholder="Enter House Name" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Block No</label>
					<s:bind path="blockNo">
						<sf:input path="${status.expression}"
							placeholder="Enter Block No" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">House Type</label>
					<s:bind path="houseType">
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="Select" />
									<sf:options   items="${houseType}" />
								</sf:select>
								<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>

			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Detail</label>
					<s:bind path="detail">
						<sf:textarea path="${status.expression}" placeholder="Enter Detail"
							class="form-control" rows="3" cols="4" />
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
