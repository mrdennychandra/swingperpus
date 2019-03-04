/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import entity.NotaDetail;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lenovo
 */
public class TrxTableModel extends AbstractTableModel {

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_CUSTOMER = 1;
    private static final int COLUMN_BOOK = 2;
    private static final int COLUMN_DATE = 3;

    private String[] columnNames = {"Id", "Customer", "Book", "Date"};
    private List<NotaDetail> listTrx;

    public TrxTableModel(List<NotaDetail> listTrx) {
        this.listTrx = listTrx;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return listTrx.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listTrx.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NotaDetail detail = listTrx.get(rowIndex);
        Object returnValue = null;
        switch (columnIndex) {
            case COLUMN_ID:
                returnValue = detail.getId();
                break;
            case COLUMN_CUSTOMER:
                returnValue = detail.getNota().getCustomerName();
                break;
            case COLUMN_BOOK:
                returnValue = detail.getBook().getTitle();
                break;
            case COLUMN_DATE:
                returnValue = detail.getNota().getNotaDate();
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        NotaDetail detail = listTrx.get(rowIndex);
        if (columnIndex == COLUMN_ID) {
            detail.setId((long) value);
        }
    }
}
