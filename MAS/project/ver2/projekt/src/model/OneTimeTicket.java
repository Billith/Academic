package model;

import model.oplusplus.ObjectPlusPlus;

import java.math.BigDecimal;

public class OneTimeTicket extends Ticket {

    private boolean hasLongTermTicket;

    public OneTimeTicket(boolean isVipTicket, TicketType type, Employee seller, boolean hasLongTermTicket, Seat seat, RoomReservation reservation) {
        super(isVipTicket, type, seller);
        this.hasLongTermTicket = hasLongTermTicket;

        this.addLink("ticketSeat", "seatTicket", seat);
        this.addLink("ticketReservation", "reservationTicket", reservation);
    }

    @Override
    public double getFinalPrice() {
        if(!hasLongTermTicket) {
            RoomReservation reservation = null;
            try {
                reservation = (RoomReservation) this.getLinks("ticketReservation")[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            Event reservationEvent = reservation.getEventObject();
            BigDecimal base = reservationEvent.getBaseTicketPrice();

            switch(this.type) {
                case NORMAL: break;
                case SCHOOL: base = base.multiply(BigDecimal.valueOf(0.5)); break;
                case REDUCED: base = base.multiply(BigDecimal.valueOf(0.7)); break;
                case SENIOR: base = base.multiply(BigDecimal.valueOf(0.6)); break;
            }

            try {
                ObjectPlusPlus[] discounts = this.getLinks("appliedDiscount");
                if(discounts.length > 0) {
                    for(ObjectPlusPlus obj : discounts) {
                        Discount discount = (Discount) obj;
                        double reduce = base.doubleValue() * (discount.getDiscountInPercent() / 100);
                        base = base.subtract(BigDecimal.valueOf(reduce));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return base.doubleValue();
        }
        return 0d;
    }

}
