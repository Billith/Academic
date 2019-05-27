package controller;

import model.Employee;
import model.oplusplus.ObjectPlus;

public class Main {
    public static void main(String[] args) {
        new Employee("Łukasz", "Dyduch", "lukas.dyduch@cienema.com", 1, "1111111111", "777111222");
        System.out.println(ObjectPlus.getClassExtent(Employee.class));
    }
}
