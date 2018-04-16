package es.upm.dit.geoloc.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import es.upm.dit.geoloc.dao.*;
import es.upm.dit.geoloc.dao.model.Thought;

@WebServlet("/getThoughts")
public class GetThoughtsServlet extends HttpServlet {


	    public static void main (String [ ] args) {

	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	    	List<Thought> thoughts = new ArrayList<>();
	    	
	    	thoughts = ThoughtDAOImplementation.getInstance().readThoughts();
	    	request.getSession().setAttribute("thoughts", thoughts);
	    	response.sendRedirect(request.getContextPath() + "/viewThoughts");
	    	
	    	
	    	
	    	
	    	
	    	
	    }
}

