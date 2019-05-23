package multi_aspect;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LongTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;

    public LongTermTicket(int tickerNumber, BigDecimal price, LocalDate validFrom, LocalDate validTo) {
        super(tickerNumber, price);
        this.validFrom = validFrom;
        this.validTo = validTo;
    }
}
