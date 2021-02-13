package es.infolojo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.infolojo.dao.UserDAO;

/**
 * Servlet implementation class ServletSign
 */
@WebServlet("/ServletSign")
public class ServletSign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con exito");
		String ret = "error";
		
		UserDAO user = new UserDAO();
		
		String nick = request.getParameter("nick");

		//always save with the first in capital letters
		nick = nick.substring(0,1).toUpperCase() + nick.substring(1).toLowerCase();
		
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String mail = request.getParameter("mail");
		
		boolean flag = false;
		
		if(user.signin(nick, password, name, surname, mail) == true) {
			flag = true;
		}	
			
		if(flag == true) {
			ret = nick;
		}
		//Type of content for the response
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(ret);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
