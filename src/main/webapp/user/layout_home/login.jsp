<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="login-content">
	<a href="#" class="close">x</a>
	<h3>Login</h3>
	<form method="post" action="Login">
		<div class="row">
			<label for="username"> Username: <input type="text"
				name="username" id="username" placeholder="Hugh Jackman"
				required="required" />
			</label>
		</div>

		<div class="row">
			<label for="password"> Password: <input type="password"
				name="password" id="password" placeholder="******"
				required="required" />
			</label>
		</div>

		<div class="row">
			<div class="remember">
				<div>
					<input type="checkbox" name="remember" value="Remember me"><span>Remember
						me</span>
				</div>
				<a href="ForgotPassword">Forget password ?</a>
			</div>
		</div>

		<div class="row">
			<jsp:include page="/common/error.jsp"></jsp:include>
			
			<button type="submit" >Login</button>
			
		</div>
		<br />
		<div class="row signupLink">
			<button type="submit">sign up</button>
		</div>
	</form>
	<div class="row">
		<p>Or via social</p>
		<div class="social-btn-2">
			<a class="fb" href="#"><i class="ion-social-facebook"></i>Facebook</a>
			<a class="tw" href="#"><i class="ion-social-twitter"></i>twitter</a>
		</div>
	</div>
</div>