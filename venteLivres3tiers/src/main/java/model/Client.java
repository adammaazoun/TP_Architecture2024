package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Client extends Utilisateur {
	public ArrayList<String> liste_livre=new ArrayList<String>();
	static Connection c= Connexion.connect();
	public static boolean verifier_mdp(String adr,String mdp) throws SQLException {
		
				Statement st=c.createStatement();
				ResultSet rs=st.executeQuery("select mot_de_passe from client where login = "+adr);
				if (rs.next()) return true;
				else return false;
					
	}
}
