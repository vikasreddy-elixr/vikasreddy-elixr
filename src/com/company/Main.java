package com.company;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Jdbc{
    final static String FILE_TYPE_TXT = ".txt";
    final static String FILE_TYPE_JSON = ".json";
    final static String SPECIAL_CHAR_REMOVAL_REGEX = "[^a-zA-Z0-9]";
    final static String SINGLE_SPACE = " ";
    static PreparedStatement statement;
    static String txtFilePath;
    static String searchWord;

    public static void main(String[] args) throws SQLException {

        if (args.length != 2) {
            String errorMessage = "Exiting, since no file path and word to search for are provided!";
            System.out.println(errorMessage);
            databaseZeroInput(errorMessage);
            return;
        }
        txtFilePath = args[0];
        searchWord = args[1];

        Pattern pattern = Pattern.compile(SPECIAL_CHAR_REMOVAL_REGEX);
        Matcher matcher = pattern.matcher(searchWord);
        boolean isStringContainsSpecialCharacter = matcher.find();
        if(isStringContainsSpecialCharacter) {
            String errorMessage="the search Word contains special character";
            System.out.println(errorMessage);
            databaseFailure(txtFilePath,searchWord,errorMessage);
            return;
        }

        File file = new File(txtFilePath);

        if (!file.exists() || !isFileFormatSupported(file) || file.length() == 0) {
            String errorMessage = "File is not valid or file doesn't exist or File doesn't contain any data";
            System.out.println(errorMessage);
            databaseFailure(txtFilePath,searchWord,errorMessage);
            return;
        }

        System.out.println("Processing................");
        String data = readFileAsString(txtFilePath);
        if(data==null)
        {
            System.out.println("Couldn't read data from the file");
            String errorMessage = "Couldn't read data from the file";
            databaseFailure(txtFilePath,searchWord,errorMessage);
            return;
        }
        searchTheWord(data, searchWord);
    }

    public static String readFileAsString(String fileName) {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            data = data.replaceAll(SPECIAL_CHAR_REMOVAL_REGEX, SINGLE_SPACE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean isFileFormatSupported(File file) {
        return (file.getName().endsWith(FILE_TYPE_TXT) || file.getName().endsWith(FILE_TYPE_JSON));
    }

    public static void searchTheWord(String data, String searchword) throws SQLException {
        StringTokenizer st = new StringTokenizer(data);
        int count = 0;

        while (st.hasMoreTokens()) {
            if (searchword.equalsIgnoreCase(st.nextToken())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Word not found");
            String errorMessage = "Word not found";
            databaseFailure(txtFilePath,searchWord,errorMessage);

        } else {
            System.out.println("The word has been found");
            System.out.println("The word has been repeated for " + count + " times");
            databaseSuccess(txtFilePath,searchWord,count);

        }
        if(count>0)
        {
            String result= "success";
        }
        else
        {
            String result="error";
        }
    }
}