package model;

import controller.ValidateDataException;
import javafx.scene.control.Alert;
import model.oplusplus.ObjectPlus;
import model.oplusplus.ObjectPlusPlus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RoomReservation extends ObjectPlusPlus {

    private LocalDateTime start;
    private LocalDateTime end;

    private Room reservedRoom;
    private Event event;

    private List<Seat> alreadyTakenSeats = new ArrayList<>();

    public RoomReservation(LocalDateTime start, LocalDateTime end, Room reservedRoom, Event event) throws ValidateDataException {

        if(checkForOverlappingReservations(start, end, reservedRoom)) {
            throw new ValidateDataException("Wprowadzona rezerwacja na daną salę nakłada się na inną. Proszę wybrać inną datę");
        }

        this.start = start;
        this.end = end;
        this.reservedRoom = reservedRoom;
        this.event = event;

        this.reservedRoom.addLink("reservationRoom", "reservedRoom", this);
        this.event.addLink("reservationEvent", "heldEvent", this);
    }

    private boolean checkForOverlappingReservations(LocalDateTime start, LocalDateTime end, Room room) {
        List<ObjectPlus> allReservations = ObjectPlus.getClassExtent(this.getClass());

        for(ObjectPlus obj : allReservations) {
            RoomReservation reservation = (RoomReservation) obj;
            if(reservation.getRoom() == room) {
                LocalDateTime startDate = start;
                LocalDateTime endDate = end;
                LocalDateTime reservationStartDate = reservation.getStart();
                LocalDateTime reservationEndDate = reservation.getEnd();
                if(startDate.isAfter(reservationStartDate) && startDate.isBefore(reservationEndDate) ||
                        endDate.isAfter(reservationStartDate) && endDate.isBefore(reservationEndDate))
                    return true;
            }
        }
        return false;

    }

    public String getEventString() throws Exception {
        return ((Event) this.getLinks("heldEvent")[0]).getEventName();
    }

    public Room getRoom() {
        try {
            return (Room) this.getLinks("reservedRoom")[0];
        } catch (Exception e) {
            return null;
        }
    }

    public Event getEvent() {
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

    public String getStartString() {
        return this.start.format(DateTimeFormatter.ofPattern("dd/MM/YY HH:mm"));
    }

    public String getEndString() {
        return this.end.format(DateTimeFormatter.ofPattern("dd/MM/YY HH:mm"));
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

    public static List<RoomReservation> getReservationsForNextWeek(Room room) {
        List<RoomReservation> matchingReservations = new ArrayList<>();
        List<ObjectPlus> allReservations = ObjectPlus.getClassExtent(RoomReservation.class);
        LocalDateTime nextWeek = LocalDateTime.now().plusDays(7);

        for(ObjectPlus obj : allReservations) {
            RoomReservation currentReservation = (RoomReservation) obj;
            if(currentReservation.start.isBefore(nextWeek) && currentReservation.getRoom() == room) {
                matchingReservations.add(currentReservation);
            }
        }

        return matchingReservations;
    }

}
