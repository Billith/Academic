package overlapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Auction extends ObjectPlusPlus {

    private static String roleNameBidding = "bindingAuction";
    private static String roleNameBuyNow = "buyNowAuction";
    private static String roleNameGeneralization = "generalization";

    private String title;
    private LocalDateTime expirationTime;
    private int quantity;

    public Auction(String title, LocalDateTime expirationTime, int quantity) {
        super();
        this.title = title;
        this.expirationTime = expirationTime;
        this.quantity = quantity;
    }

    public Auction(String title, LocalDateTime expirationTime, int quantity, BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        super();
        this.title = title;
        this.expirationTime = expirationTime;
        this.quantity = quantity;
        addAuctionBidding(startingPrice, minimalBidDifference);
    }

    public Auction(String title, LocalDateTime expirationTime, int quantity, BigDecimal buyOutPrice) {
        super();
        this.title = title;
        this.expirationTime = expirationTime;
        this.quantity = quantity;
        addAuctionBuyNow(buyOutPrice);
    }

    private void addAuctionBidding(BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        AuctionBidding auction = new AuctionBidding(startingPrice, minimalBidDifference);
        this.addLink(roleNameBidding, roleNameGeneralization, auction);
    }

    private void addAuctionBuyNow(BigDecimal buyOutPrice) {
        AuctionBuyNow auction = new AuctionBuyNow(buyOutPrice);
        this.addLink(roleNameBuyNow, roleNameGeneralization, auction);
    }

}
