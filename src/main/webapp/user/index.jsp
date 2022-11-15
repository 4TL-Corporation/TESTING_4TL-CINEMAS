<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 7]>
<html class="ie ie7 no-js" lang="en-US">
<![endif]-->
<!--[if IE 8]>
<html class="ie ie8 no-js" lang="en-US">
<![endif]-->
<!--[if !(IE 7) | !(IE 8)  ]><!-->
<html lang="en" class="no-js">

<!-- homev2_light16:29-->
<head>
<!-- Basic need -->
<base href="/ASM-JAVA4/">
<title>${page.title}</title>
<meta charset="UTF-8">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<link rel="profile" href="#">

<!--Google Font-->
<link rel="stylesheet"
	href='http://fonts.googleapis.com/css?family=Dosis:400,700,500|Nunito:300,400,600' />
<!-- Mobile specific meta -->
<meta name=viewport content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone-no">

<!-- CSS files -->
<link rel="stylesheet" href="./user/css/plugins.css">
<link rel="stylesheet" href="./user/css/style.css">
<link rel="icon" href="./user/images/logo3.png"  type="image/x-icon" />
</head>
<body>
	<!--preloading-->
	
		<div id="preloader" >
			<img class="logo" src="./user/images/logo1.png" alt="" width="119"
				height="58">
			<div id="status">
				<span></span> <span></span>
			</div>
		</div>
	
	<!--end of preloading-->

	<!--login form popup-->
	<div class="login-wrapper" id="login-content">
		<jsp:include page="/user/layout_home/login.jsp"></jsp:include>
	</div>
	<!--end of login form popup-->
	
	<!--signup form popup-->
	<div class="login-wrapper" id="signup-content">
		<jsp:include page="/user/layout_home/register.jsp"></jsp:include>
	</div>
	<!--end of signup form popup-->

	<!-- BEGIN | Header -->
	<header class="ht-header full-width-hd" id="header-light">
		<jsp:include page="/user/layout_home/nav-menu.jsp"></jsp:include>
	</header>
	<!-- END | Header -->

	<!-- BEGIN | Body -->
	<jsp:include page="${page.contentUrl}"></jsp:include>
	
	<!--end of Body-->

	<!-- footer v2 section-->
	<footer class="ht-footer full-width-ft">
		<jsp:include page="./layout_home/footer.jsp"></jsp:include>
	</footer>
	<!-- end of footer v2 section-->

	<script src="./user/js/jquery.js"></script>
	<script src="./user/js/plugins.js"></script>
	<script src="./user/js/plugins2.js"></script>
	<script src="./user/js/custom.js"></script>
</body>

<!-- homev2_light16:30-->
</html>