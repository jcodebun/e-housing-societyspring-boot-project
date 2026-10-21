<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/complain" />

<c:url var="addSearch" value="/ctl/complain/search" />

<c:url var="editUrl" value="/ctl/complain?id=" />


<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Complain
			List</li>
	</ol>

	<a href="${pageContext.request.contextPath}/ctl/complain"
		class="btn btn-primary btn-sm">Add Complain</a>

</nav>
<br>
<div class="card">
	<h5 class="card-header" style="background-color: cadetblue;">Complain
		Report</h5>
	<div class="card-body">

		<b><%@ include file="businessMessage.jsp"%></b>

		<sf:form method="post"
			action="${pageContext.request.contextPath}/ctl/complain/search"
			modelAttribute="form">
			<div class="row g-3">
				<s:bind path="status">
					<div class="col-sm">
						<sf:select class="form-control" path="${status.expression}">
							<sf:option value="" label="Select" />
							<sf:options items="${statusType}" />
						</sf:select>
					</div>
				</s:bind>

				<div class="col-sm">
					<input type="submit" name="operation" value="Search"
						class="btn btn-primary" />
				</div>
			</div>
			<br>

			<sf:input type="hidden" path="pageNo" />
			<sf:input type="hidden" path="pageSize" />

			<sf:input type="hidden" path="listsize" />
			<sf:input type="hidden" path="total" />
			<sf:input type="hidden" path="pagenosize" />

			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Subject</th>
						<th scope="col">Complain</th>
						<th scope="col">Status</th>
						<c:if test="${sessionScope.user.roleId ==1}">
							<th scope="col">Action</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="comp" varStatus="complain">
						<tr>
							<th scope="row">${complain.index+1}</th>
							<td>${comp.subject}</td>
							<td>${comp.complain}</td>
							<td>${comp.status}</td>
							<c:if test="${sessionScope.user.roleId ==1}">
								<td><a href="${editUrl} ${comp.id}"
									class="btn  btn-primary btn-sm"><i class="fa fa-pencil"
										aria-hidden="true"></i>Edit</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row g-3">
				<div class="col-sm-1">

					<input type="submit" name="operation"
						class="btn  btn-primary btn-sm"
						<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
						value="Previous">


				</div>

				<div class="col-sm-4"></div>




				<div class="col-sm-4"></div>
				<div class="col-sm-1">
					<input type="submit" name="operation"
						class="btn  btn-primary btn-sm"
						<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
						value="Next">
				</div>
			</div>

		</sf:form>
	</div>
</div>
