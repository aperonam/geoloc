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
 * Servlet implementation class StatusServlet
 */
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idChat = Integer.parseInt(request.getParameter("status"));
		ChatDAOImplementation chat = new ChatDAOImplementation();
		
		chat.changeStatus(idChat); 
		
		 response.sendRedirect(request.getContextPath() + "/Chat.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idChat = (int) request.getAttribute("value");
		ChatDAOImplementation chat = new ChatDAOImplementation();
		
		chat.changeStatus(idChat); 
		
		 response.sendRedirect(request.getContextPath() + "/Chat.jsp");
		
	}

}
