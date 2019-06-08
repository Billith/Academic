package model;

import java.time.LocalDate;

public class LognTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;

    public LognTermTicket(boolean isVipTicket, TicketType type, LocalDate validFrom, LocalDate validTo, RoomReservation reservation, Seat seat) {
        super(isVipTicket, type, reservation, seat);
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

}
