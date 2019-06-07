package model;

import model.oplusplus.ObjectPlusPlus;

import java.time.LocalDateTime;

public class RoomReservation extends ObjectPlusPlus {

    private LocalDateTime start;
    private LocalDateTime end;

    private Room reservedRoom;
    private Event event;

    public RoomReservation(LocalDateTime start, LocalDateTime end, Room reservedRoom, Event event) {
        this.start = start;
        this.end = end;
        this.reservedRoom = reservedRoom;
        this.event = event;

        this.reservedRoom.addLink("reservationRoom", "reservedRoom", this);
        this.event.addLink("reservationEvent", "heldEvent", this);
    }

}
