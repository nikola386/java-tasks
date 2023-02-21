import com.nikola.employees.Employee;
import com.nikola.employees.EmployeeDao;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeDaoTest {
    private static EmployeeDao dao;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String DEPARTMENT = "Sales";
    private static final double SALARY = 50000.0;

    @BeforeAll
    public static void setUp() {
        dao = new EmployeeDao();
        dao.insert(new Employee(FIRST_NAME, LAST_NAME, new Date(1990, 1, 1), DEPARTMENT, SALARY));
    }

    @AfterAll
    public static void tearDown() {
        dao.delete(dao.getAll().get(0).getId());
    }

    @Test
    public void testInsert() {
        Employee e = new Employee("Jane", "Doe", new Date(1990, 1, 1), "HR", 60000.0);
        dao.insert(e);
        List<Employee> employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(2, employees.size());
        dao.delete(e.getId());
    }

    @Test
    public void testUpdate() {
        List<Employee> employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        Employee e = employees.get(0);
        e.setFirstName("Jane");
        dao.update(e);
        employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals("Jane", employees.get(0).getFirstName());
    }

    @Test
    public void testGetAll() {
        List<Employee> employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        Employee e = employees.get(0);
        assertEquals(FIRST_NAME, e.getFirstName());
        assertEquals(LAST_NAME, e.getLastName());
        assertEquals(DEPARTMENT, e.getDepartment());
        assertEquals(SALARY, e.getSalary());
    }

    @Test
    public void testGetById() {
        List<Employee> employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        Employee e1 = employees.get(0);
        Employee e2 = dao.getById(e1.getId());
        assertNotNull(e2);
        assertEquals(e1.getId(), e2.getId());
        assertEquals(e1.getFirstName(), e2.getFirstName());
        assertEquals(e1.getLastName(), e2.getLastName());
        assertEquals(e1.getDepartment(), e2.getDepartment());
        assertEquals(e1.getSalary(), e2.getSalary());
    }

    @Test
    public void testDelete() {
        List<Employee> employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(1, employees.size());
        Employee e = employees.get(0);
        dao.delete(e.getId());
        employees = dao.getAll();
        assertNotNull(employees);
        assertEquals(0, employees.size());
    }
}
