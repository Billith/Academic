package zad2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class MyListModel extends DefaultListModel {
	List list = new ArrayList<String>();

	@Override
	public void addElement(Object element) {
		add(getSize(), element);
	}
	
	@Override
	public void add(int index, Object element) {
		list.add(element);
		fireContentsChanged(this, index, index);
	}
	
	@Override
	public Object remove(int index) {
		Object element = list.get(index);
		list.remove(index);
		fireContentsChanged(this, index, index);
		return element;
	}

	@Override
	public Object getElementAt(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getSize() {
		return list.size();
	}
	
	
}
