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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ThoughtServlet extends HttpServlet {
	
	
	/*
	 * Method used to create a new thought
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GeometryFactory
		// GeometryFactory geometryFactory = new GeometryFactory();
		
		// Request body
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		// Create Thought object
		Thought thought = null;
		try {
			JSONObject jsonBody = new JSONObject(body);
		
			thought = new Thought();
			thought.setText(jsonBody.getString("text"));
			thought.setTag(jsonBody.getString("tag"));
			
			if (request.getParameter("latitude") != null && request.getParameter("longitude") != null) {
				double latitude = Double.parseDouble(jsonBody.getJSONObject("location").getString("latitude"));
				double longitude = Double.parseDouble(jsonBody.getJSONObject("location").getString("longitude"));
				// thought.setLocation(geometryFactory.createPoint(new Coordinate(latitude, longitude)));
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		// Make db Request
		Integer thoughtId = 1; //ThoughtDAOImplementation.getInstance().createThought(thought);
		
		// Response configuration
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// JSON Response
		JSONObject jsonResponse = new JSONObject();
		try {
			jsonResponse.put("id", thoughtId);
			jsonResponse.put("text", thought.getText());
			jsonResponse.put("tag", thought.getTag());
			
			JSONObject location = new JSONObject();
			//location.put("latitude", String.valueOf(thought.getLocation().getCoordinates()[0]));
			//location.put("longitude", String.valueOf(thought.getLocation().getCoordinates()[1]));
			jsonResponse.put("location", location);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// String output		
		out.print(jsonResponse.toString());
	}
	
	/*
	 * Method used to delete a thought
	 * @param request
	 * @param response
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO
	}
	
}
