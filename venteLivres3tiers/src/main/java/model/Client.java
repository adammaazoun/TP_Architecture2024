package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Client extends Utilisateur {
    public ArrayList<String> liste_livre = new ArrayList<String>();
    static Connection c;

    static {
        try {
            c = Connexion.connect();
        } catch (ClassNotFoundException e) {
            // Handle the exception (e.g., log it, take alternative action)
            e.printStackTrace(); // Example of handling (printing stack trace)
        }
    }

    public static boolean verifier_mdp(String adr, String mdp) {
        if (c == null) {
            // Connection is not initialized, handle accordingly
            return false;
        }

        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("select mot_de_passe from client where login = " + adr);
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
