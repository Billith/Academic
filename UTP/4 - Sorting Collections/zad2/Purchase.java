/**
 *
 *  @author Dyduch Łukasz S15599
 *
 */

package zad2;


public class Purchase {
    private String clientId;
    private String surname;
    private String firstName;
    private String productName;
    private Double price;
    private Double boughtAmount;

    public Purchase(String line) {
        String[] splitted = line.split(";");
        String[] splittedName = splitted[1].split(" ");

        this.clientId = splitted[0];
        this.surname = splittedName[0];
        this.firstName = splittedName[1];
        this.productName = splitted[2];
        this.price = Double.valueOf(splitted[3]);
        this.boughtAmount = Double.valueOf(splitted[4]);
    }

    public String getClientId() {
        return clientId;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Double getBoughtAmount() {
        return boughtAmount;
    }

    @Override
    public String toString() {
        return this.clientId + ";" + this.surname + " " + this.firstName + ";" + this.productName + ";" + this.price + ";" + this.boughtAmount;
    }
}
