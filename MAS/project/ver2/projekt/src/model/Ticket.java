package model;

import model.oplusplus.ObjectPlusPlus;

public abstract class Ticket extends ObjectPlusPlus {

    private static int ticketCounter = 0;

    protected int ticketId;
    private boolean isVipTicket;
    private TicketType type;

    public Ticket(boolean isVipTicket, TicketType type, RoomReservation reservation, Seat seat) {
        this.ticketId = ++ticketCounter;
        this.isVipTicket = isVipTicket;
        this.type = type;

        this.addLink("ticketReservation", "reservationTicket", reservation);
        this.addLink("ticketSeat", "seatTicket", seat);
    }

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
