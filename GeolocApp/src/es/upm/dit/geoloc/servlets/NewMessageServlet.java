package es.upm.dit.geoloc.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pusher.rest.Pusher;

import es.upm.dit.geoloc.dao.MessageDAOImplementation;
import es.upm.dit.geoloc.dao.model.Message;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Servlet implementation class NewMessageServlet
 */
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMessageServlet() {
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		 MessageDAOImplementation message = new MessageDAOImplementation();
       BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
       String json = "";
       if(br != null){
           json = br.readLine();
       }
       
       final Gson gson = new Gson();
       Message mensaje = gson.fromJson(json, Message.class);
       
       TimeZone.setDefault(TimeZone.getTimeZone("GMT+2")) ;
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       Date date =new Date();
       
       mensaje.setCreatedAt(dateFormat.format(date));
       
      message.createMessage(mensaje);
      
      
      DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
      mensaje.setCreatedAt(dateFormat2.format(date));
      
      Pusher pusher = new Pusher("521820", "e8eb951d24dae72ff214", "44acbcc9435ada059746");
      pusher.setCluster("eu");

      pusher.trigger("chat", "new-message", mensaje.getJsonObject());
      
	}

}
