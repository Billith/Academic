package model;

import model.oplusplus.ObjectPlusPlus;

import java.math.BigDecimal;

/**
 * The class represents one time ticket in the system. The ticket is associated with room reservation for particular
 * event and reserved seat.
 */
public class OneTimeTicket extends Ticket {

    private boolean hasLongTermTicket;

    /**
     * The constructor
     * @param isVipTicket
     * @param type
     * @param seller
     * @param hasLongTermTicket
     * @param seat
     * @param reservation
     */
    public OneTimeTicket(boolean isVipTicket, TicketType type, Employee seller, boolean hasLongTermTicket, Seat seat, RoomReservation reservation) {
        super(isVipTicket, type, seller);
        this.hasLongTermTicket = hasLongTermTicket;

        this.addLink("ticketSeat", "seatTicket", seat);
        this.addLink("ticketReservation", "reservationTicket", reservation);
    }

    /**
     * Function calculates and returns final price of the ticket. It takes into account discount associated with this
     * ticket and type of the ticket.
     * @return
     */
    @Override
    public double getFinalPrice() {
        if(!hasLongTermTicket) {
            RoomReservation reservation = null;
            try {
                reservation = (RoomReservation) this.getLinks("ticketReservation")[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            Event reservationEvent = reservation.getEvent();
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
