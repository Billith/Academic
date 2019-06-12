package model;

import java.math.BigDecimal;

public class SocialEvent extends Event {

    private String title;
    private String description;

    public SocialEvent(RoomType requiredRoomType, BigDecimal baseTicketPrice, String title, String description) {
        super(requiredRoomType, baseTicketPrice);
        this.title = title;
        this.description = description;
    }

    @Override
    public String getEventName() {
        return this.title;
    }

}
