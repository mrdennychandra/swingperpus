/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

/**
 *
 * @author lenovo
 */
import entity.Book;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A table model implementation for a list of Author objects.
 *
 * @author www.codejava.net
 *
 */
public class BookTableModel extends AbstractTableModel {

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_TITLE = 1;
    private static final int COLUMN_ISBN = 2;
    private static final int COLUMN_STOCK = 3;
    private static final int COLUMN_AUTHOR = 4;

    private String[] columnNames = {"Id", "Title", "ISBN", "Stock", "Author"};
    private List<Book> listBooks;

    public BookTableModel(List<Book> listBooks) {
        this.listBooks = listBooks;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return listBooks.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listBooks.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = listBooks.get(rowIndex);
        Object returnValue = null;
        switch (columnIndex) {
            case COLUMN_ID:
                returnValue = book.getId();
                break;
            case COLUMN_TITLE:
                returnValue = book.getTitle();
                break;
            case COLUMN_ISBN:
                returnValue = book.getIsbn();
                break;
            case COLUMN_STOCK:
                returnValue = book.getStok();
                break;
            case COLUMN_AUTHOR:
                returnValue = book.getAuthor();
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Book book = listBooks.get(rowIndex);
        if (columnIndex == COLUMN_ID) {
            book.setId((long) value);
        }
    }
}
