package model;

import model.oplusplus.ObjectPlusPlus;

/**
 * The class represents seat in the cinema room in the system
 */
public class Seat extends ObjectPlusPlus {

    private int seatNumber;
    private int rowNumber;
    private Room room;

    /**
     * The constructor
     * @param seatNumber
     * @param rowNumber
     * @param room
     */
    private Seat(int seatNumber, int rowNumber, Room room) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.room = room;
    }

    /**
     * Fabric function for creating seats and assigning them to given room
     * @param seatNumber
     * @param rowNumber
     * @param room
     * @return
     * @throws Exception thrown if given room doesn't exists
     */
    public static Seat seatFabric(int seatNumber, int rowNumber, Room room) throws Exception {
        if(room == null) {
            throw new Exception("[!] Given room doesn't exists");
        }
        Seat newSeat = new Seat(seatNumber, rowNumber, room);
        room.addSeat(newSeat);
        return newSeat;
    }

}
