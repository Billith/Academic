package model;

import model.oplusplus.ObjectPlusPlus;

public class Discount extends ObjectPlusPlus {

    private String name;
    private String requirements;
    private double discountInPercent;

    public Discount(String name, String requirements, int discountInPercent) {
        this.name = name;
        this.requirements = requirements;
        this.discountInPercent = discountInPercent;
    }

    public double getDiscountInPercent() {
        return discountInPercent;
    }

    public String toString() {
        return name + " - " + discountInPercent + "%";
    }
}
