import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SocialEvent extends Event {

    String name;
    String organizer;
    URL eventUrl;

    List<ScreeningRoomReservation> allScreeningRoomReservations = new ArrayList<>();

    public SocialEvent(String name, String organizer, URL eventUrl) {
        this.name = name;
        this.organizer = organizer;
        this.eventUrl = eventUrl;
    }

    public void reserveScreeningRoom(ScreeningRoom screeningRoom, LocalDateTime reservationStart, LocalDateTime reservationEnd) throws Exception {
        for(ScreeningRoomReservation reservation : screeningRoom.allScreeningRoomReservations) {
            if(reservation.reservedScreeningRoom == screeningRoom && reservation.isOverlapping(reservationStart, reservationEnd))
                throw new Exception("[!] Reservation is overlapping with another reservation!");
        }

        ScreeningRoomReservation newReservation = new ScreeningRoomReservation(reservationStart, reservationEnd, screeningRoom, this);
    }

}