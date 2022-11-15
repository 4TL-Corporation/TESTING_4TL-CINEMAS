<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<jsp:include page="/common/error.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link ${activetab1 }" id="home-tab" 
				data-toggle="tab" data-target="#home" type="button" role="tab"
				aria-controls="home" aria-selected="${tabselectedtab1 }">Reports</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link ${activetab2 }" id="favoriteUsers-tab"
				data-toggle="tab" data-target="#favoriteUsers" type="button"
				role="tab" aria-controls="favoriteUsers" aria-selected="${tabselectedtab2 }">Favorite
				Users</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link ${activetab3 }" id="shareFriends-tab"
				data-toggle="tab" data-target="#shareFriends" type="button"
				role="tab" aria-controls="shareFriends" aria-selected="${tabselectedtab3 }">Share
				Friends</button>
		</li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<!-- Tab1 -->
		<jsp:include page="/admin/reports/tab1.jsp"></jsp:include>

		<!-- Tab2 -->
		<jsp:include page="/admin/reports/tab2.jsp"></jsp:include>

		<!-- Tab3 -->
		<jsp:include page="/admin/reports/tab3.jsp"></jsp:include>

	</div>
</div>