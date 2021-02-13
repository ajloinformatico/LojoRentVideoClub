package es.infolojo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import es.infolojo.dao.UserDAO;
import es.infolojo.vo.User;

/**
 * Servlet implementation class UserLoadFormUpdate
 * Load user update form
 */
@WebServlet("/UserLoadFormUpdate")
public class ServletUserLoadForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUserLoadForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String nick = request.getParameter("nick");
		UserDAO users = new UserDAO();
		
		//save to a list even though I only need one user to return it with json
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(users.getUser(nick));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(userList);
		out.println(json);
		out.flush();
		out.close();
		//TODO: HACER EL METODO EN EL PROFILE PARA CARGAR EN EL FORMULARIO LOS
		//DADTOS
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
