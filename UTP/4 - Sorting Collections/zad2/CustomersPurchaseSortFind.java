/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomersPurchaseSortFind {

    List<Purchase> list = new ArrayList<>();

    public void readFile(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));
            while(scan.hasNextLine()) list.add(new Purchase(scan.nextLine()));
        } catch (FileNotFoundException e) {}
    }

    public void showSortedBy(String phrase) {
        switch(phrase) {
            case "Nazwiska" : this.sortBySurname() ;break;
            case "Koszty"   : this.sortByCost();break;
        }
        System.out.println();
    }

    public void sortBySurname() {
        List<Purchase> copy = new ArrayList<>(this.list);
        Collections.sort(copy, Comparator.comparing(Purchase::getSurname).thenComparing(Purchase::getClientId));
        System.out.println("Nazwiska");
        for(Purchase transaction : copy) System.out.println(transaction);
    }

    public void sortByCost() {
        List<Purchase> copy = new ArrayList<>(this.list);
        Collections.sort(copy, ((Comparator<Purchase>) (p1, p2) -> Double.valueOf((p1.getPrice() * p1.getBoughtAmount() - (p2.getPrice() * p2.getBoughtAmount())))
                                                            .intValue()).reversed().thenComparing(Purchase::getClientId));
        System.out.println("Koszty");
        for(Purchase transaction : copy) System.out.println(transaction + " (koszt: " + transaction.getBoughtAmount() * transaction.getPrice() + ")");
    }

    public void showPurchaseFor(String id) {
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : this.list) {
            if(purchase.getClientId().equals(id)) result.add(purchase);
        }
        System.out.println("Klient " + id);
        for(Purchase clientPurchase : result) System.out.println(clientPurchase);
        System.out.println();
    }
}
