package zad2;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;

public class DrawIcon implements Icon {
		String color;
		int size = 10;
		
		public DrawIcon(String color) {
			this.color = color;
		}
		
		@Override
		public int getIconHeight() {
			return size;
		}
		@Override
		public int getIconWidth() {
			return size;
		}
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			switch(color) {
				case "Blue": g.setColor(java.awt.Color.BLUE);;break;
				case "Yellow" : g.setColor(java.awt.Color.YELLOW);break;
				case "Orange" : g.setColor(java.awt.Color.ORANGE);break;
				case "Red" : g.setColor(java.awt.Color.RED);break;
				case "White" : g.setColor(java.awt.Color.WHITE);break;
				case "Black" : g.setColor(java.awt.Color.BLACK);break;
				case "Green" : g.setColor(java.awt.Color.GREEN);break;
			}
			g.fillOval( x , y , size , size );
		}
}
