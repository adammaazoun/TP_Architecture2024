package controller;

import java.sql.*;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con= Connexion.connect();
		if (con != null) {
            System.out.println("Connection successful!");
            try {
                con.close(); // Close the connection after testing
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        } else {
            System.out.println("Connection failed.");
        }
	}

}
