package es.upm.dit.geoloc.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.upm.dit.geoloc.dao.model.Thought;

import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;

import com.google.gson.Gson;

/**
 * Servlet implementation class ThoughtServlet
 */
public class ThoughtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThoughtServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. get received JSON data from request
		
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		 ThoughtDAOImplementation pensamiento = new ThoughtDAOImplementation();
		try {
			long UserID = twitter.getId();
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        final Gson gson = new Gson();
        Thought pensa = gson.fromJson(json, Thought.class);
    	try {
			long UserID = twitter.getId();
			pensa.setUserId(UserID);
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    	pensamiento.createThought(pensa);
        
        response.sendRedirect(request.getContextPath() + "/Index.jsp");
        
        return;
        
	}
	
}
