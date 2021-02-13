package es.infolojo.dao;

import java.util.ArrayList;

import es.infolojo.vo.Film;

public interface IFilmDAO {
	public ArrayList<Film> listFilms();
	public boolean rentFilm(String nick, int cod_film);
	public boolean checkFilm(int cod_film);
	public Film getFilm(int cod_film);
	public ArrayList<Film> listUserFilms(String nick);
	public boolean checkUnitsFilms(int cod_film);
	public boolean backFilm(String nick, int film_code);
}
