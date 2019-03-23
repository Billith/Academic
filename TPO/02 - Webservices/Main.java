/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad1;


import zad1.frontend.MainWindow;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...
    MainWindow.startUI(s, rate1, rate2);
    // część uruchamiająca GUI
  }
}
