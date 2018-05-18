package es.upm.dit.geoloc.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ChatDAOImplementation;
import es.upm.dit.geoloc.dao.ThoughtDAOImplementation;
import es.upm.dit.geoloc.dao.ChatDAOImplementation;
import es.upm.dit.geoloc.dao.model.Chat;
import es.upm.dit.geoloc.dao.model.Thought;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.google.gson.Gson;

/**
 * Servlet implementation class ChatServlet
 */
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
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

		ChatDAOImplementation chat = new ChatDAOImplementation();
		ThoughtDAOImplementation thought = new ThoughtDAOImplementation();
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	        }
	       
	        final Gson gson = new Gson();
	        Chat ChatT = gson.fromJson(json, Chat.class);
	        
        	Thought pen = thought.readThought((int)ChatT.getThoughtId());        	
        	long UserId1 = pen.getUserId();
        	ChatT.setUserId1(UserId1);
	        try {
				ChatT.setUserId2(twitter.getId());
				ChatT.setStatus(1);
			} catch (IllegalStateException | TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        chat.createChat(ChatT);
		
	}

}
