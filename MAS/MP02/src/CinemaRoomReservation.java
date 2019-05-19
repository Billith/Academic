import java.time.LocalDateTime;

public class CinemaRoomReservation {

    LocalDateTime reservationStartDate;
    LocalDateTime reservationEndDate;

    CinemaRoom reservedCinemaRoom;
    SocialEvent socialEvent;

    public CinemaRoomReservation(LocalDateTime reservationStartDate, LocalDateTime reservationEndDate, CinemaRoom reservedCinemaRoom, SocialEvent socialEvent) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;

        this.reservedCinemaRoom = reservedCinemaRoom;
        this.socialEvent = socialEvent;

        reservedCinemaRoom.addCinemaRoomReservation(this);
        socialEvent.addEventReservation(this);
    }

    public boolean isOverlapping(LocalDateTime start, LocalDateTime end) {
        return ((start.isAfter(reservationStartDate) && start.isBefore(reservationEndDate)) ||
           (end.isAfter(reservationStartDate) && end.isBefore(reservationEndDate))) ? true : false;
    }

    public boolean isEqual(LocalDateTime start, LocalDateTime end) {
        return start.isEqual(reservationStartDate) && end.isEqual(reservationEndDate);
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        if(reservedCinemaRoom != null) {
            reservedCinemaRoom.removeCinemaRoomReservation(this);
        }
        reservedCinemaRoom = cinemaRoom;
        cinemaRoom.addCinemaRoomReservation(this);
    }

    public String toString() {
        return String.format("[ start=%s, end=%s, room=%s, event=%s ]", reservationStartDate.toString(), reservationEndDate.toString(), reservedCinemaRoom, socialEvent);
    }

}
