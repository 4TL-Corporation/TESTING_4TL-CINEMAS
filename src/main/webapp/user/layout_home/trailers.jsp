<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row ipad-width">
	<div class="col-md-9 col-sm-12 col-xs-12">
		<div class="title-hd">
			<h2>trailers</h2>
			<a href="#" class="viewall">View all <i
				class="ion-ios-arrow-right"></i></a>
		</div>
		<div class="videos">
			<div class="slider-for-2 video-ft">
				<!-- BEGIN * video trailer -->

				<c:forEach var="item" items="${randomVD}">
					<jsp:include page="item-TRAILER.jsp">
						<jsp:param value="${item.videoID }" name="link" />
					</jsp:include>
				</c:forEach>
				<!--
				<div>
					<iframe class="item-video" src="#"
						data-src="https://www.youtube.com/embed/1Q8fG0TtVAY"></iframe>
				</div>
				<div>
					<iframe class="item-video" src="#"
						data-src="https://www.youtube.com/embed/w0qQkSuWOS8"></iframe>
				</div>
				<div>
					<iframe class="item-video" src="#"
						data-src="https://www.youtube.com/embed/44LdLqgOpjo"></iframe>
				</div>
				
				<div>
					<iframe class="item-video" src="#"
						data-src="https://www.youtube.com/embed/gbug3zTm3Ws"></iframe>
				</div>
				<div>
					<iframe class="item-video" src="#"
						data-src="https://www.youtube.com/embed/e3Nl_TCQXuw"></iframe>
				</div>
				<div>
					<iframe class="item-video" src="#"
						data-src="https://www.youtube.com/embed/NxhEZG0k9_w"></iframe>
				</div>
				-->
				<!-- END * video trailer -->
			</div>

			<div class="slider-nav-2 thumb-ft">
				<!-- BEGIN * image trailer -->

				<c:forEach var="item" items="${randomVD}">
					<jsp:include page="item-TRAILER-img.jsp">
						<jsp:param value="${item.poster }" name="image" />
						<jsp:param value="${item.title }" name="title" />
					</jsp:include>
				</c:forEach>
	<!--  
				<div class="item">
					<div class="trailer-img">
						<img src="./user/images/uploads/trailer7.jpg"
							alt="photo by Barn Images" width="4096" height="2737">
					</div>
					<div class="trailer-infor">
						<h4 class="desc">Wonder Woman</h4>
						<p>2:30</p>
					</div>
				</div>
				<div class="item">
					<div class="trailer-img">
						<img src="./user/images/uploads/trailer2.jpg"
							alt="photo by Barn Images" width="350" height="200">
					</div>
					<div class="trailer-infor">
						<h4 class="desc">Oblivion: Official Teaser Trailer</h4>
						<p>2:37</p>
					</div>
				</div>
				<div class="item">
					<div class="trailer-img">
						<img src="./user/images/uploads/trailer6.jpg"
							alt="photo by Joshua Earle" width="509" height="301">
					</div>
					<div class="trailer-infor">
						<h4 class="desc">Exclusive Interview: Skull Island</h4>
						<p>2:44</p>
					</div>
				</div>
				<div class="item">
					<div class="trailer-img">
						<img src="./user/images/uploads/trailer3.png"
							alt="photo by Alexander Dimitrov" width="100" height="56">
					</div>
					<div class="trailer-infor">
						<h4 class="desc">Logan: Director James Mangold Interview</h4>
						<p>2:43</p>
					</div>
				</div>

				<div class="item">
					<div class="trailer-img">
						<img src="./user/images/uploads/trailer4.png"
							alt="photo by Wojciech Szaturski" width="100" height="56">
					</div>
					<div class="trailer-infor">
						<h4 class="desc">Beauty and the Beast: Official Teaser
							Trailer 2</h4>
						<p>2: 32</p>
					</div>
				</div>

				<div class="item">
					<div class="trailer-img">
						<img src="images/uploads/trailer5.jpg"
							alt="photo by Wojciech Szaturski" width="360" height="189">
					</div>
					<div class="trailer-infor">
						<h4 class="desc">Fast&Furious 8</h4>
						<p>3:11</p>
					</div>
				</div>
 -->
				<!-- END * image trailer -->
			</div>
		</div>
	</div>
	<div class="col-md-3 col-sm-12 col-xs-12">
		<div class="sidebar">
			<div class="celebrities">
				<h4 class="sb-title">Spotlight Celebrities</h4>
				<div class="celeb-item">
					<a href="#"><img src="./user/images/uploads/ava1.jpg" alt=""
						width="70" height="70"></a>
					<div class="celeb-author">
						<h6>
							<a href="#">Samuel N. Jack</a>
						</h6>
						<span>Actor</span>
					</div>
				</div>
				<div class="celeb-item">
					<a href="#"><img src="./user/images/uploads/ava2.jpg" alt=""
						width="70" height="70"></a>
					<div class="celeb-author">
						<h6>
							<a href="#">Benjamin Carroll</a>
						</h6>
						<span>Actor</span>
					</div>
				</div>
				<div class="celeb-item">
					<a href="#"><img src="./user/images/uploads/ava3.jpg" alt=""
						width="70" height="70"></a>
					<div class="celeb-author">
						<h6>
							<a href="#">Beverly Griffin</a>
						</h6>
						<span>Actor</span>
					</div>
				</div>
				<div class="celeb-item">
					<a href="#"><img src="./user/images/uploads/ava4.jpg" alt=""
						width="70" height="70"></a>
					<div class="celeb-author">
						<h6>
							<a href="#">Justin Weaver</a>
						</h6>
						<span>Actor</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>