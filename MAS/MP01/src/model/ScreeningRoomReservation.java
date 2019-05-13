package model;

import java.util.Date;

public class ScreeningRoomReservation extends ObjectPlus {
    Date reservationStartDate;
    Date reservationEndDate;
    ScreeningRoomReservationStatus status;

    public ScreeningRoomReservation(Date reservationStartDate, Date reservationEndDate, ScreeningRoomReservationStatus status) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.status = status;
    }
}
