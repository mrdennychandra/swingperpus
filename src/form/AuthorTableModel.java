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
import entity.Author;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * A table model implementation for a list of Author objects.
 *
 * @author www.codejava.net
 *
 */
public class AuthorTableModel extends AbstractTableModel {

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_EMAIL = 2;
    private static final int COLUMN_GENDER = 3;
    private static final int COLUMN_BIRTHDATE = 4;

    private String[] columnNames = {"Id", "Name", "Email", "Gender", "Birth Date"};
    private List<Author> listAuthors;

    public AuthorTableModel(List<Author> listAuthors) {
        this.listAuthors = listAuthors;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return listAuthors.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listAuthors.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Author author = listAuthors.get(rowIndex);
        Object returnValue = null;
        switch (columnIndex) {
            case COLUMN_ID:
                returnValue = author.getId();
                break;
            case COLUMN_NAME:
                returnValue = author.getName();
                break;
            case COLUMN_EMAIL:
                returnValue = author.getEmail();
                break;
            case COLUMN_GENDER:
                returnValue = author.getGender();
                break;
            case COLUMN_BIRTHDATE:
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                returnValue = fmt.format(author.getBirthDate());
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Author author = listAuthors.get(rowIndex);
        if (columnIndex == COLUMN_ID) {
            author.setId((long) value);
        }
    }

}
