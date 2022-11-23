<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="tab-pane fade ${showtab3 }" id="shareFriends"
	role="tabpanel" aria-labelledby="shareFriends-tab">
	
	<form action="ReportManage" method="post">
		<div class="row mt-3">
			<div class="col">
				<div class="form-inline">
					<div class="form-group">
						<label>Video Title <select name="videoIDshare" id="videoIDshare"
							class="form-control ml-3" >
								<c:forEach var="item" items="${vidList }">
									<option value="${item.videoID }"
										${item.videoID == videoUserId?'selected' : '' }>${item.title }</option>
								</c:forEach>
						</select>
						</label>
						<button class="btn btn-info ml-2" formaction="ReportManage/tab3">Report</button>
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
						<td>Sender Name</td>
						<td>Receiver Email</td>
						<td>Sent Date</td>
					</tr>
					<c:forEach var="item" items="${shareList }">
						<tr>
							<td>${item.username }</td>
							<td>${item.receiverMail }</td>
							<td>${item.sentDate }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form>
</div>