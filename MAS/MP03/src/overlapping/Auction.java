package overlapping;

import o_plus_plus.ObjectPlusPlus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Auction extends ObjectPlusPlus {

    private static String roleNameBidding = "bindingAuction";
    private static String roleNameBuyNow = "buyNowAuction";
    private static String roleNameGeneralization = "generalization";

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
        addAuctionBidding(startingPrice, minimalBidDifference);
        addAuctionBuyNow(buyOutPrice);
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

    public void setActive(boolean active) {
        isActive = active;
    }

    private void addAuctionBidding(BigDecimal startingPrice, BigDecimal minimalBidDifference) {
        AuctionBidding auction = new AuctionBidding(startingPrice, minimalBidDifference);
        this.addLink(roleNameBidding, roleNameGeneralization, auction);
    }

    private void addAuctionBuyNow(BigDecimal buyOutPrice) {
        AuctionBuyNow auction = new AuctionBuyNow(buyOutPrice);
        this.addLink(roleNameBuyNow, roleNameGeneralization, auction);
    }

    public void bidInAnAuction(BigDecimal newBid) throws Exception {
        try {
            ObjectPlusPlus[] obj = this.getLinks(roleNameBidding);
            boolean res = ((AuctionBidding)obj[0]).bidInAnAuction(newBid);
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

}
