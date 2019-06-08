package model;

import model.oplusplus.ObjectPlusPlus;

public class Discount extends ObjectPlusPlus {

    private String name;
    private String requirements;
    private int discountInPercent;

    public Discount(String name, String requirements, int discountInPercent) {
        this.name = name;
        this.requirements = requirements;
        this.discountInPercent = discountInPercent;
    }

}
