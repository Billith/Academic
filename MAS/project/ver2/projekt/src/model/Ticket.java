package model;

public abstract class Ticket {

    private static int ticketCounter = 0;

    protected int ticketId;
    // protected BigDecimal price;
    // Asocjacja z siedzeniem w sali kinowej, na konkretnym seansie

    public Ticket() {
        this.ticketId = ++ticketCounter;
        // this.price = price; // Cena powinna być brana z ascojacji z filmem/siedzeniem w kinie
    }

}
