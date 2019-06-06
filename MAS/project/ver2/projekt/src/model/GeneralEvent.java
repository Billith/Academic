package model;

public class GeneralEvent extends Event {

    private String title;
    private String description;

    public GeneralEvent(RoomType requiredRoomType, String title, String description) {
        super(requiredRoomType);
        this.title = title;
        this.description = description;
    }
}
