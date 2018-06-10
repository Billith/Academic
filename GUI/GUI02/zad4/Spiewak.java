/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Spiewak {
	static int index = 1;
	int objIndex;
	String nazwisko;
	int power = 0;
	
	public Spiewak(String nazwisko) {
		this.nazwisko = nazwisko;
		this.objIndex = index;
		index++;
	}
	
	abstract String spiewaj();
	
	public String toString() {
		return "("+this.objIndex+") "+this.nazwisko+": "+spiewaj();
	}
	
	static public Spiewak najglosniej(Spiewak tab[]) {
		Spiewak objMax = null;
		int highestVol = 0;
		String regex = "[A-Z]{1}";
		Pattern caplet = Pattern.compile(regex);
		for (Spiewak s : tab) {
			Matcher capLet = caplet.matcher(s.spiewaj());
			while(capLet.find()) {
				s.power++;
			}
			if(s.power > highestVol) {
				highestVol = s.power;
				objMax = s;
			}
		}
		return objMax;
	}
}
