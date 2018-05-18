package es.upm.dit.geoloc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.geoloc.dao.ChatDAOImplementation;
import es.upm.dit.geoloc.dao.model.Chat;

/**
 * Servlet implementation class ChatDeleteServlet
 */
public class ChatDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idChat = Integer.parseInt(request.getParameter("status"));
		ChatDAOImplementation chat = new ChatDAOImplementation();
		Chat xat = chat.readChat(idChat);
		
		 chat.deleteChat(xat);
		
		 response.sendRedirect(request.getContextPath() + "/Chat.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
