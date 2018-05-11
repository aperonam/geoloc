package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ChatDAOImplementation;
import es.upm.dit.geoloc.dao.LikeDAOImplementation;
import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.Likee;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class ChatServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			
			// Read Thought
			Thought thought = ThoughtDAOImplementation.getInstance().readThought(jsonBody.getInt("thoughtId"));
			if (thought == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(404);
				out.print("Thought does not exist");
				return;
			}
			
			// See if chat request already exist
			if (ChatDAOImplementation.getInstance().readChat((int) thought.getId(), (int) user.getId()) != null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(200);
				out.print("Like already exist");
				return;
			}
			
			// Create Chat object
			Chat chat = new Chat();
			chat.setUser(user);
			chat.setToUser(thought.getUser());
			chat.setThought(thought);
			
			if (jsonBody.has("message")) {
				chat.setMessage(jsonBody.getString("message"));
			}
			
			// Make db Request
			chat = ChatDAOImplementation.getInstance().requestChat(chat);
			
			// Response configuration
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "nocache");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			
			// JSON Response
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("id", chat.getId());
			jsonResponse.put("message", chat.getMessage());
			
			// String output		
			out.print(jsonResponse.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
