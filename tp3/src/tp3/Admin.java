package tp3;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin extends Utilisateur {
	
	public static boolean verifier_mdp(String adr,String mdp) throws SQLException {
		
		
		String query = "SELECT mot_de_passe FROM admin WHERE login = ?";
		PreparedStatement preparedStatement = c.prepareStatement(query);
		preparedStatement.setString(1, adr);
		ResultSet rs = preparedStatement.executeQuery();		if (rs.next()) return true;
		else return false;
			
}
	
	public static void ajout_livre(String isbn,String titre,String auteur,String annee,String prix) throws IOException{
		 
		 
		 try {
			PreparedStatement ps=c.prepareStatement("insert into livre (isbn,titre,auteur,annee,prix) VAlues (?,?,?,?,?) ");
			ps.setString(1,isbn);
			ps.setString(2,titre);
			ps.setString(3,auteur);
			ps.setDouble(4,Float.parseFloat(annee));
			ps.setDouble(5,Float.parseFloat(prix));
			int n=ps.executeUpdate();
			System.out.println("added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
	
	public static void supprimer_livre(String isbn) throws IOException {
		PreparedStatement ps;
		try {
			ps = c.prepareStatement("delete from livre where isbn= ? ");
			ps.setString(1,isbn);
			int n=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
}
