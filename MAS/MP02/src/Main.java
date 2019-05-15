import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) throws Exception {

        // Zwykła
        Employee emp = new Employee("Jan", "Kowalski", "11111111111");
        Employee emp1 = new Employee("Krzysztof", "Kowalski", "11111111112");

        EventTicket ticket1 = new EventTicket("ulgowy", 20d);
        EventTicket ticket2 = new EventTicket("normalny", 40d);

        emp.sellEventTicket(ticket1);
        emp1.sellEventTicket(ticket2);

        emp.printSoldTickets();
        emp1.printSoldTickets();

        System.out.println();

        // Z atrybutem
        ScreeningRoom room1 = new ScreeningRoom(1, 50, ScreeningRoom.ScreeningRoomType.TWO_D);
        SocialEvent event1 = new SocialEvent("event1", "organizer", new URL("http://event1.com"));
        SocialEvent event2 = new SocialEvent("event2", "organizer", new URL("http://event2.com"));

        event1.reserveScreeningRoom(
                room1,
                LocalDateTime.of(2019, 05, 15, 12, 30),
                LocalDateTime.of(2019, 05, 15, 14, 30)
        );

        event2.reserveScreeningRoom(
                room1,
                LocalDateTime.of(2019, 05, 16, 12, 30),
                LocalDateTime.of(2019, 05, 16, 14, 30)
        );

        room1.printAllReservations();


    }

}
