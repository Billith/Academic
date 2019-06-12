package model;

import model.oplusplus.ObjectPlusPlus;

import java.math.BigDecimal;

public abstract class Event extends ObjectPlusPlus {

    private RoomType requiredRoomType;
    private BigDecimal baseTicketPrice;

    public Event(RoomType requiredRoomType, BigDecimal baseTicketPrice) {
        this.requiredRoomType = requiredRoomType;
        this.baseTicketPrice = baseTicketPrice;
    }

    public abstract String getEventName() throws Exception;

    public BigDecimal getBaseTicketPrice() {
        return baseTicketPrice;
    }
}
