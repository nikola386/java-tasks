package com.nikola.sqltask.risebyinflation;

import java.sql.*;

public class EmployeesManager {
    public static void main(String[] args) {
        String queryString = "{call dbo.RiseSalariesByInflation(?, ?, ?)}";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall(queryString)) {
            statement.setFloat(1, 0.01f);

            statement.registerOutParameter(2, Types.INTEGER);
            statement.registerOutParameter(3, Types.VARCHAR);

            statement.executeUpdate();

            int updatedRecords = statement.getInt(2);
            String error = statement.getString(3);
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