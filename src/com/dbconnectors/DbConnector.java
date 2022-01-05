package com.dbconnectors;

import java.sql.*;

public class DbConnector extends Thread {
    static String query = "INSERT INTO audit VALUES(?,?,now(),?,?,?)";

    private static String url = "jdbc:mysql://localhost:3306/elixr_intern_poc_01";
    private static String user = "vikas";
    private static String password = "root";
    private static PreparedStatement statement;
    static Connection con;
    private final String txtFilePath;
    private final String searchWord;
    private final int count;
    private final String status;
    private final String errorMessage;

    public DbConnector(String txtFilePath, String searchWord, int count, String status, String errorMessage) {

        this.txtFilePath = txtFilePath;
        this.searchWord = searchWord;
        this.count = count;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public void run() {
        try {
            con = DriverManager.getConnection(url, user, password);
            statement = con.prepareStatement(query);
            statement.setString(1, txtFilePath);
            statement.setString(2, searchWord);
            statement.setString(3, status);
            statement.setInt(4, count);
            statement.setString(5, errorMessage);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

