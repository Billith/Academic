/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame {
	
	public Main() {
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
	}

  public static void main(String[] args) {
	  
	  Main outer = new Main();
	  
	  outer.add(outer.new MainPane());
	  outer.setVisible(true);
  }
  
  public class MainPane extends JPanel {
	  public MainPane() {
		  
	  }
	  
	  public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  
		  int width = this.getWidth()-1;
		  int height = this.getHeight()-1;
		  
		  g.setColor(Color.blue);
		  g.drawLine(1, 1, width, height);
		  g.drawLine(width, 1, 1, height);
	  }
  }
}
