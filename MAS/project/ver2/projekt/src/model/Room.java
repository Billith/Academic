package model;

import model.oplusplus.ObjectPlusPlus;

import java.util.ArrayList;
import java.util.List;

public class Room extends ObjectPlusPlus {

    private int roomId;
    private int capacity;
    private boolean isAvailable;
    private RoomType roomType;

    private List<Seat> seats = new ArrayList<>();
    private List<Seat> allSeats = new ArrayList<>();

    public Room(int roomId, int capacity, boolean isAvailable, RoomType roomType) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }

    public void addSeat(Seat seat) throws Exception {
        if(!seats.contains(seat)) {
            if(allSeats.contains(seat)) {
                throw new Exception("!] This seat is already assigned to another room!");
            }
            seats.add(seat);
            allSeats.add(seat);
        }
    }

}
