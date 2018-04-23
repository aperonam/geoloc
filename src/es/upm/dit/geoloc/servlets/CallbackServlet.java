package es.upm.dit.geoloc.servlets;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.User;

import java.io.IOException;

@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {

	/*
	 * Method that manages the Twitter callback
	 * @param request
	 * @param response
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// Get twitter session
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		
		// Verify
		RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
		String verifier = request.getParameter("oauth_verifier");
		
		try {
			twitter.getOAuthAccessToken(requestToken, verifier);
			request.getSession().removeAttribute("requestToken");
		} catch (TwitterException e) {
			throw new ServletException(e);
		}
		
		try {
			User user = UserDAOImplementation.getInstance().readUser(twitter.getId());
			
			// Check if user exist
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/Index.jsp");
				return;
			}
			
			// Create user if doesn't exist
			user = new User();
			user.setId((int) twitter.getId());
			
			// Db request
			UserDAOImplementation.getInstance().createUser(user);
			
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Redirect to index
		response.sendRedirect("home");
    }
    
}
