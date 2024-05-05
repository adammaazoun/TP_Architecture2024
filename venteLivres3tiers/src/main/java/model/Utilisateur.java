package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.Connexion;

public abstract class Utilisateur {
    public String login = "";
    public String mdp = "";

    // Note: Connection should be initialized when needed, not at the class level
    // You may want to consider a better approach for managing connections, such as a connection pool
    // This is just a simplified example
    // static Connection c = Connexion.connect();

    // Method to display the list of books
    public static void affiche_liste() throws ClassNotFoundException {
        try (Connection c = Connexion.connect(); // Establish connection within a try-with-resources block
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT titre FROM livre")) {

            while (rs.next()) {
                System.out.println(rs.getString("titre"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    // Method to search for books by author
    public static void chercher_livre(String auteur) throws ClassNotFoundException {
        try (Connection c = Connexion.connect(); // Establish connection within a try-with-resources block
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT titre FROM livre WHERE auteur='" + auteur + "'")) {

            while (rs.next()) {
                System.out.println(rs.getString("titre"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
