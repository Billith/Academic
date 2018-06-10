package ppj15_wyklad.geom;

import java.awt.Graphics;

public 
	class Prostokat 
	extends Kwadrat {
	
	int bok2;
	
	public Prostokat(int x, int y, int bok, int bok2){
		super(x, y, bok);
		this.bok2 = bok2;
	}

	public void rysuj(Graphics g) {
		g.setColor(c);
		g.fillRect(x-bok/2, y-bok2/2, bok, bok2);
	}
}
