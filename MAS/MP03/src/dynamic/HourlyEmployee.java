package dynamic;

import o_plus_plus.ObjectPlus;

public class HourlyEmployee extends Employee {

    private double hourlyWage;
    private String projectName;
    private int workedHours;

    public HourlyEmployee(String firstName, String lastName, String pesel, double hourlyWage, String projectName) {
        super(firstName, lastName, pesel);
        this.hourlyWage = hourlyWage;
        this.projectName = projectName;
    }

    public HourlyEmployee(Employee emp, double hourlyWage) {
        super(emp.firstName, emp.lastName, emp.pesel);
        this.hourlyWage = hourlyWage;

        emp.replaceObjectWith(this);
        ObjectPlus.removeObjectFromExtent(emp);
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
}
