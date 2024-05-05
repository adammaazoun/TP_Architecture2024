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
	public static ArrayList<String[]> mylist(String name) throws SQLException {
	    Statement st = c.createStatement();
	    ResultSet rs = st.executeQuery("SELECT liste_livre FROM client WHERE login = '" + name + "'");
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
	public static void addToList(String isbn, String name) throws SQLException {
	    
	            String updateQuery = "UPDATE client SET liste_livre = CONCAT(ISBN, ?, ';') WHERE login = ?";

	            try (PreparedStatement psUpdate = c.prepareStatement(updateQuery)) {
	               
	                psUpdate.setString(1, isbn + ";"); 
	                psUpdate.setString(2, name);

	                
	                int rowsAffected = psUpdate.executeUpdate();
	                if (rowsAffected > 0) {
	                    System.out.println("Book added to the list successfully.");
	                } else {
	                    System.out.println("Failed to add book to the list.");
	                }
	            }
	        }
	public static void addToClientTable(String username, String password) throws SQLException {
   
        Connection connection = Connexion.connect();
        

        String sql = "INSERT INTO client (login, mot_de_passe) VALUES (?, ?)";

        try {
           
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

          
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
           
            e.printStackTrace();
        } finally {
           
            if (connection != null) {
                connection.close();
            }
        }
    }
	    
	}


	

