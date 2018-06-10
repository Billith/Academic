package zad3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	List<Book> booksList = new ArrayList<>();
	String[] cols = { "Tytuł", "Autor", "Cena", "Okładka" };
	
	public MyTableModel(List<Book> list) {
		booksList = list;
	}
	
	@Override
	public String getColumnName(int column) {
		return cols[column];
	}
	
	@Override
	public int getColumnCount() {
		return cols.length;
	}

	@Override
	public int getRowCount() {
		return booksList.size();
	}
	
	public List<Book> getContentList() {
		return booksList;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Book b = booksList.get(row);
		switch(col) {
		case 0 : return b.getAuthor();
		case 1 : return b.getTitle();
		case 2 : return b.getPrice();
		case 3 : return b.getCover();
		default: return null;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return Double.class;
		case 3: return ImageIcon.class;
		default: return String.class;
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 2;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 2) booksList.get(rowIndex).setPrice((double)aValue);
	}
	
	public void addRow(Book rowData) {
		booksList.add(rowData);
		fireTableRowsInserted(booksList.size(), booksList.size());
	}
	
	public void removeRow(int rowIndex) {
		booksList.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public void addBook(String author, String title, String price, ImageIcon cover) {
		double dprice = Double.parseDouble(price);
		addRow(new Book(author, title, dprice, Main.getScaledImage(cover)));
	}
}
