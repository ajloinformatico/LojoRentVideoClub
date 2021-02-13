package es.infolojo.system;

import es.infolojo.connect.DBConnection;
import es.infolojo.dao.FilmDAO;
import es.infolojo.dao.UserDAO;
import es.infolojo.utilities.MyDates;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println(MyDates.yesterday());
		System.out.println(MyDates.twoDaysBack());
		System.out.println(MyDates.threDaysBack());
		System.out.println(MyDates.lastDay());
				
		DBConnection db = new DBConnection();
		db.seeder();
		//UserDAO u= new UserDAO();
		//System.out.println(u.getUsers());
		//FilmDAO films = new FilmDAO();
		//System.out.println(films.listFilms());
		//System.out.println();
	}

}
