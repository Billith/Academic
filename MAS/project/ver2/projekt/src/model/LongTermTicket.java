package model;

import java.time.LocalDate;
import java.time.Period;

public class LongTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;
    private static double pricePerMonth = 60;

    public LongTermTicket(boolean isVipTicket, TicketType type, LocalDate validFrom, LocalDate validTo, Employee seller) {
        super(isVipTicket, type, seller);
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public double getFinalPrice() {
        Period period = Period.between(validFrom, validTo);
        return period.toTotalMonths() * pricePerMonth;
    }
}
