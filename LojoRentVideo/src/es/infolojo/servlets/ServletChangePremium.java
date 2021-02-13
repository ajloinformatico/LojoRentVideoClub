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
 * Servlet implementation class ServletChangePremium
 */
@WebServlet("/ServletChangePremium")
public class ServletChangePremium extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletChangePremium() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ret = "";
		String nick = request.getParameter("nick");
		UserDAO users = new UserDAO();
		
		if(users.updatePremiun(nick) == false) {
			ret += "error";
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
