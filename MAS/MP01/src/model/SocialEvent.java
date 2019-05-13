package model;

import java.net.URL;

public class SocialEvent extends Event {
    String name;
    String organizer;
    URL eventUrl;

    public SocialEvent(String name, String organizer, URL eventUrl) {
        this.name = name;
        this.organizer = organizer;
        this.eventUrl = eventUrl;
    }
}
