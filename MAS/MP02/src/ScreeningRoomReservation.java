import java.time.LocalDateTime;

public class ScreeningRoomReservation {

    LocalDateTime reservationStartDate;
    LocalDateTime reservationEndDate;
    ScreeningRoomReservationStatus reservationStatus;

    ScreeningRoom reservedScreeningRoom;
    SocialEvent socialEvent;

    public ScreeningRoomReservation(LocalDateTime reservationStartDate, LocalDateTime reservationEndDate, ScreeningRoom reservedScreeningRoom, SocialEvent socialEvent) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.reservationStatus = ScreeningRoomReservationStatus.ACTIVE;

        this.reservedScreeningRoom = reservedScreeningRoom;
        this.socialEvent = socialEvent;

        reservedScreeningRoom.allScreeningRoomReservations.add(this);
        socialEvent.allScreeningRoomReservations.add(this);
    }

    public boolean isOverlapping(LocalDateTime start, LocalDateTime end) {
        if((start.isAfter(reservationStartDate) && start.isBefore(reservationEndDate)) ||
            (end.isAfter(reservationStartDate) && end.isBefore(reservationEndDate)) ||
            start.isEqual(reservationStartDate) || start.isEqual(reservationEndDate) ||
            end.isEqual(reservationStartDate) || end.isEqual(reservationEndDate)
        ) {
            return true;
        }
        return false;
    }

    public enum ScreeningRoomReservationStatus {
        ACTIVE, CANCELED, UNACTIVE
    }

    public String toString() {
        return String.format("[ start=%s, end=%s, room=%s, event=%s ]", reservationStartDate.toString(), reservationEndDate.toString(), reservedScreeningRoom, socialEvent);
    }

}
