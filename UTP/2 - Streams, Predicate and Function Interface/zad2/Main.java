/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;


import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter( s -> s.startsWith("WAW") )
            .map(
                (s) -> {
                    StringTokenizer st = new StringTokenizer(s);
                    String from = st.nextToken();
                    String to = st.nextToken();
                    Double price = Double.parseDouble(st.nextToken());
                    return "to " + to + " - price in PLN: " + Double.valueOf(price * ratePLNvsEUR).intValue();
                })
            .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
