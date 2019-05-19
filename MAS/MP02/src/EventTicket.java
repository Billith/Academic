public class EventTicket {

    static int numberOfSoldTickets = 1;

    int ticketNumber;
    String ticketType;
    double price;
    Employee seller;
    MovieProjection movie;

    public EventTicket(String ticketType, double price) {
        this.ticketNumber = numberOfSoldTickets;
        this.ticketType = ticketType;
        this.price = price;
        numberOfSoldTickets++;
    }

    public void setSeller(Employee emp) {
        if(seller != null) {
            emp.soldEventTickets.remove(this);
        }
        seller = emp;
    }

    public Employee getSeller() {
        return seller;
    }

    public String toString() {
        return String.format("[ %s, serialNo=%s, type=%s, price=%s, seller=%s ]", EventTicket.class.toString().replace(" ", "="), ticketNumber, ticketType, price, seller);
    }

    public void addMovieProjection(MovieProjection newMovieProjection) {
        if(movie != null) {
            movie.ticketMap.remove(this.ticketNumber);
        }
        movie = newMovieProjection;
    }
}