package abstract_class;

import o_plus_plus.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class MovieProjectionThreeD extends MovieProjection {

    public MovieProjectionThreeD(String title, String director, int durationInMinutes) {
        this.title = title;
        this.director = director;
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public List<CinemaRoom> getAvailableRooms() {
        List allRooms = ObjectPlus.getClassExtent(CinemaRoom.class);
        List<CinemaRoom> threeDRooms = new ArrayList<>();

        for(Object obj : allRooms) {
            CinemaRoom room = (CinemaRoom) obj;
            if(room.isSupportsTreeD())
                threeDRooms.add(room);
        }

        return threeDRooms;
    }

}
