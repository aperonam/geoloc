package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.UserDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.model.User;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class HomeServlet extends HttpServlet {

	/*
	 * Method for Home page requests
	 * @param request
	 * @param response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get Twitter session
		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		
		if (twitter == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		
		try {
			User user = UserDAOImplementation.getInstance().readUser((int) twitter.getId());
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/login.jsp");
				return;
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get thoughts
		List<Thought> thoughts = null;
		
		if (req.getParameter("latitude") != null && req.getParameter("longitude") != null) {
			thoughts = ThoughtDAOImplementation.getInstance().readThoughtsNear(Double.parseDouble(req.getParameter("latitude")), Double.parseDouble(req.getParameter("longitude")));
		}
		
		
		if (thoughts == null) {
			thoughts = new ArrayList<Thought>();
			Thought t = new Thought();
			t.setText("Comentario de prueba");
			t.setTag("UPM");
			t.setLatitude(40.4478104);
			t.setLongitude(-3.716025);
			thoughts.add(t);
		}
		
		req.getSession().setAttribute("thoughts", thoughts);
		req.getSession().setAttribute("twitter", twitter);
		
		resp.setContentType("text/html");
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.sendRedirect(req.getContextPath() + "/Index.jsp");
		
	}
	
}
