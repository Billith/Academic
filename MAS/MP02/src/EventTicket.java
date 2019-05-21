public class EventTicket {

    private static int numberOfSoldTickets = 1;

    private int ticketNumber;
    private String ticketType;
    private double price;
    private Employee seller;
    private MovieProjection movie;

    public EventTicket(String ticketType, double price) {
        this.ticketNumber = numberOfSoldTickets;
        this.ticketType = ticketType;
        this.price = price;
        numberOfSoldTickets++;
    }

    public void setSeller(Employee emp) {
        if(seller != null) {
            emp.removeEventTicket(this);
        }
        seller = emp;
    }

    public Employee getSeller() {
        return seller;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String toString() {
        return String.format("[ %s, serialNo=%s, type=%s, price=%s, seller=%s ]", EventTicket.class.toString().replace(" ", "="), ticketNumber, ticketType, price, seller);
    }

    public void addMovieProjection(MovieProjection newMovieProjection) {
        if(movie != null) {
            movie.removeEventTicket(this);
        }
        movie = newMovieProjection;
    }
}