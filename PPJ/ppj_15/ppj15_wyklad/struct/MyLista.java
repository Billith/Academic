package ppj15_wyklad.struct;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ppj15_wyklad.geom.FiguraGeometryczna;

public 
	class MyLista
	implements Serializable{

	Element poczatek = null;
	
	public void addElement(FiguraGeometryczna figura){
		if(poczatek == null){
			Element e = new Element();
			e.figura = figura;
			poczatek = e;
		}else{
			Element tmp = poczatek;
			while(tmp.nastepny != null){
				tmp = tmp.nastepny;
			}
			
			Element e = new Element();
			e.figura = figura;
			tmp.nastepny = e;			
		}
	}
	
	public void show(){
		Element temp = poczatek;
		while(temp != null){
			System.out.println(temp);
			System.out.println(temp.figura);
			
			temp = temp.nastepny;
		}		
	}
	
	public void show(java.awt.Graphics g){
		Element temp = poczatek;
		while(temp != null){
			System.out.println(temp);
			System.out.println(temp.figura);
			temp.figura.rysuj(g);
			
			temp = temp.nastepny;
		}		
	}
	
	public void save(ObjectOutputStream oos) throws IOException{
		Element temp = poczatek;
		while(temp != null){
			oos.writeObject(temp.figura);
			temp = temp.nastepny;
		}		
	}
	
	public void load(ObjectInputStream ois) throws IOException{
		Object tmp;
		
		try {
			while( (tmp = ois.readObject()) != null)
				addElement(
					(FiguraGeometryczna)tmp
				);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
}
