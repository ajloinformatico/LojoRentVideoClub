package es.infolojo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.infolojo.dao.UserDAO;
import es.infolojo.vo.User;

/**
 * Servlet implementation class ServletUserUpdateForm
 */
@WebServlet("/ServletUserUpdateForm")
public class ServletUserUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUserUpdateForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con éxito");
		/*Result*/
		String ret = "";
		
		/*Ajax Params*/
		String oldNick = request.getParameter("oldNick");
		String nick = request.getParameter("nick");
		nick = nick.substring(0,1).toUpperCase() + nick.substring(1).toLowerCase();
        String pass = request.getParameter("pass");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String mail = request.getParameter("mail");
        double saldo = Double.parseDouble(request.getParameter("saldo"));
		
        /*User*/
        UserDAO users = new UserDAO();
        User old = users.getUser(oldNick);
        User newUser = new User(nick, pass, name, surname, mail, saldo, false);
        
        //Exequte update
        if(users.updateUser(old, newUser) == false) {
        	ret += "error";
        }else {
        	ret += nick;
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
