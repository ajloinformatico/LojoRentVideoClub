package es.infolojo.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.infolojo.connect.DBConnection;
import es.infolojo.vo.User;


/**
 * 
 * User Implement
 * 
 * @author antoniojoselojoojeda
 *
 */
public class UserDAO implements IUserDAO{
	private ArrayList<User> users;
	
	
	/**
	 * Constructor
	 */
	public UserDAO() {
		super();
		this.users = new ArrayList<User>();
		this.loadUsers();
	}
	
	//Getters & Setters
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	
	
	/**
	 * Load all uses
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	private void loadUsers() {
		DBConnection con = new DBConnection();
		String sql = "SELECT * FROM USERS";
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet re = st.executeQuery(sql);
			while(re.next()) {
				this.users.add(new User(re.getString("nick"), re.getString("password"), re.getString("name"), re.getString("surname"),
						re.getString("mail"), re.getDouble("saldo"), re.getBoolean("cliente_premium")));
			}
			
		}catch(Exception e){
			this.users = new ArrayList<User>();
		}
		
	}
	

	
	
	
	
	
	
	@SuppressWarnings("finally")
	@Override
	/**
	 * Check user and password if user existes return true
	 * else if user dont exist return false
	 * @reutrn {boolean} : true exists, false dont exist
	 */
	public boolean loging(String nick, String password) {
		DBConnection con = new DBConnection();
		boolean result = false;
		String sql = "SELECT nick, password from users";
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet re = st.executeQuery(sql);
			while(re.next()) {
				if(re.getString("nick").equals(nick)) {
					if(re.getString("password").equals(password)) {
						result = true;
					}
				}
			}	
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			
		}finally {
			con.desconectar();
			return result;
		}
	}
	
	
	/**
	 * Insert a new User
	 * 
	 */
	@Override
	public boolean signin(String nick, String password, String name, String surname, String mail) {
		// TODO Auto-generated method stub
		DBConnection con = new DBConnection();
		User newUser = new User(nick, password, name, surname, mail, 500, false);
		for(User u : this.users) {
			//if new user or nick or mail is on the system
			if(nick.equals(u.getNick()) || mail.equals(u.getMail())) {
				//System.out.println("found");
				return false;
			}
		}
		//String sql = "INSERT INTO users VALUES('"+nick+"', '"+password+"', '"+name+"', '"+surname+"', '"+mail+"', "+500+", "+false+",)";
		con.executQ("INSERT INTO users VALUES('"+nick+"', '"+password+"', '"+name+"', '"+surname+"', '"+mail+"', "+20+", "+false+")");
		this.users.add(newUser);
		System.out.println(this.users);
		return true;
	}
	
	
	/**
	 * Check if an user exist
	 */
	@Override
	public boolean checkUserexists(String nick) {
		for(User u: this.users) {
			if(u.getNick().equals(nick)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Exqute a query to update an user
	 */
	@Override
	public boolean updateUser(User old, User newUser) {
		DBConnection db = new DBConnection();
		return db.executQ("UPDATE users SET nick = '"+newUser.getNick()+"' , password = '"+newUser.getPassword()+"' , "
				+ "surname = '"+newUser.getSurname()+"' , mail = '"+newUser.getMail()+"' , saldo = "+newUser.getSaldo()+ " "
				+ "WHERE nick = '"+ old.getNick() + "' ");
	}
	
	/**
	 * Update a premium
	 */
	@Override
	public boolean updatePremiun(String nick) {
		DBConnection db = new DBConnection();
		User u = this.getUser(nick);
		
		//capture the value of the premium customer to put it on the contrary
		boolean premium;
		if(u.isCliente_premium()) {
			premium = false;
		}else {
			premium = true;
		}
		return db.executQ("UPDATE users SET cliente_premium = "+premium+" WHERE nick = '"+nick+"' ");
	}
	

	
	
	/**
	 * 
	 * and returning the rented unit of each movie
	 */
	@Override
	public boolean returnAllFilms(String nick) {
		DBConnection con = new DBConnection();
		FilmDAO films = new FilmDAO();
		String sql = "SELECT * FROM rent WHERE nick_fk = '"+nick+"' ";
		
		//loop on user a query on rent while executing returnfilm ()
		try {
			Statement st = con.getConnection().createStatement();
			ResultSet re = st.executeQuery(sql);
			while(re.next()) {
				if(re.getString("nick_fk").equals(nick)) {
					 films.backFilm(nick, re.getInt("cod_film_fk"));
				}
			}
			return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Delete an user
	 */
	@Override
	public boolean deleteUser(String nick) {
		//if returns query are returned
		if(this.returnAllFilms(nick)) {
			DBConnection con = new DBConnection();
			return con.executQ("DELETE FROM users WHERE nick = '"+nick+"' ");
		}
		return false;
	}
	
	
	
	/**
	 * 
	 */
	@Override
	public User getUser(String nick) {
		for(User u : this.users) {
			if(u.getNick().equals(nick)) {
				return u;
			}
		}
		return null;
	}
	

}
