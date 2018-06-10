package ppj15_wyklad.geom;

import java.awt.Color;
import java.io.Serializable;

public abstract
	class FiguraGeometryczna
	implements Serializable{

	int x;
	int y;
	Color c;
	
	public FiguraGeometryczna(int x, int y){
		this.x = x;
		this.y = y;
		
		c = new Color(
			(int)(Math.random()*255),
			(int)(Math.random()*255),
			(int)(Math.random()*255)
		);
	}
	
	public abstract void rysuj(java.awt.Graphics g);
}
