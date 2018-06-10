/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
	String path;

	public Finder (String path) {
		this.path = path;
	}
	
	public int getIfCount() {
		int ifCounter = 0;
		String str;
		String regex = "if\\p{Blank}*\\({1}+[^)]*\\){1}";
		String regexCom = "^//";
		String regexLit = "\"\\p{Blank}*if\\p{Blank}*\\({1}+[^)]*\\){1}\\p{Blank}*[^\"]*\\p{Blank}*\"";
		
		try {
			File f = new File(this.path);
			Scanner file = new Scanner(f);
			while(file.hasNextLine()) {
			 	str = file.nextLine();
			 	
				Pattern pif1 = Pattern.compile(regexCom);
				Pattern pif2 = Pattern.compile(regexLit);
				Matcher mif1 = pif1.matcher(str);
				Matcher mif2 = pif2.matcher(str);
				while (mif2.find()) {
					ifCounter--;
				}
			 	if(mif1.find() == false) {
			 		Pattern pif = Pattern.compile(regex);
					Matcher mif = pif.matcher(str);
					while (mif.find()) {
						ifCounter++;
					}
			 	}
			}	
			file.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return ifCounter;
	}
	
	public int getStringCount(String phrase) {
		int stringCounter = 0;
		int wrt;
		String regex = phrase;
		StringBuffer str = new StringBuffer(); 
		
		try {
			FileReader fr = new FileReader(this.path);
		
			while((wrt = fr.read()) != -1) {
				str.append((char)wrt);
			}
			
			Pattern pif = Pattern.compile(regex);
			Matcher mif = pif.matcher(str);
			while (mif.find()) {
				stringCounter++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return stringCounter;
	}
}    
