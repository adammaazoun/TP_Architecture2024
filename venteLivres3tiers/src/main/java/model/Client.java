package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Connexion;

public class Client extends Utilisateur {
    public ArrayList<String> liste_livre = new ArrayList<String>();

    // Removed static keyword from Connection
    public static Connection c = Connexion.connect();
    

    public static boolean verifClient(String login, String mdp) {
        if (c == null) {
            System.out.println("Connection is null. Cannot proceed.");
            return false;
        }

        try {
            PreparedStatement ps = c.prepareStatement("select * from Client where login=? and mot_de_passe=?");
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void ajoutClient(String login, String mdp) {
        if (c == null) {
            System.out.println("Connection is null. Cannot proceed.");
            return;
        }

        try {
            PreparedStatement ps = c.prepareStatement("insert into client (login, mot_de_passe) values (?,?)");
            ps.setString(1, login);
            ps.setString(2, mdp);
            int b = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
