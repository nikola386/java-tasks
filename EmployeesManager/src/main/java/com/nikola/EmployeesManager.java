package com.nikola;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.nikola.employees.Employee;
import com.nikola.employees.EmployeeDao;

import java.sql.Date;
import java.util.List;

public class EmployeesManager {
    public static void main(String[] args) {
        Faker faker = new Faker();
        EmployeeDao employeeDao = new EmployeeDao();

        for(int i = 0; i < 10; i++) {
            Name name = faker.name();
            java.util.Date birthDate = faker.date().birthday(18, 60);
            employeeDao.insert(new Employee(name.firstName(), name.lastName(), new Date(birthDate.getYear(),
                    birthDate.getMonth(), birthDate.getDate()), faker.company().profession(),
                    faker.number().numberBetween(1000, 10000)));
        }

        List<Employee> allEmployees = employeeDao.getAll();
        allEmployees.forEach(System.out::println);


        Employee emp1 = employeeDao.getById(allEmployees.get(0).getId());
        System.out.println(emp1.toString());

        emp1.setFirstName(emp1.getFirstName() + " Updated");
        emp1.setLastName(emp1.getLastName() + " Updated");
        employeeDao.update(emp1);

//        allEmployees.forEach( e-> employeeDao.delete(e.getId()));
    }
}