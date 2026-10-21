<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Complain</li>
	</ol>
</nav>

<div class="card col-8">
	<h5 class="card-header" style="background-color: cadetblue;">Complain</h5>
	<div class="card-body" style="background-color: mintcream;">
	<b><%@ include file="businessMessage.jsp"%></b>
	<br>
		<sf:form method="post" class="row g-3"
			action="${pageContext.request.contextPath}/ctl/complain" 
			modelAttribute="form">
<sf:hidden path="id" />
			
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Subject</label>
					<s:bind path="subject">
						<sf:input path="${status.expression}"
							placeholder="Enter Subject" class="form-control" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>


			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Complain</label>
					<s:bind path="complain">
						<sf:textarea path="${status.expression}" placeholder="Enter Complain"
							class="form-control" rows="3" cols="4" />
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			
			<c:if test="${sessionScope.user.roleId ==1}">
			<div class="row">
				<div class="col-md-8">
					<label for="inputEmail4" class="form-label">Status</label>
					<s:bind path="status">
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="Select" />
									<sf:options   items="${statusType}" />
								</sf:select>
								<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
					</s:bind>
				</div>
			</div>
			</c:if>
			
			<div class="col-12">
				<input type="submit" class="btn btn-primary" name="operation"
					value="Save" />or <input type="submit" class="btn btn-primary"
					name="operation" value="Reset" />
			</div>
		</sf:form>
	</div>
</div>
