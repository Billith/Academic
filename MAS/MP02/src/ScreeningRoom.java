import java.util.ArrayList;
import java.util.List;

public class ScreeningRoom {

    int screeningRoomPhysicalId;
    int maximumCapacity;
    ScreeningRoomType screeningRoomType;

    List<ScreeningRoomReservation> allScreeningRoomReservations = new ArrayList<>();

    public ScreeningRoom(int screeningRoomPhysicalId, int maximumCapacity, ScreeningRoomType screeningRoomType) {
        this.screeningRoomPhysicalId = screeningRoomPhysicalId;
        this.maximumCapacity = maximumCapacity;
        this.screeningRoomType = screeningRoomType;
    }

    public void printAllReservations() {
        for(ScreeningRoomReservation reservation : allScreeningRoomReservations) {
            System.out.println(reservation);
        }
    }

    public enum ScreeningRoomType {
        TWO_D, THREE_D, FOUR_D
    }

}