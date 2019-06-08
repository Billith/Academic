package model;

public class SocialEvent extends Event {

    private String title;
    private String description;

    public SocialEvent(RoomType requiredRoomType, String title, String description) {
        super(requiredRoomType);
        this.title = title;
        this.description = description;
    }
}
