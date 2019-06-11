package model;

import model.oplusplus.ObjectPlusPlus;

public abstract class Ticket extends ObjectPlusPlus {

    private static int ticketCounter = 0;

    protected int ticketId;
    private boolean isVipTicket;
    protected TicketType type;

    public Ticket(boolean isVipTicket, TicketType type, Employee seller) {
        this.ticketId = ++ticketCounter;
        this.isVipTicket = isVipTicket;
        this.type = type;

        this.setSeller(seller);
    }

    public abstract double getFinalPrice();

    public void setSeller(Employee employee) {
        this.addLink("soldTicket", "seller", employee);
    }

    public void addDiscount(Discount discount) {
        this.addLink("appliedDiscount", "ticketsWithDiscount", discount);
    }

    public String toString() {
        return String.format("[ %s, id=%s, vip=%s, type=%s ]", this.getClass().getSimpleName(), ticketId, isVipTicket, type.toString());
    }

}
