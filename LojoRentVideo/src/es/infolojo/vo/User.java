package es.infolojo.vo;


public class User {
	private String nick; //max 30
	private String password; //max 8
	private String name; //max 60
	private String surname; //max 60
	private String mail; //max 60
	private double saldo;
	private boolean cliente_premium;
	
	
	//Constructor
	public User(String nick, String password, String name, String surname, String mail, double saldo,
			boolean cliente_premium) {
		super();
		this.nick = nick;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.saldo = saldo;
		this.cliente_premium = cliente_premium;
	}
	
	//Constructor con registro
	//el saldo es de 1000 y se podrá cambiar junto
	//con cliente premium en el menú del usuario
	public User(String nick, String password, String name, String surname, String mail) {
		super();
		this.nick = nick;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.saldo = 1000;
		this.cliente_premium = false;;
	}

	
	//Getters && Setters
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isCliente_premium() {
		return cliente_premium;
	}

	public void setCliente_premium(boolean cliente_premium) {
		this.cliente_premium = cliente_premium;
	}

	//ToString()
	@Override
	public String toString() {
		return "User [nick=" + nick + ", password=" + password + ", name=" + name + ", surname=" + surname + ", mail="
				+ mail + ", saldo=" + saldo + ", cliente_premium=" + cliente_premium +"]";
	}
}
