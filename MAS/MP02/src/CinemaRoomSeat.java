public class CinemaRoomSeat extends ObjectPlus {
    int seatNo;
    private CinemaRoom room;

    private CinemaRoomSeat(int seatNo, CinemaRoom room) {
        this.seatNo = seatNo;
        this.room = room;
    }

    public static CinemaRoomSeat CinemaRoomSeatFabric(CinemaRoom room, int seatNo) throws Exception {
        if(room == null) {
            throw new Exception("[!] Given cinema room doesn't exists!");
        }
        CinemaRoomSeat newSeat = new CinemaRoomSeat(seatNo, room);
        room.addSeat(newSeat);
        return newSeat;
    }

    public String toString() {
        return String.format("[ %s, seatNo=%s, room=%s ]", this.getClass().toString(), seatNo, room);
    }
}
