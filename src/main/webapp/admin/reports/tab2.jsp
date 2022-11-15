<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="tab-pane fade ${showtab2 }" id="favoriteUsers"
	role="tabpanel" aria-labelledby="favoriteUsers-tab">

	<form action="ReportManage" method="post">
		<div class="row mt-3">
			<div class="col">
				<div class="form-inline">
					<div class="form-group">
						<label>Video Title <select name="videoIDuser" 
							id="videoUserId" class="form-control ml-3">
								<c:forEach var="item" items="${vidList }">
									<option value="${item.videoID }"
										${item.videoID == videoUserId?'selected':''}>${item.title }</option>
								</c:forEach>
						</select>
						</label>
						<button class="btn btn-info ml-2" formaction="ReportManage/tab2">Report</button>
						<c:if test="${not empty videoSelected }">
							<h4 class="ml-2"> Selected: ${videoSelected }</h4>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col mt-3">
				<table class="table table-bordered">
					<tr>
						<td>Username</td>
						<td>Fullname</td>
						<td>Email</td>
						<td>Favorite Date</td>
					</tr>
					<c:forEach var="item" items="${favUsers }">
						<tr>
							<td>${item.username }</td>
							<td>${item.fullname }</td>
							<td>${item.email }</td>
							<td>${item.likeDate }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form>
</div>