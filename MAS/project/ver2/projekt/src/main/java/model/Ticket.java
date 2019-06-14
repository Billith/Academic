package model;

import model.oplusplus.ObjectPlusPlus;

/**
 * The class represents ticket in the system
 */
public abstract class Ticket extends ObjectPlusPlus {

    private static int ticketCounter = 0;

    protected int ticketId;
    private boolean isVipTicket;
    protected TicketType type;

    /**
     * The constructor. On every call ticket counter is incremented, which provides unique ID for every ticket.
     * @param isVipTicket
     * @param type
     * @param seller
     */
    public Ticket(boolean isVipTicket, TicketType type, Employee seller) {
        this.ticketId = ++ticketCounter;
        this.isVipTicket = isVipTicket;
        this.type = type;

        this.setSeller(seller);
    }

    /**
     * Function calculates and returns final price of the ticket.
     * @return final price of the ticket
     */
    public abstract double getFinalPrice();

    /**
     * Function sets seller of the ticket
     * @param employee
     */
    public void setSeller(Employee employee) {
        this.addLink("soldTicket", "seller", employee);
    }

    /**
     * Function adds discount association with the ticket
     * @param discount
     */
    public void addDiscount(Discount discount) {
        this.addLink("appliedDiscount", "ticketsWithDiscount", discount);
    }

    public String toString() {
        return String.format("[ %s, id=%s, vip=%s, type=%s ]", this.getClass().getSimpleName(), ticketId, isVipTicket, type.toString());
    }

}
