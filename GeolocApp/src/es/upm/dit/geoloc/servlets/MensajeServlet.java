package es.upm.dit.geoloc.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.upm.dit.geoloc.dao.model.Message;
import es.upm.dit.geoloc.dao.model.Thought;
import es.upm.dit.geoloc.dao.MessageDAOImplementation;

import com.google.gson.Gson;

/**
 * Servlet implementation class MensajeServlet
 */
public class MensajeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MensajeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		response.setContentType("text/html;charset=UTF-8");
		response.setContentType("application/json");
		MessageDAOImplementation message = new MessageDAOImplementation(); 
		List<Message> ArrayMessage = new ArrayList<Message>();
		try {
			long UserId = twitter.getId();
			ArrayMessage = message.getMessage();
		} catch (IllegalStateException | TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			String jsonObject = new Gson().toJson(ArrayMessage);
			response.getWriter().write(jsonObject);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
