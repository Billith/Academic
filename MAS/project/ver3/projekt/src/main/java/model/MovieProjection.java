package model;

import java.math.BigDecimal;

/**
 * The class represents movie projection in the system
 */
public class MovieProjection extends Event {

    /**
     * The constructor
     * @param requiredRoomType
     * @param baseTicketPrice
     * @param movie
     */
    public MovieProjection(RoomType requiredRoomType, BigDecimal baseTicketPrice, Movie movie) {
        super(requiredRoomType, baseTicketPrice);
        this.addLink("filmToDisplay", "displayedMovie", movie);
    }

    @Override
    public String getEventName() throws Exception {
        return ((Movie) this.getLinks("filmToDisplay")[0]).getTitle();
    }
}
