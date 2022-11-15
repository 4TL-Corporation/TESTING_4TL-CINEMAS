<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page-single-2">
	<div class="container">

		<div class="row ipad-width">
			<div class="left-content">
				<a href="index-2.html"><img class="md-logo" src="#" alt=""></a>


				<div class="row">
					
					<h1>Forgot your Password ?</h1>
					<h5 style="color: white;">Please enter your username and email register. You wil receive a email about your password.</h5>
					<br />
					<br />
					<form action="ForgotPassword" method="post">
						<div class="col-md-4 col-sm-6 col-xs-12">
							<h3>Your Username:</h3>
							<input class="email" name="username" type="text" placeholder="Username"> 
							
						</div>
						<div class="col-sm-4">
							<h3>Your email:</h3>
							<input class="email" name="email" type="email" placeholder="example@gmail.com"> 
					
							<input class="redbtn" type="submit" placeholder="subscribe">
						</div>
						
					</form>
					
					
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="row">
							<div class="col-md-6" >
								<img class="cm-img" style="border-radius: 10px;"
									src="./user/images/uploads/forgot-password.jpg" alt="">
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