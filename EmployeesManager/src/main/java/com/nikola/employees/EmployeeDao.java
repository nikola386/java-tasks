package com.nikola.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements Dao<Employee> {
    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void insert(Employee e) {
        String queryString = "INSERT INTO [dbo].[Employee] ([FirstName], [LastName], [BirthDate], [Department], [Salary]) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setString(1, e.getFirstName());
            statement.setString(2, e.getLastName());
            statement.setDate(3, e.getBirthDate());
            statement.setString(4, e.getDepartment());
            statement.setDouble(5, e.getSalary());

            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void update(Employee e) {
        String queryString = """
                UPDATE [dbo].[Employee]
                 SET [FirstName]=?, [LastName]=?, [BirthDate]=?, [Department]=?, [Salary]=?
                 WHERE ID=?""";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setString(1, e.getFirstName());
            statement.setString(2, e.getLastName());
            statement.setDate(3, e.getBirthDate());
            statement.setString(4, e.getDepartment());
            statement.setDouble(5, e.getSalary());
            statement.setDouble(6, e.getId());

            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String queryString = "SELECT * FROM [dbo].[Employee] WITH (NOLOCK) ";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("ID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getDate("BirthDate"),
                        resultSet.getString("Department"),
                        resultSet.getDouble("Salary")
                ));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee getById(int id) {
        String queryString = "SELECT * FROM [dbo].[Employee] WITH (NOLOCK) WHERE ID=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("ID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getDate("BirthDate"),
                        resultSet.getString("Department"),
                        resultSet.getDouble("Salary")
                );
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {
        String queryString = "DELETE FROM [dbo].[Employee] WHERE ID=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
