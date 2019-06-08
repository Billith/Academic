package model;

import model.oplusplus.ObjectPlusPlus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomReservation extends ObjectPlusPlus {

    private LocalDateTime start;
    private LocalDateTime end;

    private Room reservedRoom;
    private Event event;

    private List<Seat> alreadyTakenSeats = new ArrayList<>();

    public RoomReservation(LocalDateTime start, LocalDateTime end, Room reservedRoom, Event event) {
        this.start = start;
        this.end = end;
        this.reservedRoom = reservedRoom;
        this.event = event;

        this.reservedRoom.addLink("reservationRoom", "reservedRoom", this);
        this.event.addLink("reservationEvent", "heldEvent", this);
    }

    public void reserveSeats(Seat seat) {
        if(!alreadyTakenSeats.contains(seat)) {
            alreadyTakenSeats.add(seat);
        }
    }

}
