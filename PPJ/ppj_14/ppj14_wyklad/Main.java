package ppj14_wyklad;

import java.io.*;
import java.awt.*;

import java.util.regex.*;

public 
	class Main
	extends Frame {

	public static void main(String[] args) {
		loadPPM("c:\\1\\Snow.ppm");
		new Main();
		
		String str[] = {
				"123 456 789",
				"123-456-789",
				"123-456a789",
				"123.456.789"
		};
		
		String s = " Ala ma 123 kota";
		
		 Pattern p = Pattern.compile("(\\d{3}[ -\\.]){2}\\d{3}");
		 for(int i=0; i<str.length; i++){
			 Matcher m = p.matcher(str[i]);
			 boolean b = m.matches();
			 
			 System.out.println(str[i] + " - " + b);
		 }
		 
		 p = Pattern.compile("\\d*");
		 boolean r = p.matcher(s).find();
	}
	
	public static Color[][] loadPPM(String path){
		try{
			FileReader fr = new FileReader(path);
			StreamTokenizer st = new StreamTokenizer(fr);
			
			st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			int width = (int) st.nval;
			st.nextToken();
			int height = (int) st.nval;
			st.nextToken();


			Color[][] tab = new Color[height][width];
			int wrt,
				w = 0,
				h = 0;
			
			while( (wrt = st.nextToken()) != StreamTokenizer.TT_EOF){
				int r = (int) st.nval;
				st.nextToken();
				int g = (int) st.nval;
				st.nextToken();
				int b = (int) st.nval;
				Color c = new Color( r, g, b);
				tab[h][w % width] = c;
				
				w++;
				if( w != 0 && w % width == 0){
					h++;
				}
			}
			return tab;
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
	}
	
	Color[][] colors;
	
	public Main(){
		
		colors = loadPPM("c:\\1\\Snow.ppm");
		setSize( 640, 480);
		setVisible(true);
                //setIconImage(Toolkit.getDefaultToolkit().getImage("pix.png"));
                
	}
	
	public void paint(Graphics g){
		for( int i=0; i<colors.length; i++){
			for(int j=0; j< colors[i].length; j++){
				g.setColor(colors[i][j]);
				g.drawLine(50+j, 50+i, 50+j, 50+i);
			}
		}
	}
}
