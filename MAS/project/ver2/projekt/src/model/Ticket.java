package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.List;

public abstract class Ticket extends ObjectPlusPlus {

    private static int ticketCounter = 0;

    protected int ticketId;
    private boolean isVipTicket;
    private TicketType type;
    private List<Discount> discountList;
    // protected BigDecimal price; //TODO
    // Asocjacja z siedzeniem w sali kinowej, na konkretnym seansie // TODO

    public Ticket(boolean isVipTicket, TicketType type) {
        this.ticketId = ++ticketCounter;
        this.isVipTicket = isVipTicket;
        this.type = type;
    }

    public void addSeller(Employee employee) {
        this.addLink("soldTicket", "seller", employee);
    }

    public void addDiscount(Discount discount) {
        this.addLink("appliedDiscount", "ticketsWithDiscount", discount);
    }

    public String toString() {
        return String.format("[ %s, id=%s, vip=%s, type=%s ]", this.getClass().getSimpleName(), ticketId, isVipTicket, type.toString());
    }

}
