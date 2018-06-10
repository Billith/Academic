package zad1;

import javax.swing.AbstractListModel;

public class MyListModel extends AbstractListModel {

	@Override
	public Object getElementAt(int arg0) {
		int c = arg0-70;
		double f = (c* 1.8 ) + 32;
		return c+" stopni C = "+f+" stopni F";
	}

	@Override
	public int getSize() {
		return 131;
	}

}
