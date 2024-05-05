package tp3;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GUIAdmin {
	
	private String s;
    private JFrame frame;
    private JTextField textField;
    private JTable table;
    private BookTableModel tableModel;
    private JTextField textField_1;

    /**
     * Create the application.
     * @param string 
     * @throws SQLException 
     * @wbp.parser.entryPoint
     */
    public GUIAdmin(String string) throws SQLException {
    	s=string;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * @throws SQLException 
     */
    private void initialize() throws SQLException {
        ArrayList<String[]> bookData = Utilisateur.affiche_liste();
        if (s != null) {
        	System.out.println(s);
        	bookData = Utilisateur.chercher_livre(s);
        }
        // Example book data
        frame = new JFrame();
        frame.setBounds(100, 100, 801, 506);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 59, 765, 301);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Search Book by author:");
        lblNewLabel.setBounds(36, 23, 163, 14);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(209, 20, 113, 20);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Book list:");
        lblNewLabel_1.setBounds(33, 61, 88, 14);
        panel.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("search");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			GUIAdmin n=new GUIAdmin(textField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton.setBounds(332, 19, 99, 23);
        panel.add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 81, 745, 209);
        panel.add(scrollPane);

        tableModel = new BookTableModel(bookData);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel_2 = new JLabel("Admin");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
        lblNewLabel_2.setBounds(353, 26, 114, 22);
        frame.getContentPane().add(lblNewLabel_2);

        JButton btnNewButton_1 = new JButton("add book");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Create and display the add page
                EventQueue.invokeLater(() -> {
                    GUIAdd addPage = new GUIAdd();
                    addPage.getFrame().setVisible(true);
                });
            }
        });
        btnNewButton_1.setBounds(29, 371, 89, 23);
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("delete book");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Admin.supprimer_livre(textField_1.getText());
					GUIAdmin guiAdmin = new GUIAdmin(null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnNewButton_2.setBounds(216, 401, 114, 23);
        frame.getContentPane().add(btnNewButton_2);
        
        JLabel lblNewLabel_3 = new JLabel("ISBN :");
        lblNewLabel_3.setBounds(21, 405, 46, 14);
        frame.getContentPane().add(lblNewLabel_3);
        
        textField_1 = new JTextField();
        textField_1.setBounds(97, 402, 107, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        frame.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
            new GUIAdmin(null);
    
    }
    public void refreshTable() throws SQLException {
        // Update the table model with new data
        tableModel = new BookTableModel(Utilisateur.affiche_liste());
        table.setModel(tableModel);
    }
    public void filter(String t) throws SQLException {
        // Update the table model with new data
        tableModel = new BookTableModel(Utilisateur.chercher_livre(t));
        table.setModel(tableModel);
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
            return bookData.get(rowIndex)[columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
}
