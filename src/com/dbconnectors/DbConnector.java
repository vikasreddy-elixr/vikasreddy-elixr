package com.dbconnectors;

import java.sql.*;

public class DbConnector {
    final static String query = "INSERT INTO audit VALUES(?,?,now(),?,?,?)";

    final private static String url = "jdbc:mysql://localhost:3306/elixr_intern_poc_01";
    final private static String user = "vikas";
    final private static String password = "root";
    private static PreparedStatement statement;
    static Connection connection;

    public void databaseConnector(String txtFilePath, String searchWord, int count, String status, String errorMessage) throws SQLException {

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.prepareStatement(query);
            statement.setString(1, txtFilePath);
            statement.setString(2, searchWord);
            statement.setString(3, status);
            statement.setInt(4, count);
            statement.setString(5, errorMessage);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}

