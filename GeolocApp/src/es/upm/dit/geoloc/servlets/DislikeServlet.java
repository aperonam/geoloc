package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.LikesDAOImplementation;
import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Servlet implementation class DislikeServlet
 */

public class DislikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DislikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("value"));
		ThoughtDAOImplementation pensamiento = new ThoughtDAOImplementation();
		LikesDAOImplementation l = new LikesDAOImplementation();
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		
		Thought pen = pensamiento.readThought(id);
		
		pensamiento.changeDislike(pen);
		
		try {
			if(l.readLikes(id, twitter.getId()).getStatus() == 2) {
			l.changeStatus2(id, twitter.getId());
			}
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}