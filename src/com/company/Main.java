package com.company;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dbconnectors.DbConnector;

public class Main {

    static DbConnector database = new DbConnector();
    static int count = 0;


    public static void main(String[] args) throws SQLException, InterruptedException {
         String txtFilePath = null;
         String searchWord = null;
        System.out.println(Thread.currentThread().getName());
        if (args.length != 2) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_PARAMETERS_NOT_FOUND);
            database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_PARAMETERS_NOT_FOUND);
            return;
        }
        txtFilePath = args[0];
        searchWord = args[1];
        if (searchWord == null || searchWord.trim().isEmpty()) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_WORD_IS_NULL);
            database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_WORD_IS_NULL);
            return;
        }
        if (txtFilePath == null || txtFilePath.equals(" ") || txtFilePath.equals("")) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_PATH_IS_NULL);
            database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_PATH_IS_NULL);
            return;
        }

        Pattern pattern = Pattern.compile(Constants.SPECIAL_CHAR_REMOVAL_REGEX);
        Matcher matcher = pattern.matcher(searchWord);
        boolean isStringContainsSpecialCharacter = matcher.find();
        if (isStringContainsSpecialCharacter) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_SEARCH_WORD_HAS_SPECIAL_CHARS);
            database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_SEARCH_WORD_HAS_SPECIAL_CHARS);
            return;
        }

        File file = new File(txtFilePath);

        if (!file.exists() || !isFileFormatSupported(file) || file.length() == 0) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_FILE_NOT_VALID);
            database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_FILE_NOT_VALID);
            return;
        }

        System.out.println("Processing................");
        String data = readFileAsString(txtFilePath);
        if (data == null) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_COULD_NOT_READ_DATA);
            database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_COULD_NOT_READ_DATA);
            return;
        }
        SearchTheWord search = new SearchTheWord(data, count, searchWord, txtFilePath);
        search.start();
        search.join();
        System.out.println("main exit");
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