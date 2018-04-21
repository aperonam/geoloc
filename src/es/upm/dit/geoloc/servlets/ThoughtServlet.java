package es.upm.dit.geoloc.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vividsolutions.jts.geom.GeometryFactory;

import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ThoughtServlet extends HttpServlet {
	
	
	/*
	 * Method that manages the "Create Thought" request
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Request body
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		try {
			JSONObject jsonBody = new JSONObject(body);
			
			// Get Twitter session
			Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
			
			if (twitter == null) {
				PrintWriter out = response.getWriter();
				response.setStatus(401);
				out.print("Not Authorized");
				return;
			}
			
			// Create Thought object
			Thought thought = new Thought();
			thought.setUserId(twitter.getId());
			
			if (jsonBody.getString("text") == null) {
				PrintWriter out = response.getWriter();
				response.setStatus(400);
				out.print("Bad request");
				return;
			}
			
			thought.setText(jsonBody.getString("text"));
			thought.setTag(jsonBody.getString("tag"));
			
			if (request.getParameter("latitude") != null && request.getParameter("longitude") != null) {
				double latitude = Double.parseDouble(jsonBody.getJSONObject("location").getString("latitude"));
				thought.setLatitude(latitude);
				double longitude = Double.parseDouble(jsonBody.getJSONObject("location").getString("longitude"));
				thought.setLongitude(longitude);
			} else {
				PrintWriter out = response.getWriter();
				response.setStatus(400);
				out.print("Bad request");
				return;
			}
			
			// Make db Request
			Integer thoughtId = ThoughtDAOImplementation.getInstance().createThought(thought);
			
			// Response configuration
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			
			// JSON Response
			JSONObject jsonResponse = new JSONObject();
			
			jsonResponse.put("id", thoughtId);
			jsonResponse.put("text", thought.getText());
			jsonResponse.put("tag", thought.getTag());
			
			JSONObject location = new JSONObject();
			location.put("latitude", String.valueOf(thought.getLatitude()));
			location.put("longitude", String.valueOf(thought.getLongitude()));
			jsonResponse.put("location", location);
			
			// String output		
			out.print(jsonResponse.toString());
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Method that manages the "Delete Thought" request
	 * @param request
	 * @param response
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get Twitter session
		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		
		if (twitter == null) {
			PrintWriter out = resp.getWriter();
			resp.setStatus(401);
			out.print("Not Authorized");
			return;
		}
		
		Integer thoughtId = Integer.parseInt(req.getParameter("id"));
		
		Thought thought = ThoughtDAOImplementation.getInstance().readThought(thoughtId);
		
		if (thought == null) {
			PrintWriter out = resp.getWriter();
			resp.setStatus(404);
			out.print("Thought with id=" + thoughtId + " does not exist");
			return;
		}
		
		// Response configuration
		resp.setContentType("application/json");
		resp.setHeader("Cache-Control", "nocache");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		try {
			if (thought.getUserId() == twitter.getId()) {
				// Delete thought from db
				ThoughtDAOImplementation.getInstance().deleteThought(thought);;
				
				resp.setStatus(200);
				out.print("{ status: \"200\" }");
			} else {
				resp.setStatus(401);
				out.print("{ status: \"401\" }");
			}
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
