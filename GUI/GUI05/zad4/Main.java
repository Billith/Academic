/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame{
	public Main() {
		setSize(700, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
	}

  public static void main(String[] args) {
	  Main gui = new Main();
	  
	  JButton p1 = new JButton("Przycisk 1");
	  JButton p2 = new JButton("P 2");
	  JButton p3 = new JButton("Większy przycisk numer 3");
	  JButton p4 = new JButton("Przycisk 4");
	  JButton p5 = new JButton("P5");
	  
	  String[] options = { "A", "B", "C", "D", "E", "F", "G" };
	  String answ = (String) JOptionPane.showInputDialog(
			  null,
			  null,
			  "Wybierz Layout",
			  JOptionPane.QUESTION_MESSAGE,
			  null,
			  options,
			  options[0]);
	  
	  switch (answ) {
	  	case "A": setBorderLayout(gui, p1, p2, p3, p4, p5); break;
	  	case "B": setFlowLayout(gui, p1, p2, p3, p4, p5); break;
	  	case "C": setFlowLayoutToLeft(gui, p1, p2, p3, p4, p5); break;
	  	case "D": setFlowLayoutToRight(gui, p1, p2, p3, p4, p5); break;
	  	case "E": setGridLayout(gui, p1, p2, p3, p4, p5); break;
	  	case "F": setGridLayoutCol(gui, p1, p2, p3, p4, p5); break;
	  	case "G": setGridLayoutTab(gui, p1, p2, p3, p4, p5); break;
	  }
	  
	  	  
	  gui.setVisible(true);
  }
  
  static public void setBorderLayout(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new BorderLayout());
	  gui.add(b1, BorderLayout.NORTH);
	  gui.add(b2, BorderLayout.EAST);
	  gui.add(b3, BorderLayout.WEST);
	  gui.add(b4, BorderLayout.CENTER);
	  gui.add(b5, BorderLayout.SOUTH);
  }
  
  public static void setFlowLayout(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new FlowLayout());
	  addButtons(gui, b1, b2, b3, b4, b5);
  }
  public static void setFlowLayoutToLeft(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new FlowLayout(FlowLayout.LEFT));
	  addButtons(gui, b1, b2, b3, b4, b5);
  }
  public static void setFlowLayoutToRight(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new FlowLayout(FlowLayout.RIGHT));
	  addButtons(gui, b1, b2, b3, b4, b5);
  }
  public static void setGridLayout(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new GridLayout());
	  addButtons(gui, b1, b2, b3, b4, b5);
  }
  public static void setGridLayoutCol(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new GridLayout(0, 1));
	  addButtons(gui, b1, b2, b3, b4, b5);
  }
  public static void setGridLayoutTab(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.setLayout(new GridLayout(3, 2));
	  addButtons(gui, b1, b2, b3, b4, b5);
  }
  public static void addButtons(Main gui, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
	  gui.add(b1);
	  gui.add(b2);
	  gui.add(b3);
	  gui.add(b4);
	  gui.add(b5);
  }
}
