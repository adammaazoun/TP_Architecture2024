package tp3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connexion{
	public static Connection connect() {
		Connection con=null;
		try {
			String url="jdbc:MySQL://localhost:3306/basearchitecture";
			String login="root";
		    String mdp="1234";
		    
			con=DriverManager.getConnection(url,login,mdp);

		}
       catch(SQLException e) {
    	   System.out.println(e);
       }
		if(!con.equals(null))
		System.out.println("connected");
	    return con;
	}
}
