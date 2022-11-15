<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="slider sliderv2">
	<jsp:include page="/user/layout_home/slider.jsp"></jsp:include>
</div>

<div class="buster-light">
	<div class="movie-items  full-width">
		<jsp:include page="/user/layout_home/movie-items.jsp"></jsp:include>
	</div>

	<div class="trailers full-width">
		<jsp:include page="/user/layout_home/trailers.jsp"></jsp:include>
	</div>

	<!-- latest new v2 section-->
	<div class="latestnew full-width">
		<jsp:include page="/user/layout_home/latestnew.jsp"></jsp:include>
	</div>
	<!--end of latest new v2 section-->
</div>