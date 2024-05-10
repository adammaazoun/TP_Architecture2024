package model;

import java.sql.*;

import java.util.ArrayList;

import controller.Connexion;

public class Client extends Utilisateur {
    public ArrayList<String> liste_livre = new ArrayList<String>();

    // Removed static keyword from Connection
    public static Connection c = Connexion.connect();
    

    public static boolean verifClient(String login, String mdp) throws SQLException {
    	String query = "SELECT mot_de_passe FROM client WHERE login = ?";
		PreparedStatement preparedStatement = c.prepareStatement(query);
		preparedStatement.setString(1, login);
		ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) return true;
				else return false;
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
    
    public static void addToList(String isbn, String name) throws SQLException {
        String selectQuery = "SELECT liste_livre FROM client WHERE login=?";
        String updateQuery = "UPDATE client SET liste_livre = ? WHERE login = ?";
        
        try (PreparedStatement psSelect = c.prepareStatement(selectQuery);
             PreparedStatement psUpdate = c.prepareStatement(updateQuery)) {
            
            // Set parameters for the select query
            psSelect.setString(1, name);
            
            // Execute the select query
            ResultSet rs = psSelect.executeQuery();
            
            if (rs.next()) {
                String currentList = rs.getString("liste_livre");
                // Append the new ISBN to the existing list
                String updatedList = currentList + isbn + ";";
                
                // Set parameters for the update query
                psUpdate.setString(1, updatedList);
                psUpdate.setString(2, name);
                
                // Execute the update query
                int rowsAffected = psUpdate.executeUpdate();
                
                if (rowsAffected > 0) {
                    System.out.println("Book added to the list successfully.");
                } else {
                    System.out.println("Failed to add book to the list.");
                }
            } else {
            	psUpdate.setString(1, isbn+";");
                psUpdate.setString(2, name);
                
                // Execute the update query
                int rowsAffected = psUpdate.executeUpdate();
                
                
                if (rowsAffected > 0) {
                    System.out.println("Book added to the list successfully.");
                } else {
                    System.out.println("Failed to add book to the list.");
                }
            }
        }
    }

    
    public static ArrayList<String[]> affichePanier(String login) throws SQLException {
    	Statement st = c.createStatement();
	    ResultSet rs = st.executeQuery("SELECT liste_livre FROM client WHERE login = '" + login + "'");
	    ArrayList<String[]> bookList = new ArrayList<String[]>();

	    if (rs.next()) {
	        String l1 = rs.getString("liste_livre");
	        if (l1 != null && !l1.isEmpty()) {
	            String[] books = l1.split(";"); 
	            
	           
	            for (String book : books) {
	           
	                String isbn = book; 
	                ResultSet rs1 = st.executeQuery("SELECT * FROM livre WHERE ISBN = '" + isbn + "'");
	                if (rs1.next()) {
	                  
	                    String[] bookInfo = {
	                        rs1.getString("ISBN"),
	                        rs1.getString("titre"),
	                        rs1.getString("auteur"),
	                        String.valueOf(rs1.getInt("annee")),
	                        String.valueOf(rs1.getInt("prix"))
	                    };
	                    bookList.add(bookInfo);
	                    
	                }
	                rs1.close();
	            }
	        }
	    }

	    rs.close();
	    st.close();
	    return bookList;
    }
    
}
