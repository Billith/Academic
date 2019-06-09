package model;

import java.math.BigDecimal;

public class MovieProjection extends Event {

    private BigDecimal baseTicketPrice;
    private TranslationType translationType;

    public MovieProjection(RoomType requiredRoomType, BigDecimal baseTicketPrice, Movie movie) {
        super(requiredRoomType);
        this.baseTicketPrice = baseTicketPrice;
        this.addLink("filmToDisplay", "displayedMovie", movie);
    }

    @Override
    public String getEventName() throws Exception {
        return ((Movie) this.getLinks("filmToDisplay")[0]).getTitle();
    }
}
