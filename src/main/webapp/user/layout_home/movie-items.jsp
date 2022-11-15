<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-12">
		<div class="title-hd">
			<h2>in theater</h2>
			<a href="#" class="viewall">View all <i
				class="ion-ios-arrow-right"></i></a>
		</div>
		<div class="tabs">
			<ul class="tab-links">
				<li class="active"><a href="#tab1-h2">#Popular</a></li>
				<li><a href="#tab2-h2"> #Coming soon</a></li>
				<li><a href="#tab3-h2"> #Top rated </a></li>
				<li><a href="#tab4-h2"> #Most reviewed</a></li>
			</ul>
			<div class="tab-content">
				<div id="tab1-h2" class="tab active">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-THEATER.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="${item.videoID }" name="id" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						<!--<div class="slide-it">
								<div class="movie-item">
									<div class="mv-img">
										<img src="./user/images/uploads/mv-it8.jpg" alt="">
									</div>
									<div class="hvr-inner">
										<a href="moviesingle.html"> Read more <i
											class="ion-android-arrow-dropright"></i>
										</a>
									</div>
									<div class="title-in">
										<h6>
											<a href="#">Die hard</a>
										</h6>
										<p>
											<i class="ion-android-star"></i><span>7.4</span> /10
										</p>
									</div>
								</div>
							</div>-->
						</div>
					</div>
				</div>
				<div id="tab2-h2" class="tab">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
				<div id="tab3-h2" class="tab">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
				<div id="tab4-h2" class="tab">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="title-hd">
			<h2>on tv</h2>
			<a href="#" class="viewall">View all <i
				class="ion-ios-arrow-right"></i></a>
		</div>
		<div class="tabs">
			<ul class="tab-links-2">
				<li class="active"><a href="#tab21-h2">#Popular</a></li>
				<li><a href="#tab22-h2"> #Coming soon</a></li>
				<li><a href="#tab23-h2"> #Top rated </a></li>
				<li><a href="#tab24-h2"> #Most reviewed</a></li>
			</ul>
			<div class="tab-content">
				<div id="tab21-h2" class="tab active">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
				<div id="tab22-h2" class="tab">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
				<div id="tab23-h2" class="tab">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
				<div id="tab24-h2" class="tab">
					<div class="row">
						<div class="slick-multiItem2">
							<c:forEach var="item" items="${videos}">
								<jsp:include page="item-TV.jsp">
									<jsp:param value="${item.poster }" name="photo" />
									<jsp:param value=" Read more " name="button" />
									<jsp:param value="${item.title }" name="name" />
									<jsp:param value="#" name="link" />
									<jsp:param value="${item.views }" name="rate" />
								</jsp:include>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>