<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<jsp:include page="/common/error.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="userEditing-tab"
				data-toggle="tab" data-target="#userEditing" type="button"
				role="tab" aria-controls="userEditing" aria-selected="true">User
				Editing</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="userList-tab" data-toggle="tab"
				data-target="#userList" type="button" role="tab"
				aria-controls="userList" aria-selected="false">User List</button>
		</li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="userEditing"
			role="tabpanel" aria-labelledby="userEditing-tab">
			<form action="UsersManagement" method="post" class="needs-validation" novalidate>
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<label for="username">Username</label> 
									<input type="text" value="${user.username }"
										class="form-control" name="username" id="username"
										aria-describedby="usernameHid" placeholder="Username" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please fill in Username.</div>
								</div>
								<div class="form-group">
									<label for="fullname">Fullname</label> 
									<input type="text" value="${user.fullname }"
										class="form-control" name="fullname" id="fullname"
										aria-describedby="fullnameHid" placeholder="Fullname" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please fill in Fullname.</div>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label>Password</label>
									<div class="input-group mb-3">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1"><i
												class="fas fa-lock"></i></span>
										</div>
										<input name="password" type="password" value="${user.password }"
											class="input form-control" id="password"
											placeholder="password"  aria-label="password"
											aria-describedby="basic-addon1" 
										required/>
										<div class="input-group-append">
											<span class="input-group-text"
												onclick="password_show_hide();"> <i
												class="fas fa-eye" id="show_eye"></i> <i
												class="fas fa-eye-slash d-none" id="hide_eye"></i>
											</span>
										</div>
										<div class="valid-feedback">Looks good!</div>
										<div class="invalid-feedback">Please fill in Password.</div>
									</div>
									
								</div>
								<div class="form-group">
									<label for="email">Email</label> 
									<input type="email" value="${user.email }"
										class="form-control" name="email" id="email"
										aria-describedby="emailHid" placeholder="Email" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please fill in Email.</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" type="submit"
							formaction="UsersManagement/insert">Create</button>
						<button class="btn btn-warning" type="submit"
							formaction="UsersManagement/update">Update</button>
						<button class="btn btn-danger" 
							formaction="UsersManagement/delete">Delete</button>
						<button class="btn btn-info" 
							formaction="UsersManagement/reset">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="userList" role="tabpanel"
			aria-labelledby="userList-tab">
			<table class="table table-stripe">
				<tr>
					<td>Username</td>
					<td>Fullname</td>
					<td>Email</td>
					<td>Role</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${users }">
					<tr>
						<td>${item.username }</td>
						<td>${item.fullname }</td>
						<td>${item.email}</td>
						<td>${item.admin ? 'Admin' : 'User' }</td>
						<td><a href="UsersManagement/edit?username=${item.username }"><i
								class="fa fa-edit" aria-hidden="true"></i> Edit</a> <a
							href="UsersManagement/delete?username=${item.username }"><i
								class="fa fa-trash" aria-hidden="true"></i> Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	function password_show_hide() {
		var x = document.getElementById("password");
		var show_eye = document.getElementById("show_eye");
		var hide_eye = document.getElementById("hide_eye");
		hide_eye.classList.remove("d-none");
		if (x.type === "password") {
			x.type = "text";
			show_eye.style.display = "none";
			hide_eye.style.display = "block";
		} else {
			x.type = "password";
			show_eye.style.display = "block";
			hide_eye.style.display = "none";
		}
	}
</script>