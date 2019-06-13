package controller;

import javafx.scene.control.*;
import model.Room;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class is responsible for validation of users input from window that creates new RoomReservation object
 */
public class ReservationValidator {

    /**
     * Function validates user input
     * @param group projection type radio buttons
     * @param start start date of new reservation
     * @param startTime start time of new reservation
     * @param end end date of new reservation
     * @param endTime end time of new reservation
     * @param availableRooms list of all currently available rooms
     * @param ticketPrice movie projection base ticket price
     * @throws ValidateDataException thrown when users input is illegal
     */
    public static void validateInput(ToggleGroup group, DatePicker start, TextField startTime,
                                     DatePicker end, TextField endTime, ComboBox<Room> availableRooms, TextField ticketPrice) throws ValidateDataException {
        try {
            validateProjectionFormat(group);
            validateDate(start, startTime, end, endTime);
            validateRoom(availableRooms);
            validatePrice(ticketPrice);
        } catch (ValidateDataException e) {
            throw e;
        }
    }

    /**
     * Function validates ticket price
     * @param ticketPrice base ticket price
     * @throws ValidateDataException thrown when users input is illegal
     */
    private static void validatePrice(TextField ticketPrice) throws ValidateDataException {
        Pattern p = Pattern.compile("^\\d+\\.\\d+$");
        Matcher mPrice = p.matcher(ticketPrice.getText().trim());

        if(!mPrice.matches()) {
            throw new ValidateDataException("Zły format bazowej ceny biletu.");
        }
    }

    /**
     * Function validates list of available rooms
     * @param availableRooms
     * @throws ValidateDataException thrown when users input is illegal
     */
    private static void validateRoom(ComboBox<Room> availableRooms) throws ValidateDataException {
        Room selectedRoom = availableRooms.getSelectionModel().getSelectedItem();
        if(selectedRoom == null) {
            throw new ValidateDataException("Nie wybrano sali");
        }
    }

    /**
     * Function validates dates of start and end of the reservation
     * @param start start date of new reservation
     * @param startTime start time of new reservation
     * @param end end date of new reservation
     * @param endTime end time of new reservation
     * @throws ValidateDataException thrown when users input is illegal
     */
    private static void validateDate(DatePicker start, TextField startTime, DatePicker end, TextField endTime) throws ValidateDataException {
        String reservationStart = startTime.getText().trim();
        String reservationEnd = endTime.getText().trim();
        validateDateFormat(reservationStart, reservationEnd);
        validateDateLogic(start, reservationStart, end, reservationEnd);
    }

    /**
     * Function validates dates of start and end of the reservation in terms of logic.
     * @param start start date of new reservation
     * @param end end date of new reservation
     * @throws ValidateDataException thrown when users input is illegal
     */
    private static void validateDateLogic(DatePicker start, String reservationStart, DatePicker end, String reservationEnd) throws ValidateDataException {
        try {
            if (start.getValue().isAfter(end.getValue())) {
                throw new ValidateDataException("Data rozpoczęcia nie może być po dacie zakończenia");
            }
        } catch (NullPointerException e) {
            throw new ValidateDataException("Nie wprowadzono daty rozpoczęcia lub zakończenia");
        }

        if(start.getValue().isEqual(end.getValue())) {
            String[] reservationStartTime = reservationStart.split(":");
            String[] reservationEndTime = reservationEnd.split(":");

            int reservationStartHour = Integer.parseInt(reservationStartTime[0]);
            int reservationStartMinute = Integer.parseInt(reservationStartTime[1]);
            int reservationEndHour = Integer.parseInt(reservationEndTime[0]);
            int reservationEndMinute = Integer.parseInt(reservationEndTime[1]);

            if(reservationStartHour > 23 || reservationStartHour < 0 || reservationEndHour > 23 || reservationEndHour < 0) {
                throw new ValidateDataException("Wprowadzono godzinę z poza zakresu. Godzina musi się zawierać między 0-23");
            }

            if(reservationStartMinute > 59 || reservationStartMinute < 0 || reservationEndMinute > 59 || reservationEndMinute < 0) {
                throw new ValidateDataException("Wprowadzono minuty z poza zakresu. Minuty muszą się zawierać między 0-59");
            }

            if(reservationStartHour > reservationEndHour) {
                throw new ValidateDataException("Wprowadzono wcześniejszą godzinę zakończenia niż rozpoczęcia rezerwacji.");
            }

            if(reservationEndHour == reservationStartHour) {
                if(reservationStartMinute >= reservationEndMinute) {
                    throw new ValidateDataException("Wprowadzono wcześniejszą lub równą minutę zakończenia i rozpoczęcia rezerwacji.");
                }
            }
        }
    }

    /**
     * Function validates dates of start and end of the reservation in terms of proper format
     * @param reservationStart start time provided by user
     * @param reservationEnd start time provided by user
     * @throws ValidateDataException thrown when users input is illegal
     */
    private static void validateDateFormat(String reservationStart, String reservationEnd) throws ValidateDataException {
        Pattern p = Pattern.compile("^\\d\\d:\\d\\d$");
        Matcher mStart = p.matcher(reservationStart);
        Matcher mEnd = p.matcher(reservationEnd);

        if(!mStart.matches() || !mEnd.matches()) {
            throw new ValidateDataException("Zły format godziny rozpoczęcia lub zakończenia. Proszę wprowadzić godzinę zgdonie z formatem hh:mm.");
        }
    }

    /**
     * Function checks if one of projection format radio buttons is selected
     * @param group group of projection type radio buttons
     * @throws ValidateDataException thrown when none of radio buttons is selected.
     */
    private static void validateProjectionFormat(ToggleGroup group) throws ValidateDataException {
        if(group.getSelectedToggle() == null) {
            throw new ValidateDataException("Nie zaznaczono formatu projekcji!");
        }
    }

}
