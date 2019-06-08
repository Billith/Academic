package model;

import model.oplusplus.ObjectPlusPlus;

public class Seat extends ObjectPlusPlus {

    private int seatNumber;
    private int rowNumber;
    private Room room;

    private Seat(int seatNumber, int rowNumber, Room room) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.room = room;
    }

    public static Seat seatFabric(int seatNumber, int rowNumber, Room room) throws Exception {
        if(room == null) {
            throw new Exception("[!] Given room doesn't exists");
        }
        Seat newSeat = new Seat(seatNumber, rowNumber, room);
        room.addSeat(newSeat);
        return newSeat;
    }

}