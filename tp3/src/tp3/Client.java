package tp3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Client extends Utilisateur {
	public ArrayList<String> liste_livre=new ArrayList<String>();
	static Connection c= Connexion.connect();
	public static boolean verifier_mdp(String adr,String mdp) throws SQLException {
		
		String query = "SELECT mot_de_passe FROM client WHERE login = ?";
		PreparedStatement preparedStatement = c.prepareStatement(query);
		preparedStatement.setString(1, adr);
		ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) return true;
				else return false;
					
	}
}
