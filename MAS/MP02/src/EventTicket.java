public class EventTicket {

    String ticketType;
    double price;
    Employee seller;

    public EventTicket(String ticketType, double price) {
        this.ticketType = ticketType;
        this.price = price;
    }

    public void setSeller(Employee emp) {
        if(seller == null) {
            seller = emp;
        } else {

        }
    }

    public Employee getSeller() {
        return seller;
    }

    public String toString() {
        return String.format("[ %s, type=%s, price=%s, seller=%s ]", EventTicket.class.toString().replace(" ", "="), ticketType, price, seller);
    }

}