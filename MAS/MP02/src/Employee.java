public class Employee extends ObjectPlusPlus {

    String firstName;
    String lastName;
    String pesel;

    public Employee(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public void sellEventTicket(EventTicket ticket) throws Exception {
        //addLink("sprzedaje", "sprzedany", ticket);
        addOneToManyLink("sprzedaje", "sprzedany", ticket);
    }

    public String toString() {
        return String.format("[ %s, firstname=%s, lastname=%s, pesel=%s", Employee.class.toString(), firstName, lastName, pesel);
    }

}