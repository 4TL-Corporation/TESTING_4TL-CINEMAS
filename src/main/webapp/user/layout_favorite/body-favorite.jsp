<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="hero user-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>${firstname}’s profile</h1>
					<ul class="breadcumb">
						<li class="active"><a href="Homepage">Home</a></li>
						<li><span class="ion-ios-arrow-right"></span>Favorite movies</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="buster-light">
	<div class="page-single">
		<div class="container">
			<div class="row ipad-width2">
				<div class="col-md-3 col-sm-12 col-xs-12">
					<div class="user-information">
						<div class="user-img">
							<a href="#"><img src="./user/images/uploads/user-img.png"
								alt=""><br></a> <a href="#" class="redbtn">Change
								avatar</a>
						</div>
						<div class="user-fav">
							<p>Account Details</p>
							<ul>
								<li><a href="EditProfile">Profile</a></li>
								<li class="active"><a href="UserFavorite">Favorite
										movies</a></li>
								<li><a href="#">Rated movies</a></li>
							</ul>
						</div>
						<div class="user-fav">
							<p>Others</p>
							<ul>
								<li><a href="EditProfile">Change password</a></li>
								<li><a href="#">Log out</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-9 col-sm-12 col-xs-12">
					<div class="topbar-filter user">
						<p>
							Found <span>${countVideoFav} movies</span> in total
						</p>
						<label>Sort by:</label> <select>
							<option value="range">-- Choose option --</option>
							<option value="saab">-- Choose option 2--</option>
						</select> <a href="userfavoritelist.html" class="list"><i
							class="ion-ios-list-outline "></i></a> <a
							href="userfavoritegrid.html" class="grid"><i
							class="ion-grid active"></i></a>
					</div>
					<div class="flex-wrap-movielist grid-fav">
						<c:forEach var="item" items="${movieFav}">
							<jsp:include page="item-MOVIE-FAVORITE.jsp">
								<jsp:param value="${item.video.poster }" name="poster"/>
								<jsp:param value="${item.video.videoID }" name="videoID"/>
								<jsp:param value="${item.video.title }" name="title"/>
								<jsp:param value="${item.video.views }" name="views"/>
							</jsp:include>
						
						</c:forEach>

						<!-- <div class="movie-item-style-2 movie-item-style-1 style-3">
							<img src="./user/images/uploads/mv1.jpg" alt="">
							<div class="hvr-inner">
								<a href="moviesingle.html"> Read more <i
									class="ion-android-arrow-dropright"></i>
								</a>
							</div>
							<div class="mv-item-infor">
								<h6>
									<a href="moviesingle.html">oblivion</a>
								</h6>
								<p class="rate">
									<i class="ion-android-star"></i><span>8.1</span> /10
								</p>
							</div>
						</div> -->
					</div>
					<div class="topbar-filter">
						<label>Movies per page:</label> <select>
							<option value="range">20 Movies</option>
							<option value="saab">10 Movies</option>
						</select>

						<div class="pagination2">
							<span>Page 1 of 2:</span> <a class="active" href="#">1</a> <a
								href="#">2</a> <a href="#">3</a> <a href="#">...</a> <a href="#">78</a>
							<a href="#">79</a> <a href="#"><i class="ion-arrow-right-b"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>