import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SocialEvent extends Event {

    String name;
    String organizer;
    URL eventUrl;

    List<CinemaRoomReservation> allCinemaRoomReservations = new ArrayList<>();

    public SocialEvent(String name, String organizer, URL eventUrl) {
        this.name = name;
        this.organizer = organizer;
        this.eventUrl = eventUrl;
    }

    public void reserveCinemaRoom(CinemaRoom cinemaRoom, LocalDateTime reservationStart, LocalDateTime reservationEnd) throws Exception {

        if(reservationStart.isAfter(reservationEnd))
            throw new Exception("[!] end cannot be earlier then start!");

        for(CinemaRoomReservation reservation : cinemaRoom.allCinemaRoomReservations) {
            if (reservation.isOverlapping(reservationStart, reservationEnd))
                throw new Exception("[!] Reservation is overlapping with another reservation!");

            if (reservation.isEqual(reservationStart, reservationEnd)) {
                if(reservation.reservedCinemaRoom == cinemaRoom) {
                    throw new Exception("[!] Reservation for this room at that start and end time exists!");
                }
                reservation.setCinemaRoom(cinemaRoom);
                return;
            }
        }

        for(CinemaRoomReservation reservation : allCinemaRoomReservations) {
            if (reservation.isEqual(reservationStart, reservationEnd)) {
                reservation.setCinemaRoom(cinemaRoom);
                return;
            }
        }

        CinemaRoomReservation newReservation = new CinemaRoomReservation(reservationStart, reservationEnd, cinemaRoom, this);
    }

    public void printAllReservations() {
        for(CinemaRoomReservation reservation : allCinemaRoomReservations) {
            System.out.println(reservation);
        }
        System.out.println("------------------------------------");
    }

}