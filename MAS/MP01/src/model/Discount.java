package model;

public class Discount extends ObjectPlus {

    private static int globalDiscount = 0;

    String discountCondition;
    double discountPercent;

    public Discount(String discountCondition, double discountPercent) {
        this.discountCondition = discountCondition;
        this.discountPercent = discountPercent;
    }

    public static int getGlobalDiscount() {
        return globalDiscount;
    }

}
