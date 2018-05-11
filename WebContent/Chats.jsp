<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="chats" style="display: none;">
	<div id="chat-requests">
		<p>Requests</p>
		<c:forEach items="${requests}" var="request">
			<div class="request">
				<p class="request-user">${request.user.id}</p>
				<p class="request-message">${request.message}</p>
			</div>
		</c:forEach>
	</div>
	<div id="chats-on">
		<p>Chats</p>
		<c:forEach items="${chats}" var="chat">
			<div class="chat">
				<p class="chat-user">${chat.user.id}</p>
			</div>
		</c:forEach>
	</div>
</div>