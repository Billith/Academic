package model;

import model.oplusplus.ObjectPlus;
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

    public String getEvent() throws Exception {
        return ((Event) this.getLinks("heldEvent")[0]).getEventName();
    }

    public Room getRoom() {
        try {
            return (Room) this.getLinks("reservedRoom")[0];
        } catch (Exception e) {
            return null;
        }
    }

    public Event getEventObject() {
        try {
            return (Event) this.getLinks("heldEvent")[0];
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void reserveSeat(Seat seat) {
        if(!alreadyTakenSeats.contains(seat)) {
            alreadyTakenSeats.add(seat);
        }
    }

    public static List<RoomReservation> getReservationsForNextWeek() {
        List<RoomReservation> matchingReservations = new ArrayList<>();
        List<ObjectPlus> allReservations = ObjectPlus.getClassExtent(RoomReservation.class);
        LocalDateTime nextWeek = LocalDateTime.now().plusDays(7);

        for(ObjectPlus obj : allReservations) {
            RoomReservation currentReservation = (RoomReservation) obj;
            if(currentReservation.start.isBefore(nextWeek)) {
                matchingReservations.add(currentReservation);
            }
        }

        return matchingReservations;
    }

}
