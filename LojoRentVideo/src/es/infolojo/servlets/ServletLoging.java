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
 * Servlet implementation class ServletLoging
 */
@WebServlet("/ServletLoging")
public class ServletLoging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoging() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con exito");
		
		//Result
		String ret = "";
		
		//User implemment Instance
		UserDAO user = new UserDAO();
		
		
		String name = request.getParameter("nick").toUpperCase();
		//Capitalize name
		name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

		
		String pass = request.getParameter("pass");
	
		//Change response in function of loging()
		if(user.loging(name, pass)) {
			ret = name;
			//after check it redirect to the new page
		}else {
			ret = "error";
			
			//show error in a message
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
