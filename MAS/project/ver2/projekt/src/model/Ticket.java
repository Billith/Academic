package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.List;

public abstract class Ticket extends ObjectPlusPlus {

    private static int ticketCounter = 0;

    protected int ticketId;
    private boolean isVipTicket;
    private TicketType type;
//    private Employee seller;
    private List<Discount> discountList;
    // protected BigDecimal price; //TODO
    // Asocjacja z siedzeniem w sali kinowej, na konkretnym seansie // TODO

    public Ticket(boolean isVipTicket, TicketType type) {
        this.ticketId = ++ticketCounter;
        this.isVipTicket = isVipTicket;
        this.type = type;
//        this.seller = seller;
        // this.price = price; // Cena powinna być brana z ascojacji z filmem/siedzeniem w kinie // TODO
    }

//    public void setSeller(Employee employee) {
//        if(seller != null) {
//            try {
//                seller.removeSoldTicket(this);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        this.seller = employee;
//        employee.addSoldTicket(this);
//    }

    public void addSeller(Employee employee) {
        this.addLink("soldTicket", "seller", employee);
    }


    public void addDiscount(Discount discount) {
        if(!discountList.contains(discount)) {
            discountList.add(discount);
            discount.addTicket(this);
        }
        if(!discount.containsTicket(this)) {
            discount.addTicket(this);
        }
    }

    public boolean containsDiscount(Discount discount) {
        return discountList.contains(discount);
    }

}
