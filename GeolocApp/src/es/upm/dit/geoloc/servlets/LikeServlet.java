package es.upm.dit.geoloc.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.model.Likes;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import es.upm.dit.geoloc.dao.LikesDAOImplementation;
import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;

/**
 * Servlet implementation class LikeServlet
 */

public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("value"));

		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		ThoughtDAOImplementation pensamiento = new ThoughtDAOImplementation();
		LikesDAOImplementation l = new LikesDAOImplementation();
		Thought pen = pensamiento.readThought(id);
		
		Likes likes = new Likes();
		likes.setStatus(1);
		likes.setThoughtId(id);
		try {
			likes.setUserId(twitter.getId());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.createLikes(likes);
		
		
		
		try {
			if(l.readLikes(id, twitter.getId()).getStatus() == 1) {
				pensamiento.changeLike(pen);
			}
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			l.changeStatus(id, twitter.getId());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		

}
