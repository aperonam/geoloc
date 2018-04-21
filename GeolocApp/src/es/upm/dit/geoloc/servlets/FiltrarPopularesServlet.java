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

/**
 * Servlet implementation class FiltrarPopularesServlet
 */
public class FiltrarPopularesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarPopularesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThoughtDAOImplementation pensamiento = new ThoughtDAOImplementation();
		List<Thought> ArrayThought = new ArrayList<Thought>();
		
		String tag = request.getParameter("tag");
		System.out.println(tag);
		
		ArrayThought = pensamiento.getTagsPopulares(tag);
		
		request.getSession().setAttribute("lista", ArrayThought);
		
		RequestDispatcher dd = request.getRequestDispatcher("TagsPopulares.jsp");

		dd.forward(request, response);
		
	}

}
