package com.company;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dbconnectors.DbConnector;

public class Main {
    static int count = 0;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(Constants.errorMessage1);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage1);
            database.start();
            return;
        }
        Constants.txtFilePath = args[0];
        Constants.searchWord = args[1];
        if (Constants.searchWord == null || Constants.searchWord.equals(" ") || Constants.searchWord.equals("")) {
            System.out.println(Constants.errorMessage6);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage6);
            database.start();
            return;
        }
        if (Constants.txtFilePath == null || Constants.txtFilePath.equals(" ") || Constants.txtFilePath.equals("")) {
            System.out.println(Constants.errorMessage6);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage7);
            database.start();
            return;
        }

        Pattern pattern = Pattern.compile(Constants.SPECIAL_CHAR_REMOVAL_REGEX);
        Matcher matcher = pattern.matcher(Constants.searchWord);
        boolean isStringContainsSpecialCharacter = matcher.find();
        if (isStringContainsSpecialCharacter) {
            System.out.println(Constants.errorMessage2);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage2);
            database.start();
            return;
        }

        File file = new File(Constants.txtFilePath);

        if (!file.exists() || !isFileFormatSupported(file) || file.length() == 0) {
            System.out.println(Constants.errorMessage3);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage3);
            database.start();
            return;
        }

        System.out.println("Processing................");
        String data = readFileAsString(Constants.txtFilePath);
        if (data == null) {
            System.out.println(Constants.errorMessage4);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage4);
            database.start();
            return;
        }
        SearchWord.searchTheWord(data, count);
    }

    public static String readFileAsString(String fileName) {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            data = data.replaceAll(Constants.SPECIAL_CHAR_REMOVAL_REGEX, Constants.SINGLE_SPACE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean isFileFormatSupported(File file) {
        return (file.getName().endsWith(Constants.FILE_TYPE_TXT) || file.getName().endsWith(Constants.FILE_TYPE_JSON));
    }

}