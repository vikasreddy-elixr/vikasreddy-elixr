package com.company;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.lang.String;


public class Main {
    public static void main(String[] args) {

        String txtFilePath = args[0];

        File file = new File(txtFilePath);
        if(file.length()==0)
        {
            System.out.println("Enter the valid path ");
            System.exit(0);
        }
        isFileFormatSupported(file);
        isFileExists(file);

        if((isFileExists(file) && isFileFormatSupported(file))== false)
        {
            System.out.println("file is not valid");
            System.exit(0);
        }


        System.out.println("processing................");

        String data = readFileAsString(txtFilePath);


        String searchword = null;
        try {
            searchword = args[1];
        } catch (Exception e) {
            System.out.println("search word has not entered");
            System.exit(1);
        }

        searchTheWord(data, searchword);


    }


        public static String readFileAsString(String fileName)
             {
                String data = "";
              try {
                   data = new String(Files.readAllBytes(Paths.get(fileName)));
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return data.replaceAll("[^a-zA-Z0-9@]", " ");

    }

        public static boolean isFileFormatSupported(File file)
        {
            return (file.getName().endsWith(".txt"));
        }

        public static boolean isFileExists(File file) {
           return file.exists();
        }

            public static void searchTheWord(String data,String searchword) {
                StringTokenizer st = new StringTokenizer(data);
                int count = 0;
                if(st.hasMoreElements()==false)
                {
                    System.out.println("file is empty");
                    System.exit(0);
                }
                while (st.hasMoreTokens())
                {

                        if (searchword.equalsIgnoreCase(st.nextToken())) {
                            count++;
                        }

                }

                if (count == 0) {
                    System.out.println("Word not found");
                } else {
                    System.out.println("The word has been found");
                    System.out.println("the word has been repeated for " + count + " times");
                }
            }

        }






