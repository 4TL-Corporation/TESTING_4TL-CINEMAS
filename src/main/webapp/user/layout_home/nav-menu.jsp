<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<nav id="mainNav" class="navbar navbar-default navbar-custom">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header logo">
			<div class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<div id="nav-icon1">
					<span></span> <span></span> <span></span>
				</div>
			</div>
			<a href="Homepage"><img class="logo"
				src="./user/images/logo2.png" alt="" width="119" height="58"></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse flex-parent"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav flex-child-menu menu-left">
				<li class="hidden"><a href="#page-top"></a></li>
				<li class="dropdown first"><a
					class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown"
					data-hover="dropdown"> Home <i class="fa fa-angle-down"
						aria-hidden="true"></i>
				</a>
					<ul class="dropdown-menu level1">
						<li><a href="#">Home 01</a></li>
						<li><a href="#">Home 02</a></li>
						<li><a href="#">Home 03</a></li>
					</ul></li>
				<li class="dropdown first"><a
					class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown"
					data-hover="dropdown"> movies<i class="fa fa-angle-down"
						aria-hidden="true"></i>
				</a>
					<ul class="dropdown-menu level1">
						<!-- <li class="dropdown">
									<a href="#">about us <i class="fa fa-caret-right" aria-hidden="true"></i></a>
									<ul class="dropdown-menu level2">
										<li><a href="aboutv1.html">About Us 01</a></li>
										<li><a href="aboutv2.html">About Us 02</a></li>
									</ul>
								</li> -->
						<li><a href="#">Movie grid</a></li>
						<li><a href="moviegridfw_light.jsp">movie grid full width</a></li>
						<li><a href="#">Movie list</a></li>
						<li class="it-last"><a href="moviesingle_light.jsp">Movie
								single</a></li>
					</ul></li>
				<li class="dropdown first"><a
					class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown"
					data-hover="dropdown"> celebrities <i class="fa fa-angle-down"
						aria-hidden="true"></i>
				</a>
					<ul class="dropdown-menu level1">
						<li><a href="#">celebrity grid 01</a></li>
						<li><a href="#">celebrity grid 02</a></li>
						<li><a href="#">celebrity list</a></li>
						<li class="it-last"><a href="#">celebrity single</a></li>
					</ul></li>
				<li class="dropdown first"><a
					class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown"
					data-hover="dropdown"> news <i class="fa fa-angle-down"
						aria-hidden="true"></i>
				</a>
					<ul class="dropdown-menu level1">
						<li><a href="#">blog List</a></li>
						<li><a href="#">blog Grid</a></li>
						<li class="it-last"><a href="#">blog Detail</a></li>
					</ul></li>
				<c:if test="${not empty user }">
					<li class="dropdown first"><a
						class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown"
						data-hover="dropdown"> community <i class="fa fa-angle-down"
							aria-hidden="true"></i>
					</a>
						<ul class="dropdown-menu level1">
							<!-- <li><a href="userfavoritegrid_light.jsp">user favorite
								grid</a></li> -->


							<li><a href="UserFavorite">user favorite list</a></li>
							<li><a href="EditProfile">user profile</a></li>
							<!-- <li class="it-last"><a href="userrate_light.jsp">user
									rate</a></li> -->

						</ul></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav flex-child-menu menu-right">
				<c:if test="${not empty adminRole }">
					<li class="dropdown first"><a
						class="btn btn-default dropdown-toggle lv1" data-toggle="dropdown"
						data-hover="dropdown"> pages <i class="fa fa-angle-down"
							aria-hidden="true"></i>
					</a>
						<ul class="dropdown-menu level1">
							<li><a href="VideosManagementServlet">ADMIN PAGE</a></li>
							<li><a href="404.jsp">404 Page</a></li>
							<li class="it-last"><a href="comingsoon.jsp">Coming soon</a></li>
						</ul></li>
				</c:if>
				<li><a href="#">Help</a></li>
				<c:if test="${empty user}">
					<li class="loginLink"><a href="#">LOG In</a></li>
					<li class="btn signupLink"><a href="#">sign up</a></li>
				</c:if>
				<c:if test="${not empty user }">
					<li class=""><a href="Logoff">Logout</a></li>
					<li class="btn signupLink"><a href="#">Hello!
							${user.username}</a></li>
				</c:if>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<!-- search form -->
</div>