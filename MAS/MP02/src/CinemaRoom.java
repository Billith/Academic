import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CinemaRoom extends ObjectPlus {

    int screeningRoomPhysicalId;
    int maximumCapacity;
    ScreeningRoomType screeningRoomType;

    List<CinemaRoomReservation> allCinemaRoomReservations = new ArrayList<>();

    List<CinemaRoomSeat> seats = new ArrayList<>();
    static Set<CinemaRoomSeat> allSeats = new HashSet<>();

    public CinemaRoom(int screeningRoomPhysicalId, int maximumCapacity, ScreeningRoomType screeningRoomType) {
        this.screeningRoomPhysicalId = screeningRoomPhysicalId;
        this.maximumCapacity = maximumCapacity;
        this.screeningRoomType = screeningRoomType;
    }

    public void printAllReservations() {
        for(CinemaRoomReservation reservation : allCinemaRoomReservations) {
            System.out.println(reservation);
        }
        System.out.println("------------------------------------");
    }

    public void addSeat(CinemaRoomSeat newSeat) throws Exception {
        if(!seats.contains(newSeat)) {
            if(allSeats.contains(newSeat)) {
                throw new Exception("[!] This seat is already assigned to another room!");
            }
            seats.add(newSeat);
            allSeats.add(newSeat);
        }
    }

    public void printAllSeats() {
        for(CinemaRoomSeat seat : seats) {
            System.out.println(seat);
        }
        System.out.println("------------------------------------");
    }

    public String toString() {
        return String.format("[ %s, capacity=%s, type=%s, reservations=%s, seats=%s]",
                CinemaRoom.class.toString(),
                maximumCapacity,
                screeningRoomType,
                allCinemaRoomReservations.size(), seats.size()
        );
    }

    public static void removeRoomFromExtent(CinemaRoom room) {
        List<CinemaRoomSeat> roomSeats = room.seats;
        CinemaRoom.allSeats.removeAll(roomSeats);
        for(CinemaRoomSeat seat : roomSeats) {
            ObjectPlus.removeObjectFromExtent(seat);
        }
        ObjectPlus.removeObjectFromExtent(room);
    }

    public enum ScreeningRoomType {
        TWO_D, THREE_D, FOUR_D
    }

}