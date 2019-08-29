package model;

import model.oplusplus.ObjectPlus;
import model.oplusplus.ObjectPlusPlus;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents cinema room in the system.
 */
public class Room extends ObjectPlusPlus {

    private int roomId;
    private int capacity;
    private boolean isAvailable;
    private RoomType roomType;

    private List<Seat> seatsAssignedToRoom = new ArrayList<>();
    private static List<Seat> allSeats = new ArrayList<>();

    /**
     * The constructor
     * @param roomId unique id of the room assigned by cinema staff
     * @param capacity
     * @param isAvailable
     * @param roomType
     */
    public Room(int roomId, int capacity, boolean isAvailable, RoomType roomType) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }

    /**
     * Function add seat to the list of seats in the room. Function checks if given seat is not associated with another
     * room
     * @param seat
     * @throws Exception thrown if seat is associated with another room
     */
    public void addSeat(Seat seat) throws Exception {
        if(!seatsAssignedToRoom.contains(seat)) {
            if(allSeats.contains(seat)) {
                throw new Exception("[!] This seat is already assigned to another room!");
            }
            seatsAssignedToRoom.add(seat);
            allSeats.add(seat);
        }
    }

    /**
     * Function removes seat from the list of seat in this room
     * @param seat
     * @throws Exception thrown when seat which is being deleted isn't associated with this room
     */
    public void removeSeat(Seat seat) throws Exception {
        if(!seatsAssignedToRoom.contains(seat)) {
            throw new Exception("[!] This seat isn't assigned to this room");
        }
        seatsAssignedToRoom.remove(seat);
    }

    /**
     * Function returns all rooms that are certain room type, defined in RoomType enum.
     * @param requiredRoom
     * @return list of rooms of given type
     */
    public static List<Room> getRoomList(RoomType requiredRoom) {
        List<Room> list = new ArrayList<>();
        List<ObjectPlus> allRooms = ObjectPlus.getClassExtent(Room.class);
        if(allRooms != null) {
            for (ObjectPlus obj : allRooms) {
                Room currentRoom = (Room) obj;
                if (currentRoom.roomType.toString().equals(requiredRoom.toString())) {
                    list.add(currentRoom);
                }
            }
        }
        return list;
    }

    public String toString() {
        return String.format("Sala nr %s", roomId);
    }

}
