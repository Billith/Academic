package abstract_class;

import o_plus_plus.ObjectPlus;

import java.util.List;

public abstract class MovieProjection extends ObjectPlus {

    protected String title;
    protected String director;
    protected int durationInMinutes;

    public abstract List<CinemaRoom> getAvailableRooms();

    public String toString() {
        return String.format("[ %s, title=%s, director=%s, duration=%s ]", this.getClass().toString(), title, director, durationInMinutes);
    }

}

