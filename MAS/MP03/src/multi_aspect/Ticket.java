package multi_aspect;

import java.math.BigDecimal;

public abstract class Ticket {

    private int tickerNumber;
    private BigDecimal price;
    private boolean isVipTicket;

    public Ticket(int tickerNumber, BigDecimal price, boolean isVipTicket) {
        this.tickerNumber = tickerNumber;
        this.price = price;
        this.isVipTicket = isVipTicket;
    }

    public BigDecimal getPrice() {
        return price.multiply(vipFreeInPercent);
    }

    private static BigDecimal vipFreeInPercent = new BigDecimal(1.2d);

}
