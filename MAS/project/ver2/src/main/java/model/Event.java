package model;

import model.oplusplus.ObjectPlusPlus;

import java.math.BigDecimal;

/**
 * The class represents event in the system
 */
public abstract class Event extends ObjectPlusPlus {

    private RoomType requiredRoomType;
    private BigDecimal baseTicketPrice;

    /**
     * The constructor
     * @param requiredRoomType
     * @param baseTicketPrice
     */
    public Event(RoomType requiredRoomType, BigDecimal baseTicketPrice) {
        this.requiredRoomType = requiredRoomType;
        this.baseTicketPrice = baseTicketPrice;
    }

    /**
     * Function returns the event name. Way to get it may differ in different event subclasses.
     * @return
     * @throws Exception
     */
    public abstract String getEventName() throws Exception;

    /**
     * Function returns base ticket price for the event
     * @return
     */
    public BigDecimal getBaseTicketPrice() {
        return baseTicketPrice;
    }
}
