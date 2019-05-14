public class Main {

    public static void main(String[] args) throws Exception {

        // Zwykła
        Employee emp = new Employee("Jan", "Kowalski", "11111111111");
        Employee emp1 = new Employee("Krzysztof", "Kowalski", "11111111112");

        EventTicket ticket1 = new EventTicket("ulgowy", 20d);
        EventTicket ticket2 = new EventTicket("normalny", 40d);

        emp.sellEventTicket(ticket1);
        emp1.sellEventTicket(ticket1);
        emp.sellEventTicket(ticket2);

        emp.showLinks("sprzedaje", System.out);
        emp1.showLinks("sprzedaje", System.out);
        ticket1.showLinks("sprzedany", System.out);
        //ticket2.showLinks("sprzedany", System.out);

        // Z atrybutem


    }

}
