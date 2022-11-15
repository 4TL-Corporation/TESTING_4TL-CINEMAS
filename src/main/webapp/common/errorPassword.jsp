<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${not empty messagePass }">
	<div class="alert alert-success">${messagePass }</div>
</c:if>
<c:if test="${not empty errorPass }">
	<div class="alert alert-danger">${errorPass }</div>
</c:if>

