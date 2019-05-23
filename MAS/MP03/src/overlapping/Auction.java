package overlapping;

import o_plus_plus.ObjectPlusPlus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Auction extends ObjectPlusPlus {

    private String title;
    private LocalDateTime expirationTime;
    private int quantity;
    private boolean isActive;

    public Auction(String title, LocalDateTime expirationTime, int quantity, BigDecimal startingPrice, BigDecimal minimalBidDifference, BigDecimal buyOutPrice) {
        super();
        this.title = title;
        this.expirationTime = expirationTime;
        this.quantity = quantity;
        this.isActive = true;
        addBiddingAuction(startingPrice, minimalBidDifference);
        addBuyNowAuction(buyOutPrice);
    }

    public Auction(String title, LocalDateTime expirationTime, int quantity, BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        super();
        this.title = title;
        this.expirationTime = expirationTime;
        this.quantity = quantity;
        addBiddingAuction(startingPrice, minimalBidDifference);
    }

    public Auction(String title, LocalDateTime expirationTime, int quantity, BigDecimal buyOutPrice) {
        super();
        this.title = title;
        this.expirationTime = expirationTime;
        this.quantity = quantity;
        addBuyNowAuction(buyOutPrice);
    }

    private void addBiddingAuction(BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        BiddingAuction auction = new BiddingAuction(startingPrice, minimalBidDifference);
        this.addLink(roleNameBidding, roleNameGeneralization, auction);
    }

    private void addBuyNowAuction(BigDecimal buyOutPrice) {
        buyOutAuction auction = new buyOutAuction(buyOutPrice);
        this.addLink(roleNameBuyNow, roleNameGeneralization, auction);
    }

    public void bidInAnAuction(BigDecimal newBid) throws Exception {
        try {
            ObjectPlusPlus[] obj = this.getLinks(roleNameBidding);
            boolean res = ((BiddingAuction)obj[0]).bidInAnAuction(newBid);
            this.setActive(!res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Tried call bidInAnAuction function on non biding auction object");
        }
    }

    public void buyOutAuction() throws Exception {
        try {
            ObjectPlusPlus[] obj = this.getLinks(roleNameBuyNow);
            this.setActive(false);
        } catch (Exception e) {
            throw new Exception("Tried call buyOutAuction function on non buyout auction object");
        }
    }

    private void setActive(boolean active) {
        isActive = active;
    }

    private final static String roleNameBidding = "bindingAuction";
    private final static String roleNameBuyNow = "buyNowAuction";
    private final static String roleNameGeneralization = "generalization";

}
