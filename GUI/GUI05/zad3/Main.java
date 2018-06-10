/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame {
	
	public Main() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
	}
	
  public static void main(String[] args) {
	  Main gui = new Main();
	  
	  JTextArea jta = new JTextArea(20,50);
	  jta.setBackground(Color.BLUE);
	  jta.setForeground(Color.yellow);
	  jta.setFont(new Font("Dialog", Font.ITALIC, 14));
	  
	  JScrollPane p = new JScrollPane(jta);
	  
	  gui.add(p, BorderLayout.CENTER);
	  gui.pack();
	  gui.setVisible(true);
  }
}
