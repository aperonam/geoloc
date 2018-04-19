package es.upm.dit.geoloc.servlets;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;

import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.User;



public class CallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1657390011452788111L;
    
    public static void main (String [ ] args) {
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        try {
            twitter.getOAuthAccessToken(requestToken, verifier);
            request.getSession().removeAttribute("requestToken");
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        UserDAOImplementation usuario = new UserDAOImplementation();
    	User user = new User();

    	try {
			long UserID = twitter.getId();
			request.setAttribute("UserID", UserID);
			user.setUserID(UserID);
			usuario.createUser(user);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect(request.getContextPath() + "/Index.jsp");
    }
}
