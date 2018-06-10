/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calc {
	public String doCalc(String args) {
		Calc kalkulator = new Calc();
		Scanner n1 = new Scanner(args);
		BigDecimal l1 = BigDecimal.ZERO;
		BigDecimal l2 = BigDecimal.ZERO;
		String op = "";
		try {
			l1 = n1.nextBigDecimal();
			op = n1.next("[+-||*/]{1}?");
			l2 = n1.nextBigDecimal();
		} catch ( Exception e ) {
			System.out.println("Invalid command to calc");
		}
		n1.close();

		Dzialanie dodawanie = (a, b) -> a.add(b);
		Dzialanie odejmowanie = (a, b) -> a.subtract(b);
		Dzialanie dzielenie = (a, b) -> a.divide(b, 7, RoundingMode.HALF_UP);
		Dzialanie mnozenie = (a, b) -> a.multiply(b);
		
		Map<String, BigDecimal> operacje = new HashMap<>();
		operacje.put("+", kalkulator.wykonaj(l1, l2, dodawanie));
		operacje.put("-", kalkulator.wykonaj(l1, l2, odejmowanie));
		operacje.put("/", kalkulator.wykonaj(l1, l2, dzielenie));
		operacje.put("*", kalkulator.wykonaj(l1, l2, mnozenie));
		
		//return (operacje.get(op)).wynik.toPlainString();
		return operacje.get(op).toString();
	}
	
	private BigDecimal wykonaj( BigDecimal A, BigDecimal B, Dzialanie dzialanie) {
		return dzialanie.policz(A, B);
	}
}  
