import abstract_class.CinemaRoom;
import abstract_class.MovieProjection;
import abstract_class.MovieProjectionThreeD;
import abstract_class.MovieProjectionTwoD;
import overlapping.Auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception {

        // Klasa abstr/ polimorfizm
        CinemaRoom room1 = new CinemaRoom(true, false);
        CinemaRoom room2 = new CinemaRoom(true, false);
        CinemaRoom room3 = new CinemaRoom(true, true);

        MovieProjection mp1 = new MovieProjectionTwoD("title", "dir", 150);
        MovieProjection mp2 = new MovieProjectionThreeD("title", "dir", 125);

        System.out.println(mp1.getAvailableRooms());
        System.out.println(mp2.getAvailableRooms());

        // Overlapping
        Auction auction = new Auction(
                "title",
                LocalDateTime.now(),
                2,
                new BigDecimal(200),
                new BigDecimal(5),
                new BigDecimal(300)
        );
        auction.bidInAnAuction(new BigDecimal(10));
        auction.buyOutAuction();

        // Wielodziedziczenie

    }
}
