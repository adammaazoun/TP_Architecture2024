package model;
import java.io.*;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public abstract class Utilisateur {
	public String login=new String();
	public String mdp=new String();
	static Connection c= Connexion.connect();
	public static void affiche_liste() throws SQLException{
			Statement st=c.createStatement();
			ResultSet rs=st.executeQuery("select titre from livre");
			while (rs.next()) {
				System.out.println(rs.getString("titre"));
			}
	}
	public static void chercher_livre(String auteur) throws SQLException{
		Statement st=c.createStatement();
		ResultSet rs=st.executeQuery("select titre from livre where auteur='"+auteur+"'");
		while (rs.next()) {
			System.out.println(rs.getString("titre"));
		}
	 }
}