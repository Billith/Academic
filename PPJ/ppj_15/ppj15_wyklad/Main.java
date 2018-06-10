package ppj15_wyklad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import ppj15_wyklad.geom.*;
import ppj15_wyklad.struct.MyLista;

public 
	class Main 
	extends Frame {

	public static void main(String[] args) {
		new Main();
	}
	
	//FiguraGeometryczna figura;
	//FiguraGeometryczna[] figury;
	//int count;
	
	MyLista lista;
	
	public Main(){
		//figury = new FiguraGeometryczna[3];
		//count = 0;
		
		lista = new MyLista();
		
		try{
			FileInputStream fis = new FileInputStream(
				"c:\\1\\ppjzIIfiguryLista.out"
			);
			ObjectInputStream ois = new ObjectInputStream(fis);
			//lista.load(ois);
			lista = (MyLista)ois.readObject();
			fis.close();
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		addMouseListener(
			new MouseAdapter(){
				public void mouseClicked(MouseEvent evt){
					System.out.println(evt.getX()+" "+evt.getY());
					
					//figury[count++] = new Kolo( evt.getX(), evt.getY(), 20);
					
					switch((int)(Math.random()*3)){
					case 0:
						lista.addElement(
							new Kolo( 
								evt.getX(), 
								evt.getY(), 
								20+(int)(Math.random()*40)
							)
						);
						break;
					case 1:
						lista.addElement(new Kwadrat( evt.getX(), evt.getY(), 20+(int)(Math.random()*40)));
						break;
					case 2:
						lista.addElement(
							new Prostokat( 
								evt.getX(), 
								evt.getY(), 
								20+(int)(Math.random()*40), 
								20+(int)(Math.random()*40)
							)
						);
						break;
					}
					
					
					repaint();
				}
			}
		);
		
		addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent evt){
					
					try{
						FileOutputStream fos = new FileOutputStream(
							"c:\\1\\ppjzIIfiguryLista.out"
						);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						//lista.save(oos);
						oos.writeObject(lista);
						oos.close();
						fos.close();
					}catch(FileNotFoundException ex){
						ex.printStackTrace();
					}catch(IOException ex){
						ex.printStackTrace();
					}
					
					
					System.exit(0);
				}
			}
		);
		
		setSize( 640, 480);
		setVisible(true);
	}

	public void paint(Graphics g){
		//if(figura != null)
		//	figura.rysuj(g);
		/*
		if(figury != null)
			for(int i=0; i<count; i++)
				figury[i].rysuj(g);
		*/
		if(lista != null)
			lista.show(g);
	}

}
