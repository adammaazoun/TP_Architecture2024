package model;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Connexion;
public class Admin extends Utilisateur {
	
	public static Connection c = Connexion.connect();

	public static boolean verifAdmin(String login, String mdp) {
		try {
            PreparedStatement ps = c.prepareStatement("select * from admin where login=? and mot_de_passe=?");
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
