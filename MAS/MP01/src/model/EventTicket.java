package model;

import java.util.List;

public class EventTicket extends ObjectPlus {
    EventTicketType ticketType;
    double price;

    public EventTicket(EventTicketType ticketType, double price) {
        this.ticketType = ticketType;
        this.price = price;
    }

}


