package es.infolojo.dao;

import es.infolojo.vo.User;

public interface IUserDAO {
	public boolean loging(String nick, String password);
	public boolean signin(String nick, String password, String name, String surname, String mail);
	public boolean checkUserexists(String nick);
	public User getUser(String nick);
	public boolean updateUser(User old, User newUser);
	public boolean updatePremiun(String nick);
	public boolean returnAllFilms(String nick);
	public boolean deleteUser(String nick);

}
