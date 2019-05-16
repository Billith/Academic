public class EventTicket {

    static int numberOfSoldTickets = 1;

    int serialNumber;
    String ticketType;
    double price;
    Employee seller;
    MovieProjection movie;

    public EventTicket(String ticketType, double price) {
        this.serialNumber = numberOfSoldTickets;
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
        return String.format("[ %s, serialNo=%s, type=%s, price=%s, seller=%s ]", EventTicket.class.toString().replace(" ", "="), serialNumber, ticketType, price, seller);
    }

    public void addMovieProjection(MovieProjection newMovieProjection) {
        if(movie != null) {
            movie.ticketMap.remove(this.serialNumber);
        }
        movie = newMovieProjection;
    }
}