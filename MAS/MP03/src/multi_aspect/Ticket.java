package multi_aspect;

import java.math.BigDecimal;

public abstract class Ticket {

    private int tickerNumber;
    private BigDecimal price;

    public Ticket(int tickerNumber, BigDecimal price) {
        this.tickerNumber = tickerNumber;
        this.price = price;
    }
}
