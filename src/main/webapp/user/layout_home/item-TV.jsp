<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="slide-it">
	<div class="movie-item">
		<div class="mv-img">
			<img src="./user/images/uploads/${param.photo}" alt="">
		</div>
		<div class="hvr-inner">
			<a href="moviesingle.html">${param.button}<i
				class="ion-android-arrow-dropright"></i>
			</a>
		</div>
		<div class="title-in">
			<h6>
				<a href="#">${param.name}</a>
			</h6>
			<p>
				<i class="ion-android-star"></i><span>${param.rate}</span> /10
			</p>
		</div>
	</div>
</div>