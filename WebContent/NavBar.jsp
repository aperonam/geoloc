<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="left-container">
	<div id="left-container-content">
		<p id="title">Geoloc</p>
		<p class="section-title">Thoughts</p>
		<div id="thoughts-container">
			<c:forEach items="${thoughts}" var="thought">
				<div class="thought">
					<p class="thought-text">${thought.text}</p>
					<p class="thought-tag">${thought.tag}</p>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@ include file = "Post.jsp" %>
</div>