package tp3;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GUIClient {

    private JFrame frame;
    private JTextField textField;
    private JTable table1;
    private JTable table2;
    private BookTableModel tableModel1;
    private BookTableModel tableModel2;
    private JTextField textField_1;

    /**
     * Create the application.
     * @param string 
     * @wbp.parser.entryPoint
     */
    public GUIClient(String s,String name) {
        try {
			initialize(s, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Initialize the contents of the frame.
     * @throws SQLException 
     */
    private void initialize(String s,String name) throws SQLException {
        
        ArrayList<String[]> bookData = Utilisateur.affiche_liste();
        if (s != null) {
        	System.out.println(s);
        	bookData = Utilisateur.chercher_livre(s);
        }
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // First Panel
        JPanel panel1 = new JPanel();
        panel1.setBounds(10, 10, 400, 430);
        frame.getContentPane().add(panel1);
        panel1.setLayout(null);

        JLabel lblNewLabel1 = new JLabel("Search Book by author:");
        lblNewLabel1.setBounds(10, 10, 150, 14);
        panel1.add(lblNewLabel1);

        textField = new JTextField();
        textField.setBounds(170, 10, 150, 20);
        panel1.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel1_1 = new JLabel("Book list:");
        lblNewLabel1_1.setBounds(10, 40, 100, 14);
        panel1.add(lblNewLabel1_1);

        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(10, 60, 380, 300);
        panel1.add(scrollPane1);

        tableModel1 = new BookTableModel(bookData);
        table1 = new JTable(tableModel1);
        scrollPane1.setViewportView(table1);

        JButton btnNewButton1 = new JButton("Order book");
        btnNewButton1.setBounds(237, 371, 150, 30);
        panel1.add(btnNewButton1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(85, 376, 142, 20);
        panel1.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("ISBN :");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(13, 380, 63, 14);
        panel1.add(lblNewLabel);

        // Second Panel
        JPanel panel2 = new JPanel();
        panel2.setBounds(420, 10, 450, 430);
        frame.getContentPane().add(panel2);
        panel2.setLayout(null);

        JLabel lblNewLabel2 = new JLabel("Books ordered:");
        lblNewLabel2.setBounds(10, 10, 100, 14);
        panel2.add(lblNewLabel2);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 30, 430, 390);
        panel2.add(scrollPane2);

        List<String[]> bookDetailData = new ArrayList<>();
        // Example book detail data
        bookDetailData.add(new String[]{"ISBN1", "Title 1", "Author 1", "2000", "10", "Description 1"});
        bookDetailData.add(new String[]{"ISBN2", "Title 2", "Author 2", "2001", "15", "Description 2"});
        bookDetailData.add(new String[]{"ISBN3", "Title 3", "Author 3", "2002", "20", "Description 3"});

        tableModel2 = new BookTableModel(bookDetailData);
        table2 = new JTable(tableModel2);
        scrollPane2.setViewportView(table2);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
            new GUIClient(null,"amin.karray");
        
    }

    private class BookTableModel extends AbstractTableModel {
        private List<String[]> bookData;
        private String[] columns = {"ISBN", "Title", "Author", "Year", "Price"};

        public BookTableModel(List<String[]> bookData) {
            this.bookData = bookData;
        }

        @Override
        public int getRowCount() {
            return bookData.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            String[] rowData = bookData.get(rowIndex);
            if (columnIndex >= 0 && columnIndex < rowData.length) {
                return rowData[columnIndex];
            } else {
                return null; // Or handle the case where the column index is out of bounds
            }
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
}
