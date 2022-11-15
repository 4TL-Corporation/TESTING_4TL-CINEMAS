<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="movie-item-style-2 movie-item-style-1 style-3">
	<img src="./user/images/uploads/${param.poster}" alt="">
	<div class="hvr-inner">
		<a href="MovieSingle?id=${param.videoID}"> Read more <i
			class="ion-android-arrow-dropright"></i>
		</a>
	</div>
	<div class="mv-item-infor">
		<h6>
			<a href="MovieSingle?id=${param.videoID}">${param.title}</a>
		</h6>
		<p class="rate">
			<i class="ion-android-star"></i><span>${param.views} Views</span>
		</p>
	</div>
</div>