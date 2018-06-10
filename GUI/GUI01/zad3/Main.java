/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad3;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {  
    String fname = System.getProperty("user.home") + "/tab.txt"; 

    ArrayList<String> list = new ArrayList<>();
    int wrt;
	StringBuffer str = new StringBuffer(); 
	
	try {
		FileReader fr = new FileReader(fname);
	
		while((wrt = fr.read()) != -1) {
			str.append((char)wrt);
		}
		String regex = "\\-?\\d+";
		Pattern pDig = Pattern.compile(regex);
		Matcher mDig = pDig.matcher(str);
		String regexCheck = "[^\\d+\\p{Space}\\s\\-\\+]";
		Pattern pDig1 = Pattern.compile(regexCheck);
		Matcher mDig1 = pDig1.matcher(str);
		
		if(mDig1.find()) {
			throw new Exception();
		}  else {
			while (mDig.find()) {
				list.add(mDig.group());
			}
			int[] tab = new int[list.size()];
			for(int i=0; i<tab.length;i++) {
				tab[i] = Integer.parseInt(list.get(i));
			}
			int maxVal = 0;

			for(int i=0;i<tab.length;i++) {
				if(i != tab.length-1) { 
					if(tab[i] < tab[i+1]) {
						maxVal = tab[i+1];
					}
				}
			}
			for(int i=0;i<tab.length;i++) {
				System.out.print(tab[i]+" ");
			}
			System.out.println();
			System.out.println(maxVal);
			for(int i=0;i<tab.length;i++) {
				if(tab[i]==maxVal) {
				System.out.print(i+" ");
				}
			}
		}
	} catch (Exception e) {
		System.out.println("***");
	}
  }
}
