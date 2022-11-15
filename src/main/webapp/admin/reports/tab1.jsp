<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!-- Tab1 -->
<div class="tab-pane fade ${showtab1 }" id="home" role="tabpanel"
	aria-labelledby="home-tab">
	<table class="table table-bordered mt-3">
		<tr>
			<td>Video Title</td>
			<td>Favorites Count</td>
			<td>Lasted Date</td>
			<td>Oldest Date</td>
		</tr>
		<c:forEach var="item" items="${favList }">
			<tr>
				<td>${item.videoTitle }</td>
				<td>${item.count }</td>
				<td>${item.newsDate }</td>
				<td>${item.oldDate }</td>
			</tr>
		</c:forEach>
	</table>
</div>