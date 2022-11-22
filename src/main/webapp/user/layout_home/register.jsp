<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="login-content">
	<a href="#" class="close">x</a>
	<h3>sign up</h3>
	<form method="post" action="registration">
		<div class="row">
			<label for="username-2"> Username: <input type="text"
				name="username" id="username-2" placeholder="Jackman" 
				required="required" />
			</label>
		</div>

		<div class="row">
			<label for="username-2"> Fullname: <input type="text"
				name="fullname" id="fullname" placeholder="Hugh Jackman"
				required="required" />
			</label>
		</div>

		<div class="row">
			<label for="email-2"> Your email: <input type="email"
				name="emailSignin" id="email-2" placeholder=""
				
				required="required" />
			</label>
		</div>
		<div class="row">
			<label for="password-2"> Password: <input type="password"
				name="password" id="password-2" placeholder=""
				
				required="required" />
			</label>
		</div>
		<div class="row">
			<label for="repassword-2"> re-type Password: <input
				type="password" name="password" id="repassword-2" placeholder=""
				
				required="required" />
			</label>
		</div>
		<div class="row">
			<jsp:include page="/common/error.jsp"></jsp:include>
			<div class="row">
				<button type="submit" class="register_btn">sign up</button>
			</div>
		</div>
	</form>
</div>