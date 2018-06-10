/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


public static void main(String[] args) throws FileNotFoundException {
	  File txt = new File("../Towary.txt");
	  Scanner file = new Scanner(txt);
	  AtomicBoolean ready = new AtomicBoolean(true) ;
	  AtomicInteger currentTowarWeigth = new AtomicInteger(0);
	  AtomicInteger generalTowarWeigth = new AtomicInteger(0);
	  
	  Thread A = new Thread() {
		  public void run() {
			  int counter = 1;
		    		
			  while(file.hasNextLine()) {
		    			
				  if (ready.get() == true) {
			    		String line = file.nextLine();
			    		Scanner lineScan = new Scanner(line);
			    		
			    		String id = lineScan.findInLine("^\\d+?(?=_)");
			    			
			    		String name = lineScan.findInLine("\\w+?\\d+?(?= )");
			    		name = name.replace("_", "");
			    			
			    		String weigth1 = lineScan.findInLine(" \\d+");
			    		weigth1 = weigth1.replaceAll(" ", "");
			    		int weigth = Integer.parseInt(weigth1);
			    			
			    		lineScan.close();
			    		
			    		Towar current = new Towar(id, name, weigth);
			    		currentTowarWeigth.set(weigth);
			    		counter++;                                
			    			
			    		if (counter%200 == 0) {
			    			System.out.println("utworzono "+counter+" obiektów");
			    		}
			    		ready.set(false);
		    		}
		    
		    	}
		    	file.close();
		  }
	  };
	  A.setName("A");
	  
	  Thread B = new Thread() {
		  public void run() {
			 try {
				int counter = 1;
				
				while(true) {
					if (ready.get() == false) {
						generalTowarWeigth.set(generalTowarWeigth.get()+currentTowarWeigth.get());
						counter++;
						ready.set(true);
						if (counter%100 == 0) {
							System.out.println("policzono wage "+counter+" towarów");
						}
					}
					
					if (A.isAlive() == false) {
						System.out.println(generalTowarWeigth.get());
						throw new InterruptedException();
					}
				}
			} catch (Exception e) {
				return;
			}
		  }
	  };
	  B.setName("Thread B");

	  
	  A.start();
	  B.start();
	}
}


