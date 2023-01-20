package com.nikola.company.employees;

public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, String email, int salary) {
        this.setName(name);
        this.setEmail(email);
        this.setSalary(salary);
        this.setVacation(20);
        this.setTax(0.1);
    }
}
