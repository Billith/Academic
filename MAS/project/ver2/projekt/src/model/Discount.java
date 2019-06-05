package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.List;

public class Discount extends ObjectPlusPlus {

    private String name;
    private String requirements;
    private int discountInPercent;
    private List<Ticket> ticketList;

    public Discount(String name, String requirements, int discountInPercent) {
        this.name = name;
        this.requirements = requirements;
        this.discountInPercent = discountInPercent;
    }

}
