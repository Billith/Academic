import java.net.URL;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception {

        // Zwykła (1 do *)
        // Poprawione - private List<EventTicket> soldEventTickets w Employee
        // obsłuzenie połączenia zwrotnego
        Employee emp = new Employee("Jan", "Kowalski", "11111111111");

        EventTicket ticket1 = new EventTicket("ulgowy", 20d);

        emp.addEventTicket(ticket1);

        System.out.println("emp: ");emp.printSoldTickets();System.out.println();

        // Z atrybutem (* do *)
        // W środku konstruktora musimy wszystko zrobić apropos połączeń
        // Zaliczone
        CinemaRoom room1 = new CinemaRoom(1, 50, CinemaRoom.ScreeningRoomType.TWO_D);
        CinemaRoom room2 = new CinemaRoom(2, 75, CinemaRoom.ScreeningRoomType.TWO_D);
        SocialEvent event = new SocialEvent("event1", "organizer", new URL("http://event1.com"));

        event.reserveCinemaRoom(
                room1,
                LocalDateTime.of(2019, 05, 15, 12, 30),
                LocalDateTime.of(2019, 05, 15, 14, 30)
        );
        event.reserveCinemaRoom(
                room2,
                LocalDateTime.of(2019, 06, 15, 12, 30),
                LocalDateTime.of(2019, 06, 15, 14, 30)
        );

        System.out.println("room1: ");room1.printAllReservations();
        System.out.println("room2: ");room2.printAllReservations();
        System.out.println("event: ");event.printAllReservations();

        System.out.println();

        // Kwalifikowana (1 do *)
        // obsłuzenie połączenia zwrotnego
        EventTicket ticket3 = new EventTicket("ulgowy", 22d);
        EventTicket ticket4 = new EventTicket("normalny", 38d);
        EventTicket ticket5 = new EventTicket("normalny", 38d);

        MovieProjection movie1 = new MovieProjection("Title1", "Director1", "PL", 90);

        movie1.addEventTicket(ticket3);
        movie1.addEventTicket(ticket4);
        movie1.addEventTicket(ticket5);

        EventTicket ticketFound = movie1.findEventTicket(3);
        System.out.println("ticketFound:\n" + ticketFound); System.out.println("------------------------------------\n");

        // Kompozycja (1 do *)
        // Zaliczone
        CinemaRoom room3 = new CinemaRoom(3, 100, CinemaRoom.ScreeningRoomType.TWO_D);

        CinemaRoomSeat.CinemaRoomSeatFabric(room3, 1);
        CinemaRoomSeat.CinemaRoomSeatFabric(room3, 2);
        CinemaRoomSeat.CinemaRoomSeatFabric(room3, 3);

        System.out.println("room3:");room3.printAllSeats();

        System.out.println("extent:");ObjectPlus.printAllExtents();System.out.println("------------------------------------");
        CinemaRoom.removeRoomFromExtent(room3);
        System.out.println("extent:");ObjectPlus.printAllExtents();System.out.println("------------------------------------");

    }

}
