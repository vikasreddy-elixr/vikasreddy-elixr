package com.company;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.lang.String;


public class Main {
    public static void main(String[] args) {


        String txtFilePath = null;
        try {
            txtFilePath = args[0];
        } catch (Exception e) {
            System.out.println("Path was not entered");
            return ;
        }


        File file = new File(txtFilePath);

        isFileFormatSupported(file);

        if(file.length() == 0) {
            System.out.println("Enter the valid path ");
            return ;
        }

        if(!file.exists() || !isFileFormatSupported(file)) {
            System.out.println("file is not valid");
            return ;
        }



        System.out.println("processing................");

        String data = readFileAsString(txtFilePath);


        String searchword = null;
        try {
            searchword = args[1];
        } catch (Exception e) {
            System.out.println("word to search has not entered");
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



            public static void searchTheWord(String data,String searchword) {
                StringTokenizer st = new StringTokenizer(data);
                int count = 0;
                if(st.hasMoreElements()==false)
                {
                    System.out.println("file is empty");
                    return ;
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






