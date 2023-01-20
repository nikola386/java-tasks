package com.nikola.company;

import com.nikola.company.employees.Employee;
import com.nikola.company.employees.Manager;

import java.util.List;

public class Department {
    private final String name;
    private final Manager manager;
    private final List<Employee> employees;

    public Department(String name, Manager manager, List<Employee> employees) {
        this.name = name;
        this.manager = manager;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public double getTotalTaxes() {
        return employees.stream().reduce(0.0, (sub, e) -> sub + e.calculateTax(), Double::sum) + manager.calculateTax();
    }

    public int getTotalVacationDays() {
        return employees.stream().reduce(0, (sub, e) -> sub + e.getVacation(), Integer::sum) + manager.getVacation();
    }
}
