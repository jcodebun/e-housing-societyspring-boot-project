<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js"
	integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js"
	integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG"
	crossorigin="anonymous"></script>
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
	
	<link
	href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'mm/dd/yy',
			defaultDate : "01/01/1983",
			changeMonth : true,
			changeYear : true,
			yearRange : '-35:-18'
		});
	});
	$(function() {
		$("#datepicker1").datepicker({
			dateFormat : 'mm/dd/yy',
			changeMonth : true,
			changeYear : true,
		});
	});
	$(document).ready(function() {
		$('.nav-link-collapse').on('click', function() {
			$('.nav-link-collapse').not(this).removeClass('nav-link-show');
			$(this).toggleClass('nav-link-show');
		});
	});
	// Hide submenus
	$('#body-row .collapse').collapse('hide');

	// Collapse/Expand icon
	$('#collapse-icon').addClass('fa-angle-double-left');

	// Collapse click
	$('[data-toggle=sidebar-colapse]').click(function() {
		SidebarCollapse();
	});

	function SidebarCollapse() {
		$('.menu-collapsed').toggleClass('d-none');
		$('.sidebar-submenu').toggleClass('d-none');
		$('.submenu-icon').toggleClass('d-none');
		$('#sidebar-container').toggleClass(
				'sidebar-expanded sidebar-collapsed');

		// Treating d-flex/d-none on separators with title
		var SeparatorTitle = $('.sidebar-separator-title');
		if (SeparatorTitle.hasClass('d-flex')) {
			SeparatorTitle.removeClass('d-flex');
		} else {
			SeparatorTitle.addClass('d-flex');
		}

		// Collapse/Expand icon
		$('#collapse-icon').toggleClass(
				'fa-angle-double-left fa-angle-double-right');
	}
</script>

</head>
<body>


	<tiles:insertAttribute name="header" />
	<div class="row" id="body-row">
		<tiles:insertAttribute name="sidebar" />
		<div class="col p-4">
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>



</body>
</html>