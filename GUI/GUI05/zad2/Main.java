/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

  public static void main(String[] args) throws InterruptedException {
	  Main gui = new Main();
	  gui.setLayout(new BorderLayout());
	  gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  File directory = new File(args[0]);
	  
	  BlockingQueue<ImageIcon> que = new ArrayBlockingQueue<ImageIcon>(1024);
	  int timer = Integer.parseInt(args[1])*1000;
	  int font = Integer.parseInt(args[2]);
	  
	  for (File file : directory.listFiles())
	  {
	      if(file.getName().toLowerCase().endsWith(".png") || file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".gif"))
	      {
	          que.put(new ImageIcon(file.getPath()));
	      }
	  }
	  if(!que.isEmpty()) {
		  gui.setMinimumSize(new Dimension(que.peek().getIconWidth(), que.peek().getIconHeight()));
	  }
		  
		  JPanel image = new JPanel() {
			  public void paintComponent(Graphics g) {
				  super.paintComponent(g);
				  setLayout(new BorderLayout());
				  setBackground(Color.black);
				  setBorder(null);
					  
				  try {
					  if (!que.isEmpty()) {  
						  
						  
						  g.drawImage(que.take().getImage(), 0, 0, null);
						  gui.revalidate();
						  gui.repaint();
						  
						  gui.pack();
						  Thread.sleep(timer);
						  gui.setSize(new Dimension(que.peek().getIconWidth(), que.peek().getIconHeight()));
						  //setMaximumSize(new Dimension(que.peek().getIconWidth(), que.peek().getIconHeight()));
						  //setMinimumSize(new Dimension(que.peek().getIconWidth(), que.peek().getIconHeight()));
					  } else {
						  Thread.sleep(timer);
						  g.setFont(new Font("Arial", Font.PLAIN, font));
						  g.setColor(Color.white);
						  g.drawString("Koniec prezentacji", 10, (gui.getHeight()/2)-(font/2));
					  }
				  } catch (NullPointerException e) {
					  
			  	  } catch (Exception e) {
					  g.drawString("Brak obrazka", 10, (gui.getHeight()/2)-(font/2));
				  }
			  }
		  };
		  gui.add(image);
		  gui.setVisible(true); 
	  }
}
