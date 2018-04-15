package es.upm.dit.geoloc.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class PostThoughtServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create Thought object
		Thought thought = new Thought();
        thought.setText(request.getParameter("text"));
        thought.setTag(request.getParameter("tag"));
        
        // Make db Request
        Integer thoughtId = ThoughtDAOImplementation.getInstance().createThought(thought);
        
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
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// String output		
		out.print(jsonResponse.toString());
    }
	
}
