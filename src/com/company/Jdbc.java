package com.company;

import java.sql.*;

import static com.company.Main.statement;

public class Jdbc {

    public static void databaseFailure(String txtFilePath,String searchWord,String errorMessage) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elixr_intern_poc_01","vikas","root");
        String query = "insert into audit values(?,?,now(),?,?,?)";
        statement =con.prepareStatement(query);
        statement.setString(1,txtFilePath);
        statement.setString(2,searchWord);
        statement.setString(3,"Failure");
        statement.setInt(4,0);
        statement.setString(5,errorMessage);
        statement.executeUpdate();
    }
    public static void databaseSuccess(String txtFilePath,String searchWord,int count) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elixr_intern_poc_01","vikas","root");
        String query = "insert into audit values(?,?,now(),?,?,?)";
        statement =con.prepareStatement(query);
        statement.setString(1,txtFilePath);
        statement.setString(2,searchWord);
        statement.setString(3,"Success");
        statement.setInt(4,count);
        statement.setString(5,null);
        statement.executeUpdate();
    }
    public static void databaseZeroInput(String errorMessage) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elixr_intern_poc_01","vikas","root");
        String query = "insert into audit values(?,?,now(),?,?,?)";
        statement =con.prepareStatement(query);
        statement.setString(1,null);
        statement.setString(2,null);
        statement.setString(3,"Failure");
        statement.setInt(4,0);
        statement.setString(5,errorMessage);
        statement.executeUpdate();
    }


}

