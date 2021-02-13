package es.infolojo.vo;


/**
 * Class for Film
 * @author antoniojoselojoojeda
 *
 */
public class Film {
	private int cod_film;
	private String name_film;
	private double price;
	private boolean estreno;
	private int n_copias; //atributo constante
	private String genero;
	
	//No hay código porque consideré que este debía
	//ser autogenerado, esto simplifica el uso
	//de la aplicación y facilita el mantenimiento en
	//la base de datos
	
	public Film(int cod_film, String name_film, double price, boolean estreno, int n_copias, String genero) {
		super();
		this.cod_film = cod_film;
		this.name_film = name_film;
		this.price = price;
		this.estreno = estreno;
		this.n_copias = n_copias;
		this.genero = genero;
	}

	
	//Getters & Setters
	public int getCod_film() {
		return cod_film;
	}

	public void setCod_film(int cod_film) {
		this.cod_film = cod_film;
	}

	public String getName_film() {
		return name_film;
	}

	public void setName_film(String name_film) {
		this.name_film = name_film;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isEstreno() {
		return estreno;
	}

	public void setEstreno(boolean estreno) {
		this.estreno = estreno;
	}

	public int getN_copias() {
		return n_copias;
	}

	public void setN_copias(int n_copias) {
		this.n_copias = n_copias;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
	/**
	 * ToString
	 */
	@Override
	public String toString() {
		return "Film [cod_film=" + cod_film + ", name_film=" + name_film + ", price=" + price + ", estreno=" + estreno
				+ ", n_copias=" + n_copias + ", genero=" + genero + "]";
	}
	
}
