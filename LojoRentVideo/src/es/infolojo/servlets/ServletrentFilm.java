package es.infolojo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.infolojo.dao.FilmDAO;
import es.infolojo.dao.UserDAO;
import es.infolojo.vo.Film;
import es.infolojo.vo.User;

/**
 * Servlet implementation class ServletrentFilm
 */
@WebServlet("/ServletrentFilm")
public class ServletrentFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletrentFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Petición asistida con éxito");
		
		//Instances of necessary objects
		String ret = "";
		UserDAO users = new UserDAO();
		FilmDAO films = new FilmDAO();
		boolean flag = false;
		
		
		int cod_film = Integer.parseInt(request.getParameter("cod_film"));
		String nick = request.getParameter("nick");
		
		//First check user
		if(users.checkUserexists(nick) || films.checkFilm(cod_film)) {
			
			//Check user units
			if(films.checkUnitsFilms(cod_film)) {
				
				if(films.rentFilm(nick, cod_film)) {
					flag = true; //pass all the conditions
				}else {
					ret+= "haveit"; //user allready have the film
				}
			}else {
				ret += "units"; //there isnt more units
			}
		}else { 
			ret += "error"; //Uknow error
		}
		
		
		
		
		//if it returns false the insert was unsuccessfull user have this rent doid
		if(flag == true) {
			ret += "rented";
		}
		
		
		//return ret
		System.out.println(ret);
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
