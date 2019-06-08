package model;

public class OneTimeTicket extends Ticket {

    public OneTimeTicket(boolean isVipTicket, TicketType type, RoomReservation reservation, Seat seat) {
        super(isVipTicket, type, reservation, seat);
    }

}
