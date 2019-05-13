package model;

import java.time.LocalDate;
import java.util.List;

public class EventTicketReservation extends ObjectPlus {
    EventTicketReversationStatus status;
    LocalDate dateOfReservation;

    public EventTicketReservation() {
        this.status = EventTicketReversationStatus.NOT_PAID;
        this.dateOfReservation = LocalDate.now();
    }

    public EventTicketReservation(EventTicketReversationStatus status) {
        this.status = status;
        this.dateOfReservation = LocalDate.now();
    }
    
    public static void removeUnpaidTicketReservations() {
        List<ObjectPlus> extent = ObjectPlus.getClassExtent(EventTicketReservation.class);
        for (ObjectPlus ticketRes : extent) {
            EventTicketReservation ticketReservation = (EventTicketReservation) ticketRes;
            if( ticketReservation.status == EventTicketReversationStatus.NOT_PAID) {
                ObjectPlus.removeObjectFromExtent(ticketReservation);
            }
        }
    }

}
