package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Connexion;

public abstract class Utilisateur {
	
	public String login=new String();
	public String mdp=new String();
	static Connection c= Connexion.connect();
	
	public static ArrayList<String[]> affiche_liste() throws SQLException{
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("select* from livre");
			ArrayList<String[]> l =new ArrayList<String[]>();
			while (rs.next()) {
				String[] k= {rs.getString("ISBN"),rs.getString("titre"),rs.getString("auteur"),String.valueOf(rs.getInt("annee")),String.valueOf(rs.getInt("prix"))};
				l.add(k);
			}
			return l;
	}
	public static ArrayList<String[]> chercher_livre(String auteur) throws SQLException{
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery("select* from livre where auteur='"+auteur+"'");
		ArrayList<String[]> l =new ArrayList<String[]>();
		while (rs.next()) {
			System.out.println(rs.getString("titre"));
			String[] k= {rs.getString("ISBN"),rs.getString("titre"),rs.getString("auteur"),String.valueOf(rs.getInt("annee")),String.valueOf(rs.getInt("prix"))};
			l.add(k);
		}
		return l;
	 }
}
