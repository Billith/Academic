/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad4;



import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    String fname = System.getProperty("user.home") + "/textforwords.txt";
    CountWords cw = new CountWords(fname);
    List<String> result = cw.getResult();
    for (String wordRes : result) {
      System.out.println(wordRes);
    }
  }
}
