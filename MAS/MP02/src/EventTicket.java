public class EventTicket extends ObjectPlusPlus {

    String ticketType;
    double price;

    public EventTicket(String ticketType, double price) {
        this.ticketType = ticketType;
        this.price = price;
    }

    public String toString() {
        return String.format("[ %s, type=%s, price=%s ]", EventTicket.class.toString().replace(" ", "="), ticketType, price);
    }

}