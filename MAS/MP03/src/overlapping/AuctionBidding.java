package overlapping;

import java.math.BigDecimal;

public class AuctionBidding extends ObjectPlusPlus {

    private BigDecimal startingPrice;
    private BigDecimal highestBid;
    private BigDecimal minimalBidDifference;

    public AuctionBidding(BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        this.startingPrice = startingPrice;
        this.minimalBidDifference = minimalBidDifference;
    }
}
