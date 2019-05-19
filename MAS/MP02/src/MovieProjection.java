import java.util.Map;
import java.util.TreeMap;

public class MovieProjection {

    String title;
    String director;
    String country;
    int duration;

    Map<Integer, EventTicket> ticketMap = new TreeMap<>();

    public MovieProjection(String title, String director, String country, int duration) {
        this.title = title;
        this.director = director;
        this.country = country;
        this.duration = duration;
    }

    public void addEventTicket(EventTicket ticket) {
        if(!ticketMap.containsKey(ticket.ticketNumber)) {
            ticketMap.put(ticket.ticketNumber, ticket);
            ticket.addMovieProjection(this);
        }
    }

    public EventTicket findEventTicketByTicketNumber(int serialNumber) throws Exception {
        if(!ticketMap.containsKey(serialNumber)) {
            throw new Exception(String.format("[!] Unable to find a ticket with %s serial number", serialNumber));
        }
        return ticketMap.get(serialNumber);
    }

    public String toString() {
        return String.format("[ %s, title=%s, director=%s, country=%s, duration=%s ]", MovieProjection.class.toString(), title, director, country, duration);
    }
}
