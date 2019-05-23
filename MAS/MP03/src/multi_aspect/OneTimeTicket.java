package multi_aspect;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OneTimeTicket extends Ticket {

    private LocalDate movieProjectionDate;

    public OneTimeTicket(int tickerNumber, BigDecimal price, LocalDate movieProjectionDate) {
        super(tickerNumber, price);
        this.movieProjectionDate = movieProjectionDate;
    }

}
