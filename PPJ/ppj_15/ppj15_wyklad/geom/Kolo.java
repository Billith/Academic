package ppj15_wyklad.geom;

import java.awt.Color;
import java.awt.Graphics;

public 
	class Kolo 
	extends FiguraGeometryczna {
	
	int r;
	
	public Kolo(int x, int y, int r){
		super( x, y);
		this.r = r;
	}
	
	public void rysuj(Graphics g) {
		g.setColor(c);
		g.fillOval(x-r, y-r, r*2, r*2);
		g.setColor(Color.BLACK);
		g.drawOval(x-r, y-r, r*2, r*2);
	}

}
