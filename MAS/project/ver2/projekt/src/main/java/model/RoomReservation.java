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

/**
 * The class represents room reservation in the system.
 */
public class RoomReservation extends ObjectPlusPlus {

    private LocalDateTime start;
    private LocalDateTime end;

    private Room reservedRoom;
    private Event event;

    private List<Seat> alreadyTakenSeats = new ArrayList<>();

    /**
     * The constructor
     * @param start
     * @param end
     * @param reservedRoom
     * @param event
     * @throws ValidateDataException thrown if passed objects doesn't pass validation checks
     */
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

    /**
     * Function checks if passed dates doesn't overlap with other reservations of particular room
     * @param start
     * @param end
     * @param room
     * @return Returns true if dates overlap with other reservation, if it doesn't returns false
     */
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

    /**
     * Function returns string representation of room associated with this reservation.
     * @return string representation of room associated with this reservation
     * @throws Exception
     */
    public String getEventString() throws Exception {
        return ((Event) this.getLinks("heldEvent")[0]).getEventName();
    }

    /**
     * Function returns room associated with this reservation
     * @return room associated with this reservation
     */
    public Room getRoom() {
        try {
            return (Room) this.getLinks("reservedRoom")[0];
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Function returns event associated with this reservation
     * @return event associated with this reservation
     */
    public Event getEvent() {
        try {
            return (Event) this.getLinks("heldEvent")[0];
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Function returns start date of the reservation
     * @return start date of the reservation
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Function returns end date of the reservation
     * @return end date of the reservation
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Function returns string representation of formatted start date of the reservation.
     * @return string representation of formatted start date of the reservation
     */
    public String getStartString() {
        return this.start.format(DateTimeFormatter.ofPattern("dd/MM/YY HH:mm"));
    }

    /**
     * Function returns string representation of formatted end date of the reservation.
     * @return string representation of formatted end date of the reservation
     */
    public String getEndString() {
        return this.end.format(DateTimeFormatter.ofPattern("dd/MM/YY HH:mm"));
    }

    /**
     * Adds seat to the list of already taken seats.
     * @param seat
     */
    public void reserveSeat(Seat seat) {
        if(!alreadyTakenSeats.contains(seat)) {
            alreadyTakenSeats.add(seat);
        }
    }

    /**
     * Function returns list of reservations which start date equals current date plus 7 days
     * @return list of reservations
     */
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

    /**
     * Function returns list of reservations for given room, which start date equals current date plus 7 days
     * @param room
     * @return list of reservations for given room
     */
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
