package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.LikeDAOImplementation;
import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.Like;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Twitter;

public class LikeServlet extends HttpServlet {

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
			
			// Read Thought
			Thought thought = ThoughtDAOImplementation.getInstance().readThought(jsonBody.getInt("thought_id"));
			if (thought == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(404);
				out.print("Thought does not exist");
				return;
			}
			
			// Create Like object
			Like like = new Like();
			like.setUser(user);
			like.setThought(thought);
			
			// Make db Request
			Integer likeId = LikeDAOImplementation.getInstance().createLike(like);
			
			// Response configuration
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "nocache");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			
			// JSON Response
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("id", likeId);
			
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
			
			// Get Like object
			Like like = LikeDAOImplementation.getInstance().readLike(jsonBody.getInt("like_id"));
			if (like == null) {
				PrintWriter out = resp.getWriter();
				resp.setStatus(404);
				out.print("Not found");
				return;
			}
			
			// Remove like
			if (like.getUser().getId() == user.getId()) {
				LikeDAOImplementation.getInstance().deleteLike(like);
			} else {
				PrintWriter out = resp.getWriter();
				resp.setStatus(401);
				out.print("Not Authorized");
				return;
			}
			
			// Response configuration
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "nocache");
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			
			// JSON Response
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("id", like.getId());
			
			// String output		
			out.print(jsonResponse.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
