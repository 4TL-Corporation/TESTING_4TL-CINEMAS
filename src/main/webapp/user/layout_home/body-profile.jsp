<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="hero user-hero">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="hero-ct">
					<h1>${firstname}- profile</h1>
					<ul class="breadcumb">
						<li class="active"><a href="Homepage">Home</a></li>
						<li><span class="ion-ios-arrow-right"></span>Profile</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="page-single">
	<div class="container">
		<div class="row ipad-width">
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
							<li class="active"><a href="EditProfile">Profile</a></li>
							<li><a href="UserFavorite">Favorite movies</a></li>
							<li><a href="#">Rated movies</a></li>
						</ul>
					</div>
					<div class="user-fav">
						<p>Others</p>
						<ul>
							<li><a href="EditProfile">Change password</a></li>
							<li><a href="Logoff">Log out</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-9 col-sm-12 col-xs-12">
				<div class="form-style-1 user-pro" >
					<form action="EditProfile" method="post">
						<h4>01. Profile details</h4>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Username</label> <input type="text"
									placeholder="Username" value="${user.username}" name="username">
							</div>
							<div class="col-md-6 form-it">
								<label>Email Address</label> <input type="text"
									placeholder="Email" value="${user.email}" name="email">
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>First Name</label> <input type="text"
									placeholder="First Name" value="${firstname}" name="firstname">
							</div>
							<div class="col-md-6 form-it">
								<label>Last Name</label> <input type="text"
									placeholder="Lastname" value="${lastname}" name="lastname">
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Country</label> <select>
									<option value="united">United States</option>
									<option value="saab">Others</option>
								</select>
							</div>
							<div class="col-md-6 form-it">
								<label>State</label> <select>
									<option value="united">New York</option>
									<option value="saab">Others</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<input class="submit" type="submit" formaction="EditProfile/updateProfile" value="save">
							</div>
						</div>
						<br />
						<jsp:include page="/common/error.jsp"></jsp:include>
					</form>
					<form action="EditProfile" method="post" class="#">
						<h4>02. Change password</h4>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Old Password</label> <input type="text"
									placeholder="${user.password}" name="oldpassword">
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>New Password</label> <input type="text"
									placeholder="***************" name="newpassword">
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 form-it">
								<label>Confirm New Password</label> <input type="text"
									placeholder="*************** " name="confirmpassword">
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<input class="submit" type="submit" formaction="EditProfile/updatePassword" value="change">
							</div>
						</div>
						<br />
						<jsp:include page="/common/errorPassword.jsp"></jsp:include>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>