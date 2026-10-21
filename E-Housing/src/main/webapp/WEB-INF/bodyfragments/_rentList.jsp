<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/rent" />

<c:url var="addSearch" value="/ctl/rent/search" />

<c:url var="editUrl" value="/message?rid=" />


<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Rent House
			List</li>
	</ol>
</nav>

<div class="card">
	<h5 class="card-header" style="background-color: cadetblue;">Rent
		House Report</h5>
	<div class="card-body">

		<b><%@ include file="businessMessage.jsp"%></b>

		<sf:form method="post"
			action="${pageContext.request.contextPath}/ctl/rent/search"
			modelAttribute="form">


			<sf:input type="hidden" path="pageNo" />
			<sf:input type="hidden" path="pageSize" />

			<sf:input type="hidden" path="listsize" />
			<sf:input type="hidden" path="total" />
			<sf:input type="hidden" path="pagenosize" />

			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Society Name</th>
						<th scope="col">House Name</th>
						<th scope="col">Owner Name</th>
						<th scope="col">Sell Price</th>
						<th scope="col">House Type</th>
						<th scope="col">Block No</th>
						<c:if test="${sessionScope.user.roleId !=1}">
							<th scope="col">Send Message</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="renn" varStatus="rentt">
						<tr>

							<th scope="row">${rentt.index+1}</th>
							<td>${renn.house.society.name}</td>
							<td>${renn.house.name}</td>
							<td>${renn.allocate.firstName}${sel.allocate.lastName}</td>
							<td>${renn.price}</td>
							<td>${renn.house.houseType}</td>
							<td>${renn.house.blockNo}</td>
							<c:if test="${sessionScope.user.roleId !=1}">
								<td><a href="${editUrl} ${renn.id}"
									class="btn  btn-primary btn-sm"><i class="fa fa-pencil"
										aria-hidden="true"></i>Add Message</a></td>
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
