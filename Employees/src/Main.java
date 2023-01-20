import com.nikola.company.Company;
import com.nikola.company.Department;
import com.nikola.company.employees.Employee;
import com.nikola.company.employees.FullTimeEmployee;
import com.nikola.company.employees.Manager;
import com.nikola.company.employees.PartTimeEmployee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();

        Manager salesManager = new Manager("Liisa Warman", "liisa.warman@example.com", 10000);
        List<Employee> salesEmployees = List.of(
                new FullTimeEmployee("Channy Escobar", "channy.escobar@example.com", 5678),
                new PartTimeEmployee("Candise Jayne", "candise.jayne@example.com", 7890),
                new PartTimeEmployee("Keionna Strong", "keionna.strong@example.com", 3455)
        );
        Department salesDepartment = new Department("Sales", salesManager, salesEmployees);
        departments.add(salesDepartment);

        Manager itManager = new Manager("Quentisha Dittrich", "quentisha.dittrich@example.com", 15000);
        List<Employee> itEmployees = List.of(
                new FullTimeEmployee("Giulia Marino", "giulia.marino@example.com", 9999),
                new PartTimeEmployee("Ronrico Veith", "ronrico.veith@example.com", 8990),
                new PartTimeEmployee("Lucius Stott", "lucius.stott@example.com", 2134)
        );
        Department itDepartment = new Department("IT", itManager, itEmployees);
        departments.add(itDepartment);

        Company company = new Company("ECorp");
        company.setDepartments(departments);

        System.out.printf("Sales department manager: %s%n", company.getManagerByDepartment("Sales").getName());
        System.out.printf("Sales department total vacation days: %d%n", salesDepartment.getTotalVacationDays());
        System.out.printf("Sales department taxes: %f%n", salesDepartment.getTotalTaxes());

        System.out.printf("IT department manager: %s%n", company.getManagerByDepartment("IT").getName());
        System.out.printf("IT department total vacation days: %d%n", salesDepartment.getTotalVacationDays());
        System.out.printf("IT department taxes: %f%n", itDepartment.getTotalTaxes());

        System.out.printf("Total taxes for %s: %f%n", company.getName(), company.getTotalTaxes());
    }
}