/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Main extends JFrame {
	
	public Main() {
		setMinimumSize(new Dimension(400, 600));
		
		JList list = new JList(new MyListModel());
		add(new JScrollPane(list));
		
		setVisible(true);
	}

  public static void main(String[] args) {
	  new Main();
  }
}
