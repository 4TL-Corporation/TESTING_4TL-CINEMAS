<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-single-2">
	<div class="container">

		<div class="row ipad-width">
			<div class="left-content">
				<a href="index-2.html"><img class="md-logo" src="#" alt=""></a>


				<div class="row">
					<div class="col-md-6 col-sm-12 col-xs-12">
						<h1>Send Video to Your Friends</h1>
						<br />
						<form action="ShareVideo" method="post">
							<h3>Your Friends' email:</h3>
							<input class="email" name="email" type="text"
								placeholder="example@gmail.com"> <input class="redbtn"
								type="submit" placeholder="subscribe"> <input
								type="hidden" name="videoID" class="videoID" value="${videoID}">
						</form>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
						<div class="row">
							<div class="col-md-6">
								<img class="cm-img" src="./user/images/uploads/${posterVid}"
									alt="">
								<div class="cate">
									<span class="blue"><a href="#">Sci-fi</a></span> <span
										class="yell"><a href="#">Action</a></span> <span
										class="orange"><a href="#">advanture</a></span>
								</div>
								<div class="mv-details">
									<i style="color: #f5b50a; font-size: 22px;" class="ion-android-star"></i>
									<span style="font-weight: 400; font-size: 18px; color: #ffffff">
										${rateVid} Views
									</span>
								</div>
								<br />
								<h3>Release: 1 May 2015</h3>
								<h3>Rated: PG-13</h3>
								<h3>Release: 1 May 2015</h3>
								
							</div>
							<div class="col-md-6">
								<div class="title-in">
									<h1>
										${nameVid} <span>2022</span>
									</h1>

								</div>
							</div>
						</div>



					</div>
				</div>
				<div class="row">
					<jsp:include page="/common/error.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>