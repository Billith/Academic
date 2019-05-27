package model;

import java.time.LocalDate;

public class LognTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;

    public LognTermTicket(LocalDate validFrom, LocalDate validTo) {
        super();
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

}
