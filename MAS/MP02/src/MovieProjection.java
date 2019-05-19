import java.util.Map;
import java.util.TreeMap;

public class MovieProjection {

    private String title;
    private String director;
    private String country;
    private int duration;

    private Map<Integer, EventTicket> ticketMap = new TreeMap<>();

    public MovieProjection(String title, String director, String country, int duration) {
        this.title = title;
        this.director = director;
        this.country = country;
        this.duration = duration;
    }

    public void addEventTicket(EventTicket ticket) {
        if(!ticketMap.containsKey(ticket.getTicketNumber())) {
            ticketMap.put(ticket.getTicketNumber(), ticket);
            ticket.addMovieProjection(this);
        }
    }

    public void removeEventTicket(EventTicket ticket) {
        if(ticketMap.containsKey(ticket.getTicketNumber())) {
            ticketMap.remove(ticket.getTicketNumber());
        }
    }

    public EventTicket findEventTicket(int serialNumber) throws Exception {
        if(!ticketMap.containsKey(serialNumber)) {
            throw new Exception(String.format("[!] Unable to find a ticket with %s serial number", serialNumber));
        }
        return ticketMap.get(serialNumber);
    }

    public String toString() {
        return String.format("[ %s, title=%s, director=%s, country=%s, duration=%s ]", MovieProjection.class.toString(), title, director, country, duration);
    }
}
