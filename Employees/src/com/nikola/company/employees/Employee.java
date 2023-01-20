package com.nikola.company.employees;

abstract public class Employee {
    private String name;
    private String email;
    private int vacation;
    private double salary;
    private double tax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double calculateTax() {
        return salary * tax;
    }
}
