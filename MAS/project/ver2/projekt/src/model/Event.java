package model;

import model.oplusplus.ObjectPlusPlus;

public abstract class Event extends ObjectPlusPlus {

    private RoomType requiredRoomType;

    public Event(RoomType requiredRoomType) {
        this.requiredRoomType = requiredRoomType;
    }

    public abstract String getEventName() throws Exception;

}
