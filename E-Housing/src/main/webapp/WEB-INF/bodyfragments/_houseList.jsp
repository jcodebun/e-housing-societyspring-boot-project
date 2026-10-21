<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/house" />

<c:url var="addSearch" value="/ctl/house/search" />

<c:url var="editUrl" value="/ctl/house?id=" />


<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">House
			List</li>
	</ol>
</nav>

<div class="card">
	<h5 class="card-header" style="background-color: cadetblue;">House
		Report</h5>
	<div class="card-body">

		<b><%@ include file="businessMessage.jsp"%></b>

		<sf:form method="post"
			action="${pageContext.request.contextPath}/ctl/house/search"
			modelAttribute="form">
			<div class="row g-3">
				<s:bind path="name">
					<div class="col-sm">
						<sf:input path="${status.expression}" class="form-control"
							placeholder="Name" />
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
						<th scope="col">Society Name</th>
						<th scope="col">Name</th>
						<th scope="col">Block No</th>
						<th scope="col">House Type</th>
						<th scope="col">Detail</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="hos" varStatus="house">
						<tr>
							<td><input type="checkbox" class="case" name="ids"
								value="${hos.id}"></td>
							<th scope="row">${house.index+1}</th>
								<td>${hos.society.name}</td>
							<td>${hos.name}</td>
							<td>${hos.blockNo}</td>
							<td>${hos.houseType}</td>
							<td>${hos.detail}</td>
							<td><a href="${editUrl} ${hos.id}"
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
