package es.infolojo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.infolojo.dao.FilmDAO;

/**
 * Servlet implementation class ServletreturnFilm
 */
@WebServlet("/ServletreturnFilm")
public class ServletreturnFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletreturnFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Petición asistida con existo");
		String ret = "";
		FilmDAO films = new FilmDAO();
		String nick = request.getParameter("nick");
		int cod_film = Integer.parseInt(request.getParameter("cod_film"));
		
		//if it returns true the movie was returned correctly
		boolean flag = films.backFilm(nick, cod_film);
		
		if(flag == false) {
			ret += "error";
		}
		
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
