package multi_aspect;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LongTermTicket extends Ticket {

    private LocalDate validFrom;
    private LocalDate validTo;
    private int boughtDays;

    public LongTermTicket(int tickerNumber, BigDecimal price, boolean isVipTicket, LocalDate validFrom, LocalDate validTo) {
        super(tickerNumber, price, isVipTicket);
    }

    public void activateTicket() {
        this.validFrom = LocalDate.now();
        this.validTo = this.validFrom.plusDays(boughtDays);
    }

}
