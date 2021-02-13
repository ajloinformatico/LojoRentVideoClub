package es.infolojo.connect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import es.infolojo.utilities.MyDates;


/**
 * DataBase Class
 * 
 * @author antoniojoselojoojeda
 *
 */
public class DBConnection {
	/**Parametros de conexion*/
    static String bd = "videoclub";
    static String login = "root";
    static String password = "";//Input here your user and pass of mariadb
    static String url = "jdbc:mysql://localhost/"+bd;
    Connection connection = null;

    /** Constructor de DbConnection */
    public DBConnection() {
        try{
            //obtenemos el driver de para mysql
            Class.forName("org.mariadb.jdbc.Driver");
            //obtenemos la conexión
            connection = DriverManager.getConnection(url,login,password);

            if (connection!=null){
                //System.out.println("Conexión a base de datos "+bd+" OK\n");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    /**
     * Retoma la conexión
     * @return {Connection} connection
     */
    public Connection getConnection(){
        return connection;
    }

    /**
     * Rompe la conexion
     */
    public void desconectar(){
        connection = null;
    }
    
    /**
     * I can't do multiple inserts in n: m 
     * tables without doing close between each one
     * it could be interesting for the application
     * @param quer: String of the Statement
     */
    public boolean executQ(String quer) {
    	DBConnection con = new DBConnection();
    	try{
            Statement st = con.getConnection().createStatement();
            st.executeQuery(quer);
            st.close();
            con.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            con.desconectar();
            return false;
        }
    }
    
    
   
   
    
    /**
     * Inserta todos los datos por defecto
     */
    public void seeder() {

    	DBConnection con = new DBConnection();
         //Seeder alumnos
         String users = "INSERT INTO users (nick, password, name,  surname, mail, saldo, cliente_premium) VALUES" +
                 "('Juanito_1998', 'pestillo01', 'Juan', 'Álvarez', 'juanito@juanito.com', 1000, true),"+
                 "('Marisa_0010', 'pestillo_01', 'Marisa', 'Rodríguez','marisa@gmail.com',250, false),"+
                 "('Elena_34', 'pestillo_01', 'Elena', 'Lancho', 'elenica@gmail.com', 20, true)";
         //Seeder films
         String films = "INSERT INTO films(cod_film, name_film, price, estreno, n_copias, genero) VALUES" +
                 "(01, 'Mátrix', 1, false, 5, 'accion'),"+
                 "(02, 'Harry Potter', 1, false, 10, 'fantasticas'),"+
                 "(03, 'SpiderMan',1, false, 12, 'accion'),"+
                 "(04, 'Alone in the dark', 2, true, 12, 'terror'),"+
                 "(05, 'Mr Robot', 2, true, 15, 'intriga'),"+
         		 "(06, 'Love', 1, false, 20, 'romantica'),"+
         		 "(07, 'Doctor who', 1, true, 7, 'misterio'),"+
         		 "(08, 'Fast and furius', 1, false, 3, 'conduccion'),"+
         		 "(09, 'Star Wars', 2, true, 10, 'misterio')";
         //Seeder Rent
         
         String rent1 = "INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Juanito_1998', 01, '"+ MyDates.yesterday() +"')";
         String rent2 = "INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Juanito_1998', 03, '"+ MyDates.lastDay() +"')";
         String rent3 = "INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Marisa_0010', 09, '"+ MyDates.lastDay() +"')";
         String rent4 = "INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Elena_34', 01, '"+ MyDates.lastDay() +"')";
         String rent5 = "INSERT INTO rent(nick_fk, cod_film_fk, fecha) VALUES ('Elena_34', 06, '"+ MyDates.threDaysBack() +"')";
        
         
         this.executQ(users);
         this.executQ(films);
         this.executQ(rent1);
         this.executQ(rent2);
         this.executQ(rent3);
         this.executQ(rent4);
         this.executQ(rent5);  
     }
}


