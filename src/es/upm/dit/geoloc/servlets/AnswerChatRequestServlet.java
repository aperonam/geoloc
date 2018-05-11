package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ChatDAOImplementation;
import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.User;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Twitter;

public class AnswerChatRequestServlet extends HttpServlet {
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Request body
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		try {
			JSONObject jsonBody = new JSONObject(body);
			
			// Get Twitter session
			Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
			
			if (twitter == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(401);
				out.print("Not Authorized");
				return;
			}
			
			User user = UserDAOImplementation.getInstance().readUser((int) twitter.getId());
			if (user == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(401);
				out.print("Not Authorized");
				return;
			}
			
			if (!jsonBody.has("thoughtId")) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(400);
				out.print("Bad request");
				return;
			}
			
			if (!jsonBody.has("chatId")) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(400);
				out.print("Bad request");
				return;
			}
			
			// Make db Request
			Chat chat = ChatDAOImplementation.getInstance().readChat(jsonBody.getInt("chatId"));
			
			if (chat == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(404);
				out.print("Chat request does not exist");
				return;
			}
			
			ChatDAOImplementation.getInstance().acceptChatRequest(chat);
			
			// Response configuration
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "nocache");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			
			// JSON Response
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("id", chat.getId());
			jsonResponse.put("accepted", chat.getAccepted());
			
			// String output		
			out.print(jsonResponse.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Request body
		String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		try {
			JSONObject jsonBody = new JSONObject(body);
			
			// Get Twitter session
			Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
			
			if (twitter == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(401);
				out.print("Not Authorized");
				return;
			}
			
			User user = UserDAOImplementation.getInstance().readUser((int) twitter.getId());
			if (user == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(401);
				out.print("Not Authorized");
				return;
			}
			
			if (!jsonBody.has("thoughtId")) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(400);
				out.print("Bad request");
				return;
			}
			
			if (!jsonBody.has("chatId")) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(400);
				out.print("Bad request");
				return;
			}
			
			// Make db Request
			Chat chat = ChatDAOImplementation.getInstance().readChat(jsonBody.getInt("chatId"));
			
			if (chat == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(404);
				out.print("Chat request does not exist");
				return;
			}
			
			ChatDAOImplementation.getInstance().declineChatRequest(chat);
			
			// Response configuration
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "nocache");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			
			// JSON Response
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("id", chat.getId());
			jsonResponse.put("accepted", chat.getAccepted());
			
			// String output		
			out.print(jsonResponse.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
