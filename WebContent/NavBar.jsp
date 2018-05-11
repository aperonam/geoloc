<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="left-container">
	<div id="left-container-content">
		<a href="/geoloc"><img id="nav-logo" alt="logo" src="./assets/img/logo2.png"></a>
		<input id="search-bar" type="text" placeholder="Search by zip, city, street…" />
		<p class="section-title">Thoughts</p>
		<div id="thoughts-container">
			<c:forEach items="${thoughts}" var="thought">
				<div class="thought">
					<p class="thought-text">${thought.text}</p>
					<p class="thought-tag">${thought.tag}</p>
					<div class="thought-buttons">
						<button class="like-button" onclick="postLike(${thought.id})"><img alt="like-icon" src="./assets/img/like-icon.svg"></button>
						<p class="thought-tag">${thought.numberOfLikes} likes</p>
						<button class="chat-button" onclick="requestChat(${thought.id})"><img alt="chat-icon" src="./assets/img/chat-icon.png"></button>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@ include file = "Post.jsp" %>
</div>