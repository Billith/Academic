package overlapping;

import o_plus_plus.ObjectPlusPlus;

import java.math.BigDecimal;

public class BiddingAuction extends ObjectPlusPlus {

    private BigDecimal startingPrice;
    private BigDecimal highestBid;
    private BigDecimal minimalBidDifference;

    public BiddingAuction(BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        this.startingPrice = startingPrice;
        this.minimalBidDifference = minimalBidDifference;
    }

    public boolean bidInAnAuction(BigDecimal newBid) {
        if(highestBid != null) {
            if((newBid.compareTo(startingPrice) == -1) && (newBid.compareTo(highestBid) == -1)) {
                if (newBid.subtract(highestBid).compareTo(minimalBidDifference) == -1) {
                    this.highestBid = newBid;
                    return true;
                }
            }
        } else {
            highestBid = newBid;
        }

        return false;
    }
}
