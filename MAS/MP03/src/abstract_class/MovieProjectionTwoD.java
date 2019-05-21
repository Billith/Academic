package abstract_class;

import java.util.ArrayList;
import java.util.List;

public class MovieProjectionTwoD extends MovieProjection {

    public MovieProjectionTwoD(String title, String director, int durationInMinutes) {
        this.title = title;
        this.director = director;
        this.durationInMinutes = durationInMinutes;
    }

    @Override
    public List<CinemaRoom> getAvailableRooms() {
        List allRooms = ObjectPlus.getClassExtent(CinemaRoom.class);
        List<CinemaRoom> twoDRooms = new ArrayList<>();

        for(Object obj : allRooms) {
            CinemaRoom room = (CinemaRoom) obj;
            if(room.isSupportsTwoD())
                twoDRooms.add(room);
        }

        return twoDRooms;
    }

}
