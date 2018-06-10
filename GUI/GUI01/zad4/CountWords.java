/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad4;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountWords {
	String path;
	LinkedHashMap<String, Integer> words = new LinkedHashMap<String, Integer>();  
	ArrayList<String> wordList = new ArrayList<>();
	ArrayList<String> results = new ArrayList<>();
	
	public CountWords(String path) {
		this.path = path;
	}
	
	public ArrayList<String> getResult() {
		try {
			int wrt;
			StringBuffer str = new StringBuffer(); 
			FileReader fr = new FileReader(this.path);
		
			while((wrt = fr.read()) != -1) {
				str.append((char)wrt);
			}
			
			String regex = "\\w+";
			Pattern pWord = Pattern.compile(regex);
			Matcher mWord = pWord.matcher(str);
			while (mWord.find()) {
				if( words.containsKey(mWord.group()) != true) {
					words.put(mWord.group(), 1);
					if (!wordList.contains(mWord.group())) {
						wordList.add(mWord.group());
					}
				} else {
					words.put(mWord.group(), words.get(mWord.group())+1);
					if (!wordList.contains(mWord.group())) {
						wordList.add(mWord.group());
					}
				} 
			}
			
			for(int i=0; i<wordList.size(); i++) {
				results.add(wordList.get(i)+" "+(words.get(wordList.get(i))));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return results;
	}

}  
