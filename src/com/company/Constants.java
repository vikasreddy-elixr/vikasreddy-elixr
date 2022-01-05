package com.company;


public class Constants {
    final static String FILE_TYPE_TXT = ".txt";
    final static String FILE_TYPE_JSON = ".json";
    final static String SPECIAL_CHAR_REMOVAL_REGEX = "[^a-zA-Z0-9@-]";
    final static String SINGLE_SPACE = " ";
    static String txtFilePath = null;
    static String searchWord = null;

    static String status = "Failure";
    final static String errorMessage1 = "Exiting, since no file path and word to search for are provided!";
    final static String errorMessage2 = "the search Word contains special character";
    final static String errorMessage3 = "File is not valid or file doesn't exist or File doesn't contain any data";
    final static String errorMessage4 = "Couldn't read data from the file";
    final static String errorMessage5 = "Word not found";
    final static String errorMessage6 = "txtFilePath has null value or Empty";
    final static String errorMessage7 = "searchWord has null value or Empty";
}
