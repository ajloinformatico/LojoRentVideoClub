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

import es.infolojo.dao.FilmDAO;
import es.infolojo.vo.Film;

/**
 * Servlet implementation class ListUserFilms
 */
@WebServlet("/ListUserFilms")
public class ListUserFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUserFilms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con éxisto");
		FilmDAO films = new FilmDAO();	
		
		String nick = request.getParameter("nick");

		ArrayList<Film> filmsList = films.listUserFilms(nick);
		System.out.println(filmsList);
		
		//Common in two cases
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		//if user havent any film
		if(filmsList.size() < 0) {	
			//return string error
			out.println("error");
			out.flush();
			out.close();
		
		//If user have rentinf films	
		}else {
			/*Return json*/
			Gson gson = new Gson();
			String json = gson.toJson(filmsList);
			out.println(json);
			out.flush();
			out.close();
		}
		
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
