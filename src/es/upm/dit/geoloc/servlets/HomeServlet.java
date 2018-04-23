package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.Twitter;

@WebServlet("/")
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
		
		// Get thoughts
		List<Thought> thoughts;
		
		thoughts = ThoughtDAOImplementation.getInstance().readThoughtsNear(Double.parseDouble(req.getParameter("latitude")), Double.parseDouble(req.getParameter("longitude")));
		
		if (thoughts == null) {
			thoughts = new ArrayList<Thought>();
		}
		
		req.getSession().setAttribute("thoughts", thoughts);
		req.getSession().setAttribute("twitter", twitter);
		
		resp.setContentType("text/html");
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.sendRedirect(req.getContextPath() + "/Index.jsp");
		
	}
	
}
