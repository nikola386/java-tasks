package com.nikola.company;

import com.nikola.company.employees.Manager;

import java.util.List;

public class Company {
    private final String name;
    private List<Department> departments;
    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Manager getManagerByDepartment(String departmentName) {
        Department department = departments.stream().filter(d -> departmentName.equals(d.getName()))
                .findAny()
                .orElse(null);

        if(department == null) return null;
        return department.getManager();
    }

    public double getTotalTaxes() {
        return departments.stream().reduce(0.0, (sub, d) -> sub + d.getTotalTaxes(), Double::sum);
    }
}
