package tp3;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookTableExample extends JFrame {
    private BookTableModel tableModel;
    private JTable table;

    public BookTableExample(List<String[]> bookData) {
        setTitle("Book Table Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new BookTableModel(bookData);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        List<String[]> bookData = new ArrayList<>();
        // Example book data
        bookData.add(new String[]{"Title 1", "Author 1", "Genre 1", "2000"});
        bookData.add(new String[]{"Title 2", "Author 2", "Genre 2", "2001"});
        bookData.add(new String[]{"Title 3", "Author 3", "Genre 3", "2002"});

        SwingUtilities.invokeLater(() -> new BookTableExample(bookData));
    }

    // Custom TableModel for books
    private class BookTableModel extends AbstractTableModel {
        private List<String[]> bookData;
        private String[] columns = {"Title", "Author", "Genre", "Year"};

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
