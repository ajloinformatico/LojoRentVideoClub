package es.infolojo.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



import es.infolojo.connect.DBConnection;
import es.infolojo.utilities.MyDates;
import es.infolojo.vo.Film;
import es.infolojo.vo.User;
/**
 * 
 * Film DAO
 * @author antoniojoselojoojeda
 *
 */
public class FilmDAO implements IFilmDAO{
	private ArrayList<Film> films;
	
	
	//Constructor
	public FilmDAO() {
		super();
		this.films = new ArrayList<Film>();
		this.loadFilms();
		
	}
	
	//Getters & Setters
	public ArrayList<Film> getFilms() {
		return this.films;
	}
	
	public void setFilms(ArrayList<Film> eFilms) {
		this.films = eFilms;
	}
	
	/**
	 * Load all films
	 */
	private void loadFilms() {
		DBConnection con = new DBConnection();
		String sql = "SELECT * FROM FILMS";
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet re = st.executeQuery(sql);
			
			while(re.next()) {
				this.films.add(new Film(re.getInt("cod_film"), re.getString("name_film"), re.getDouble("price"), re.getBoolean("estreno"),
						re.getInt("n_copias"), re.getString("genero")));
				
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			this.films = new ArrayList<Film>();
		}
	}
	
	
	/**
	 * 
	 * Serach internal for film
	 * 
	 */
	private Film searchFilm(int cod_film){
		for(Film f: this.films) {
			if(f.getCod_film() == cod_film) {
				return f;
			}
		}
		return null;
	}
	
	
	
	/**
	 * Array of availables films 
	 */
	@Override
	public ArrayList<Film> listFilms() {
		// TODO Auto-generated method stub
		ArrayList<Film> result = new ArrayList<Film>();

		//if there are copies add to the list
		for (Film f : this.films) {
			if(f.getN_copias() > 0) {
				result.add(f);
			}
		}
		return result;
	}

	/**
	 * Search a film
	 */
	@Override
	public boolean checkFilm(int cod_film) {
		for(Film f : this.films) {
			if(f.getCod_film() == cod_film) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Rent a film
	 * @param nick {String of user}
	 * @param cod_film {int of cod}
	 * @return {boolean}: true for renting, false for not renting
	 */
	@Override
	public boolean rentFilm(String nick, int cod_film) {
		DBConnection db = new DBConnection();
		//Instance user
		UserDAO users = new UserDAO();
		User u = users.getUser(nick);
		
		//Instance film
		Film f = this.getFilm(cod_film);
		
		double newSaldo;
		//depending on whether it is premium or not it is subtracted
		if(u.isCliente_premium()) {
			newSaldo = u.getSaldo() -  (10 * f.getPrice()/100);
		}else {
			newSaldo = u.getSaldo() - f.getPrice();
		}
		String sql = "INSERT INTO RENT VALUES('"+nick+"',"+cod_film+",'"+MyDates.today()+"')";
		String sql2 = "UPDATE users SET saldo = "+newSaldo+" WHERE nick = '"+nick+"' ";
		return (db.executQ(sql) && db.executQ(sql2)); //if it run returns true, if get exception return false
	}
	
	
	/**
	 * check units and substract one copie
	 * @param {int} cod_film: cod of the film
	 * @return {boolean}: true if query is execute and false if not
	 */
	@Override
	public boolean checkUnitsFilms(int cod_film) {
		DBConnection db = new DBConnection();
		Film f = this.getFilm(cod_film);
		int newCopies = f.getN_copias()-1;
		
		if(newCopies < 0) {
			return false;
		}else {
			//If the query is executed correctly, return true if not false
			return db.executQ("UPDATE films SET n_copias = " + newCopies+" WHERE cod_film =" + f.getCod_film()+""); //update database with nre value
		}
		
	}
	
	
	
	
	/**
	 * Return just one film only call if you are shure that film exists
	 */
	@Override
	public Film getFilm(int cod_film) {
		for(Film f : this.films) {
			if(f.getCod_film() == cod_film) {
				return f;
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * I do the query directly to bring up a list of movies
	 */
	@SuppressWarnings("finally")
	@Override
	public ArrayList<Film> listUserFilms (String nick){
		ArrayList<Film> result = new ArrayList<Film>();
		
		
		DBConnection con = new DBConnection();
		String sql = "SELECT * FROM rent WHERE nick_fk = '"+nick+"'";
		
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet re = st.executeQuery(sql);
			
			while(re.next()) {
				
				//if user is in the relationship
				//TODO INCLUIR ESTO EN UN MÉTODO
				for(Film f : this.films) {
					//result cannot repeat films
					if(f.getCod_film() == re.getInt("cod_film_fk") && !result.contains(f)) {
						result.add(f);
					}
						
				}
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			result = new ArrayList<Film>();
		}finally {
			con.desconectar();
			return result;
		}
	}
	
	
	public boolean backFilm(String nick,int film_code){
		DBConnection db = new DBConnection();
		Film f = this.getFilm(film_code);
		int newCopies = f.getN_copias()+1;
		String sql = "UPDATE films SET n_copias = " + newCopies+" WHERE cod_film =" + f.getCod_film()+"";
		String sql2 = "DELETE FROM RENT WHERE nick_fk = '"+nick+"' AND cod_film_fk = "+film_code;
		
		return (db.executQ(sql) && db.executQ(sql2));
		
	}
	
	
	
	//ToString
	@Override
	public String toString() {
		return "FilmDAO [films=" + films + "]";
	}
	
	
	
	
	
	
	
	
	

}


