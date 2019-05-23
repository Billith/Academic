package dynamic;

public class HourlyEmployee extends Employee {

    private double hourlyWage;

    public HourlyEmployee(String firstName, String lastName, String pesel, double hourlyWage) {
        super(firstName, lastName, pesel);
        this.hourlyWage = hourlyWage;
    }

    public HourlyEmployee(Employee emp, double hourlyWage) {
        super(emp.firstName, emp.lastName, emp.pesel);
        this.hourlyWage = hourlyWage;
    }

}
