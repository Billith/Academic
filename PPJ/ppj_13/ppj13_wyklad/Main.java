package ppj13_wyklad;

import java.io.*;

public 
	class Main {

	public static void main(String[] args) {
		try{
			A a = new A();
			a.wrt = 1701;
			a.text = "Ala ma kota";

			System.out.println(a);
			//writeA(a);
			writeO(a);
			//A b = readA();
			A b = readO();
			System.out.println(b);
			
		}catch(Exception ex){
			
		}
	}
	
	public static A readO(){
		try{			
			FileInputStream fos = new FileInputStream("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\objII.obj");
			ObjectInputStream ois = new ObjectInputStream(fos);
			A a = (A)ois.readObject();
			return a;
		}catch(Exception ex){
			System.out.println(ex);
		}		
		return null;
	}	
	
	public static void writeO(A a){
		try{
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\objII.obj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(a);
			fos.close();
			
		}catch(Exception ex){
			System.out.println(ex);
		}		
	}	
	
	public static A readA(){
		try{			
			FileInputStream fos = new FileInputStream("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\objII.obj");
			A a = new A();
			a.wrt = fos.read();
			a.wrt = (a.wrt << 8) + fos.read();
			int tmpL = fos.read();
			a.text = "";
			for(int i=0; i<tmpL; i++){
				a.text += (char)fos.read();
			}
			fos.close();
			return a;
		}catch(Exception ex){
			System.out.println(ex);
		}		
		return null;
	}	
	
	public static void writeA(A a){
		try{
			// 1. wrt - int
			// 2. text length - int
			// 3. text - string
			
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\objII.obj");
			fos.write(a.wrt>>8);
			fos.write(a.wrt);
			fos.write(a.text.length());
			for(int i=0; i<a.text.length(); i++){
				fos.write(a.text.charAt(i));
			}
			fos.close();
			
		}catch(Exception ex){
			System.out.println(ex);
		}		
	}
	
	public static void fun1(){
		System.out.println("test");
		
		try {
			int wrt = System.in.read();
			System.out.println("wrt: "+(char)wrt);
			
			while(true){
				wrt = System.in.read();
				System.out.println("wrt: "+(char)wrt + " - " + wrt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fun2(){
		try {
			
			FileInputStream fis = new FileInputStream(
				"C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\1.txt"
			);
			/*
			int wrt = fis.read();

			while(wrt != -1 ){
				System.out.println("wrt: "+(char)wrt + " - " + wrt);
				wrt = fis.read();
			}
			*/
			
			StringBuffer sb = new StringBuffer();
			
			int wrt;
			
			while((wrt = fis.read()) != -1){
				System.out.println("wrt: "+(char)wrt + " - " + wrt);
				sb.append((char)wrt);
			}
			System.out.println(sb);
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(
				"C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\1_copyII.txt"
			);
			
			for(int i=0; i<sb.length(); i++)
				fos.write(sb.charAt(i));
			
			fos.write('a');
			
			fos.close();
		} catch( FileNotFoundException e){
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void fun3(){
		try{
			FileReader fr = new FileReader("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\ppj\\ppj_13\\src\\ppj13_wyklad\\1.txt");
			
			BufferedReader br = new BufferedReader(fr);
			
			String wrt;
			
			while((wrt = br.readLine()) != null){
				System.out.println("wrt: " + wrt);
			}
			
			fr.close();
		}catch(Exception ex){
			
		}		
	}
}

class A
	implements Serializable {
	int wrt;
	String text;
	
	public String toString(){
		return "A: "+wrt +", "+text;
	}
}