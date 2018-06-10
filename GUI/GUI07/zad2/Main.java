/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Main extends JFrame {
	public Main() {
		setMinimumSize(new Dimension(400, 500));
		
		MyListModel mlm = new MyListModel();
		JList list = new JList<String>(mlm);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if ((e.getModifiers() & InputEvent.ALT_MASK) == InputEvent.ALT_MASK) {
					mlm.remove(list.getSelectedIndex());
				}
			}
		});
		JTextField tf = new JTextField();
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mlm.addElement(tf.getText());
				tf.setText("");
			}
		});
		
		add(new JScrollPane(list), BorderLayout.CENTER);
		add(tf, BorderLayout.SOUTH);
		
		setVisible(true);
	}

  public static void main(String[] args) {
	  new Main();
  }
}
