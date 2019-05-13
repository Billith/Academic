package model;

public class ScreeningRoom extends ObjectPlus {

    int screeningRoomPhysicalId;
    int maximumCapacity;
    ScreeningRoomType screeningRoomType;

    public ScreeningRoom(int screeningRoomPhysicalId, int maximumCapacity, ScreeningRoomType screeningRoomType) {
        this.screeningRoomPhysicalId = screeningRoomPhysicalId;
        this.maximumCapacity = maximumCapacity;
        this.screeningRoomType = screeningRoomType;
    }

}
