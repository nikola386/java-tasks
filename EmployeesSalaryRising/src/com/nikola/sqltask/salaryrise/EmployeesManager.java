package com.nikola.sqltask.salaryrise;

import java.sql.*;

public class EmployeesManager {
    public static void main(String[] args) {
        String queryString = "{call dbo.RiseSalaries(?, ? ,?, ? )}";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall(queryString)) {
            statement.setString(1, "sales");
            statement.setFloat(2, 100f);

            statement.registerOutParameter(3, Types.INTEGER);
            statement.registerOutParameter(4, Types.VARCHAR);

            statement.executeUpdate();

            int updatedRecords = statement.getInt(3);
            String error = statement.getString(4);
            System.out.println("Updated records: " + updatedRecords);
            System.out.println("Error: " + error);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }
}