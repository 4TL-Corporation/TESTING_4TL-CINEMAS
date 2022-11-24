<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="movie-item">
	<div class="row">
		<div class="col-md-8 col-sm-12 col-xs-12">
			<div class="title-in">
				<div class="cate">
					<span class="blue"><a href="#">Sci-fi</a></span> <span class="yell"><a
						href="#">Action</a></span> <span class="orange"><a href="#">advanture</a></span>
				</div>
				<h1>
					<a href="#">${param.name} <span>2022</span></a>
				</h1>
				<div class="social-btn">
					<a href="#" class="parent-btn"><i class="ion-play"></i> Watch
						Trailer</a>
					<div class="hover-bnt">
						<a href="ShareVideo?videoID=${param.id}" class="parent-btn"><i
							class="ion-android-share-alt"></i>share</a>
						<div class="hvr-item">
							<a href="#" class="hvr-grow"><i class="ion-social-facebook"></i></a>
							<a href="#" class="hvr-grow"><i class="ion-social-twitter"></i></a>
							<a href="#" class="hvr-grow"><i class="ion-social-googleplus"></i></a>
							<a href="#" class="hvr-grow"><i class="ion-social-youtube"></i></a>
						</div>
					</div>
				</div>
				<div class="mv-details">
					<p>
						<i class="ion-android-star"></i><span>${param.rate}</span> /10
					</p>
					<ul class="mv-infor">
						<li>Run Time: 2h21’</li>
						<li>Rated: PG-13</li>
						<li>Release: 1 May 2015</li>
					</ul>
				</div>
				<div class="btn-transform transform-vertical">
					<div>
						<a id="${param.id}" href="MovieSingle?id=${param.id}" class="item item-1 redbtn">more detail</a>
					</div>
					<div>
						<a id="${param.id}" href="MovieSingle?id=${param.id}" class="item item-2 redbtn hvrbtn">more detail</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-12 col-xs-12">
			<div class="mv-img-2">
				<a href="#"><img src="./user/images/uploads/${param.photo}"  alt=""></a>
			</div>
		</div>
	</div>
</div>