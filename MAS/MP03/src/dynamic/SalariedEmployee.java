package dynamic;

import o_plus_plus.ObjectPlus;

public class SalariedEmployee extends Employee {

    private double monthlySalary;

    public SalariedEmployee(String firstName, String lastName, String pesel, double monthlySalary) {
        super(firstName, lastName, pesel);
        this.monthlySalary = monthlySalary;
    }

    public SalariedEmployee(Employee emp, double monthlySalary) {
        super(emp.firstName, emp.lastName, emp.pesel);
        this.monthlySalary = monthlySalary;
        emp.replaceObjectWith(this);
        ObjectPlus.removeObjectFromExtent(emp);
    }

}
