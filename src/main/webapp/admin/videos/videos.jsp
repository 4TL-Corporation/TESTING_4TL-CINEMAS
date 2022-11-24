<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col mt-4">
	<jsp:include page="/common/error.jsp"></jsp:include>
	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<button class="nav-link active" id="videoEditing-tab"
				data-toggle="tab" data-target="#videoEditing" type="button"
				role="tab" aria-controls="videoEditing" aria-selected="true">Video
				Editing</button>
		</li>
		<li class="nav-item" role="presentation">
			<button class="nav-link" id="videoList-tab" data-toggle="tab"
				data-target="#videoList" type="button" role="tab"
				aria-controls="videoList" aria-selected="false">Video List</button>
		</li>

	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEditing"
			role="tabpanel" aria-labelledby="videoEditing-tab">
			<form action="VideosManagementServlet" method="post"
				class="needs-validation" novalidate enctype="multipart/form-data">
				<div class="card">
					<div class="card-body">
						<div class="row">
							<div class="col-3">
								<div class="border p-3">
									<img id="posterShow"
										src="./user/images/uploads/${video.poster}" alt=""
										class="img-fluid">

									<div class="input-group mb-3 mt-3">
										<div class="custom-file">
											<label class="custom-file-label" for="cover">Choose
												File</label> <input type="file"
												accept="image/gif, image/jpeg, image/png"
												onchange="chooseFile(this)" class="custom-file-input"
												id="cover" name="cover" />
										</div>
									</div>

								</div>
							</div>
							<div class="col-9">
								<div class="form-group">
									<label for="videoID">Youtube ID</label> <input type="text"
										value="${video.videoID }" class="form-control videoID"
										name="videoID" id="videoID" aria-describedby="videoID"
										placeholder="Youtube ID" required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please fill in Video ID.</div>
								</div>
								<div class="form-group">
									<label for="title">Video Title</label> <input type="text"
										value="${video.title }" class="form-control" name="title"
										id="title" aria-describedby="title" placeholder="Video Title"
										required>
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please fill in Video Title.</div>
								</div>
								<div class="form-group">
									<label for="views">View Count</label> <input type="text"
										value="${video.views }" class="form-control" name="views"
										id="views" value="0" aria-describedby="viewCountHid"
										placeholder="View Count">
									<div class="valid-feedback">Looks good!</div>
								</div>
								<div class="form-group">
									<div class="form-check form-check-inline">
										<label class="form-check-label" for="">Status:</label>
									</div>

									<div class="custom-control custom-radio custom-control-inline">
										<input class="custom-control-input" type="radio" name="active"
											id="active" value="true" ${video.active? 'checked' : ''}
											required> <label class="custom-control-label"
											for="active">Active</label>
									</div>

									<div class="custom-control custom-radio custom-control-inline">
										<input class="custom-control-input" type="radio" name="active"
											id="invalid" value="false" ${video.active? '' : 'checked'}
											required> <label class="custom-control-label"
											for="invalid">Inactive</label>
										<div class="invalid-feedback">More example invalid
											feedback text</div>
									</div>
								</div>

								<div class="form-group">
									<label for="description">Description</label>
									<textarea name="description" id="description" cols="30"
										rows="7.5" class="form-control" required>${video.description }</textarea>
									<div class="invalid-feedback">Please enter a description
										in the textarea.</div>
								</div>

							</div>
						</div>

					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-primary" id="create" type="submit"
							formaction="VideosManagementServlet/insert">Create</button>
						<button class="btn btn-warning" type="submit"
							formaction="VideosManagementServlet/update">Update</button>
						<button class="btn btn-danger"
							formaction="VideosManagementServlet/delete">Delete</button>
						<button class="btn btn-info"
							formaction="VideosManagementServlet/reset">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videoList" role="tabpanel"
			aria-labelledby="videoList-tab">
			<table class="table table-stripe">
				<tr>
					<td>Youtube ID</td>
					<td>Video Title</td>
					<td>View Count</td>
					<td>Status</td>
					<td>Action</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${videos}">
					<tr>
						<td>${item.videoID}</td>
						<td>${item.title}</td>
						<td>${item.views}</td>
						<td>${item.active? 'Active':'Inactive'}</td>
						<td><a id="edit/${item.videoID}" href="VideosManagementServlet/edit?id=${item.videoID}"><i
								class="fa-solid fa-pen-to-square"></i>Edit</a> <a
						 id="delete/${item.videoID}" href="VideosManagementServlet/delete?id=${item.videoID}"><i
								class="fa-solid fa-trash"></i>Delete</a></td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</div>
<script>
	//show img to posterShow
	function chooseFile(fileInput) {
		if (fileInput.files && fileInput.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#posterShow').attr('src', e.target.result);
			};

			reader.readAsDataURL(fileInput.files[0]);
		}
	};
</script>

