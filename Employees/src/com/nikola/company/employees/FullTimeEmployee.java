package com.nikola.company.employees;

public class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name, String email, int salary) {
        this.setName(name);
        this.setEmail(email);
        this.setSalary(salary);
        this.setVacation(30);
        this.setTax(0.2);
    }
}
