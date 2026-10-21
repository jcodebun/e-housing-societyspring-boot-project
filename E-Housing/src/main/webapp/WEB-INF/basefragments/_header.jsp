<!------ Include the above in your HEAD tag ---------->

<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!-- Bootstrap NavBar -->
<nav class="navbar navbar-expand-md navbar-light " style="background-color: #0dcaf0">
	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarNavDropdown"
		aria-controls="navbarNavDropdown" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" style="margin-left: 25px" href="#"> <img
		src="${pageContext.request.contextPath}/resources/images/depositphotos_63913959-stock-illustration-wave-in-house-logo.jpg"
		width="30" height="30" class="d-inline-block align-top" alt="">
		<span class="menu-collapsed">E-Housing</span>
	</a>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav ml-auto">
			 <li class="nav-item active"><a class="nav-link" href="<c:url value="/contactUs"/>">Contact Us
					<span class="sr-only">(current)</span>
			</a></li>
			

		</ul>
	</div>

	<ul class="nav justify-content-end">
		<c:if test="${sessionScope.user !=null}">
		<li class="nav-item active"><a  class="nav-link link-dark" href='<c:url value="/login"/>'>Logout
				<span class="sr-only">(current)</span>
		</a></li>
		</c:if>
		
		<c:if test="${sessionScope.user ==null}">
		<li class="nav-item active"><a class="nav-link link-dark" href="<c:url value="/login"/>">Login
				<span class="sr-only">(current)</span>
		</a></li>
		</c:if>
	</ul>

</nav>
<!-- NavBar END -->