package model;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 *  The class represents long term ticket in the system. This type of ticket that allow client for unlimited entries on
 *  various event in cinema. Is client has this LongTerm ticket he still needs to but OneTimeTicket, but as long as
 *  LongTermTicket is valid, the price for OneTimeTicket will be 0.
 */
public class LongTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;
    private static double pricePerMonth = 60;

    /**
     * The constructor
     * @param isVipTicket
     * @param type
     * @param validFrom
     * @param validTo
     * @param seller
     */
    public LongTermTicket(boolean isVipTicket, TicketType type, LocalDate validFrom, LocalDate validTo, Employee seller) {
        super(isVipTicket, type, seller);
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    /**
     * Function calculates and returns final price of the ticket multiplying number of month and price for one month.
     * It takes into account type of the ticket.
     * @return
     */
    @Override
    public double getFinalPrice() {
        Period period = Period.between(validFrom, validTo);
        return period.toTotalMonths() * pricePerMonth;
    }
}
