package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Servlet implementation class FiltrarMisMarcadoresServlet
 */
public class FiltrarMisMarcadoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarMisMarcadoresServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThoughtDAOImplementation pensamiento = new ThoughtDAOImplementation();
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		List<Thought> ArrayThought = new ArrayList<Thought>();
		
		String tag = request.getParameter("tag");
		System.out.println(tag);
		
		try {
			ArrayThought = pensamiento.getTagsMisMarcadores(tag, twitter.getId());
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("lista", ArrayThought);
		
		RequestDispatcher dd = request.getRequestDispatcher("TagsMisMarcadores.jsp");

		dd.forward(request, response);
	}

}
