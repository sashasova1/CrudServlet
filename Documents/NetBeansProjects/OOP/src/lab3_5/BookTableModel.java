package lab3_5;

import java.util.List;
import javax.swing.table.AbstractTableModel;

class BookTableModel extends AbstractTableModel {

    public static final int OBJECT_COL = -1;
    private static final int ID_COL = 0;
    private static final int NAME_COL = 1;
    private static final int AUTHOR_COL = 2;
    private static final int GENRE_COL = 3;

    private String[] columnNames = {"ID", "Name", "Author", "Genre"};
    private List<Book> books;

    public BookTableModel(List<Book> theBooks) {
        books = theBooks;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Book tempBook = books.get(row);

        switch (col) {
            case ID_COL:
                return tempBook.getID();
            case NAME_COL:
                return tempBook.getName();
            case AUTHOR_COL:
                return tempBook.getAuthor();
            case GENRE_COL:
                return tempBook.getGenre();
            case OBJECT_COL:
                return tempBook;
            default:
                return tempBook.getName();
        }
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
