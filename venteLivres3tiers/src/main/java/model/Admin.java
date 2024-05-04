package model;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin extends Utilisateur {
	
	public static void ajout_livre() throws IOException{
		 
		 File inputFile = new File("C:\\Users\\maazo\\Documents\\tp 2eme p\\tpArch\\livres.txt");
		 FileReader fileReader = new FileReader(inputFile);
		 BufferedReader in = new BufferedReader(fileReader) ;
		 FileWriter out = new FileWriter(inputFile,true);
		 Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		 System.out.println("Auteur :");
		 String name = myObj.nextLine();  // Read user input
		 System.out.println("Titre :");
		 String titre = myObj.nextLine();
		 System.out.println("ISBN:");
		 String isbn = myObj.nextLine();
		 System.out.println("Année parution :");
		 String annee = myObj.nextLine();
		 System.out.println("Prix :");
		 String prix = myObj.nextLine();
		 boolean b=true;
		 
		 String data = in.readLine();

		 while (data!=null) {
		 	String[] tab = data.split(",", 5);{
		 		if(tab[0].equals(isbn) ){b=false;}}
		     data = in.readLine();
		   }
		 
		 if (b) {
			 out.write("\n"+isbn+","+name+","+titre+","+annee+","+prix); 
		 }
		 else {
			 System.out.println("not added");
		 }
		 out.close();
		 in.close()	; 
	}
	
	public static void supprimer_livre(String isbn) throws IOException {
        File inputFile = new File("C:\\Users\\maazo\\Documents\\tp 2eme p\\tpArch\\livres.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader in = new BufferedReader(fileReader);

        ArrayList<String[]> file1 = new ArrayList<>();

        String data = in.readLine();

        while (data != null) {
            String[] tab = data.split(",", 5);
            file1.add(tab);
            data = in.readLine();
        }

        in.close(); // Close the initial reader

        FileWriter fileWriter = new FileWriter(inputFile);
        BufferedWriter out = new BufferedWriter(fileWriter);

        for (String[] entry : file1) {
            if (entry[0].equals(isbn)) {
                System.out.println("Deleted"); // Assuming index 1 contains book title
            } else {
                out.write(String.join(",", entry));
                out.newLine();
            }
        }

        out.close();
    }
	
}
