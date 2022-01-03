package com.company;

import java.sql.*;

import static com.company.Main.statement;

public class Database {
    static String query = "insert into audit values(?,?,now(),?,?,?)";
    private static String url = "jdbc:mysql://localhost:3306/elixr_intern_poc_01";
    private static String user="vikas";
    private static String password="root";

    void databaseInsertion(String txtFilePath,String searchWord,int count,String status,String errorMessage) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            statement = con.prepareStatement(query);
            statement.setString(1, txtFilePath);
            statement.setString(2, searchWord);
            statement.setString(3, status);
            statement.setInt(4, count);
            statement.setString(5, errorMessage);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}

