package com.company;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.lang.String;


public class Main {
    final static String extention=".txt";
    final static String specialChars="[^a-zA-Z0-9@]";
    final static String replacement=" ";
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Exiting, since no file path and word to search for are provided!");
            return;
        }
        String txtFilePath = args[0];
        String searchword = args[1];
        File file = new File(txtFilePath);

        if (!file.exists() || !isFileFormatSupported(file)) {
            System.out.println("File is not valid");
            return;
        }
        if (file.length() == 0) {
            System.out.println("The file does not contains any data ");
            return;
        }
        System.out.println("Processing................");
        String data = readFileAsString(txtFilePath);
        searchTheWord(data, searchword);
    }

    public static String readFileAsString(String fileName) {
        String data =null;
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.replaceAll(specialChars, replacement);
    }

    public static boolean isFileFormatSupported(File file) {
        return (file.getName().endsWith(extention));
    }

    public static void searchTheWord(String data, String searchword) {
        StringTokenizer st = new StringTokenizer(data);
        int count = 0;

        while (st.hasMoreTokens()) {
            if (searchword.equalsIgnoreCase(st.nextToken())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Word not found");
        } else {
            System.out.println("The word has been found");
            System.out.println("The word has been repeated for " + count + " times");
        }
    }
}