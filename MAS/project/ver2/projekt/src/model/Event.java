package model;

import model.oplusplus.ObjectPlusPlus;

public abstract class Event extends ObjectPlusPlus {

    private RoomType requiredRoomType;

    public Event(RoomType requiredRoomType) {
        this.requiredRoomType = requiredRoomType;
    }
//
//    public RoomType getRequiredRoomType() {
//        return requiredRoomType;
//    }

}
