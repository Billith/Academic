package model;

import model.oplusplus.ObjectPlusPlus;

/**
 * The class represents discount in the system
 */
public class Discount extends ObjectPlusPlus {

    private String name;
    private String requirements;
    private double discountInPercent;

    /**
     * The constructor
     * @param name
     * @param requirements
     * @param discountInPercent
     */
    public Discount(String name, String requirements, int discountInPercent) {
        this.name = name;
        this.requirements = requirements;
        this.discountInPercent = discountInPercent;
    }

    /**
     * Function return percent of a discount
     * @return
     */
    public double getDiscountInPercent() {
        return discountInPercent;
    }

    /**
     * Function returns discount as a string in format "name - percent %"
     * @return
     */
    public String toString() {
        return name + " - " + discountInPercent + "%";
    }
}
