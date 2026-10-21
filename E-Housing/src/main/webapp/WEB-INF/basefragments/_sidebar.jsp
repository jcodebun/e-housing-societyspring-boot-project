<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!-- Sidebar -->
<div id="sidebar-container" class="sidebar-expanded d-none d-md-block">
	<!-- d-* hiddens the Sidebar in smaller devices. Its itens can be kept on the Navbar 'Menu' -->
	<!-- Bootstrap List Group -->
	<ul class="list-group">
				<a href="#" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
					</div>
				</a>

		<c:if test="${sessionScope.user !=null}">
			<c:if test="${sessionScope.user.roleId ==1}">
				<a href="<c:url value="/ctl/society"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed"> Add Society</span>
					</div>
				</a>
				<a href="<c:url value="/ctl/society/search"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Society Report</span>
					</div>
				</a>
				

				<a href="<c:url value="/ctl/house"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Add House</span>
					</div>
				</a>
				<a href="<c:url value="/ctl/house/search"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">House Report</span>
					</div>
				</a>

				<a href="<c:url value="/ctl/allocate"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Allocate House</span>
					</div>
				</a>
				<a href="<c:url value="/ctl/allocate/search"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Member Report</span>
					</div>
				</a>
				<a href="<c:url value="/ctl/complain/search"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Complain</span>
					</div>
				</a>
				<a href="<c:url value="/ctl/rentSell/search/sell"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Sell House Report</span>
					</div>
				</a>
				<a href="<c:url value="/ctl/rentSell/search/rent"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Rent House Report</span>
					</div>
				</a>
			</c:if>
			
			<c:if test="${sessionScope.user.roleId ==2}">
			
			
			<a href="<c:url value="/ctl/complain/search"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Complain</span>
					</div>
				</a>
				
				<a href="<c:url value="/ctl/rentSell/rent"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Rent</span>
					</div>
				</a>
				
				<a href="<c:url value="/ctl/rentSell/sell"/>"" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Sell</span>
					</div>
				</a>
				
				<a href="<c:url value="/message/search"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Message</span>
					</div>
				</a>
				
				<a href="<c:url value="/profile"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">My Profile</span>
					</div>
				</a>
				
				<a href="<c:url value="/changepassword"/>" class="bg-dark list-group-item list-group-item-action">
					<div class="d-flex w-100 justify-content-start align-items-center">
						 <span
							class="menu-collapsed">Change Password</span>
					</div>
				</a>
			
			</c:if>
			
			
		</c:if>

		<c:if test="${sessionScope.user ==null}">
			<a href="<c:url value="/welcome"/>" class="bg-dark list-group-item list-group-item-action">
				<div class="d-flex w-100 justify-content-start align-items-center">
				 <span
						class="menu-collapsed">Home</span>
				</div>
			</a>
			
			<a href="<c:url value="/ctl/rentSell/search/home"/>" class="bg-dark list-group-item list-group-item-action">
				<div class="d-flex w-100 justify-content-start align-items-center">
					 <span
						class="menu-collapsed">Search</span>
				</div>
			</a>
			
			<a href="<c:url value="/ctl/rentSell/search/rent"/>" class="bg-dark list-group-item list-group-item-action">
				<div class="d-flex w-100 justify-content-start align-items-center">
					 <span
						class="menu-collapsed">Rent List</span>
				</div>
			</a>
			<a href="<c:url value="/ctl/rentSell/search/sell"/>" class="bg-dark list-group-item list-group-item-action">
				<div class="d-flex w-100 justify-content-start align-items-center">
					 <span
						class="menu-collapsed">Sell List</span>
				</div>
			</a>
			
		</c:if>
	</ul>
	<!-- List Group END-->
</div>