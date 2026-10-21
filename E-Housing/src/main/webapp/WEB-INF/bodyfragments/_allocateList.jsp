<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/allocate" />

<c:url var="addSearch" value="/ctl/allocate/search" />

<c:url var="editUrl" value="/ctl/allocate?id=" />


<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Allocate 
			List</li>
	</ol>
</nav>

<div class="card">
	<h5 class="card-header" style="background-color: cadetblue;">Allocate
		Report</h5>
	<div class="card-body">

		<b><%@ include file="businessMessage.jsp"%></b>

		<sf:form method="post"
			action="${pageContext.request.contextPath}/ctl/allocate/search"
			modelAttribute="form">
			<div class="row g-3">
				<s:bind path="firstName">
					<div class="col-sm">
						<sf:input path="${status.expression}" class="form-control"
							placeholder="First Name" />
					</div>
				</s:bind>
				<s:bind path="email">
					<div class="col-sm">
						<sf:input path="${status.expression}" class="form-control"
							placeholder="Email" />
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
						<th scope="col">Select</th>
						<th scope="col">#</th>
						<th scope="col">Image</th>
						<th scope="col">Name</th>
						<th scope="col">Email</th>
						<th scope="col">Mobile</th>
						<th scope="col">Members</th>
						<th scope="col">Society</th>
						<th scope="col">House</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="act" varStatus="allocate">
						<tr>
							<td><input type="checkbox" class="case" name="ids"
								value="${act.id}"></td>
							<th scope="row">${allocate.index+1}</th>
							<td><img src="${pageContext.request.contextPath}/ctl/allocate/getImage/${act.id}"  width="120px;" height="100px"/></td>
							<td>${act.firstName} ${act.lastName}</td>
							<td>${act.email}</td>
							<td>${act.mobileNo}</td>
							<td>${act.totalMember}</td>
							<td>${act.society.name}</td>
							<td>${act.house.name}</td>
							<td><a href="${editUrl} ${act.id}"
								class="btn  btn-primary btn-sm"><i class="fa fa-pencil"
									aria-hidden="true"></i>Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row g-3">
				<div class="col-sm-1">

					<input type="submit" name="operation" class="btn  btn-primary btn-sm"
						<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
						value="Previous">


				</div>
				
				<div class="col-sm-4">
				</div>
				<div class="col-sm-1">
					<input type="submit" name="operation"
						<c:if test="${listsize== 0}">disabled="disabled"</c:if>
						class="btn  btn-danger btn-sm" value="Delete">
				</div>

				<div class="col-sm-1">
					<input type="submit" name="operation" class="btn  btn-primary btn-sm"
						class="btn  btn-danger btn-sm" value="New">
				</div>

				<div class="col-sm-4">
				</div>
				<div class="col-sm-1">
					<input type="submit" name="operation" class="btn  btn-primary btn-sm"
						<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
						value="Next">
				</div>
			</div>

		</sf:form>
	</div>
</div>
