package model;

import java.time.LocalDate;

public class LognTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;

    public LognTermTicket(boolean isVipTicket, TicketType type, LocalDate validFrom, LocalDate validTo) {
        super(isVipTicket, type);
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

}
