package ppj15_wyklad.geom;

import java.awt.Graphics;

public 
	class Kwadrat
	extends FiguraGeometryczna {
	
	int bok;
	
	public Kwadrat(int x, int y, int bok){
		super( x, y);
		this.bok = bok;
		System.out.println("Kwadrat");
	}

	public void rysuj(Graphics g) {
		g.setColor(c);
		g.fillRect(x-bok/2, y-bok/2, bok, bok);
	}

}
