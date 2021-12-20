package com.company;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.lang.String;


public class Main {
    public static void main(String[] args){

        String txtfile = args[0];
        File file = new File(txtfile);
        checkforsupport(file);
        checkforexistance(file);
        String searchword = args[1];
        System.out.println("processing................");
        String data = null;
        data = readFileAsString(txtfile);
        avoidspecialchar(data);
        searchtheword(data,searchword);


    }


        public static String readFileAsString(String fileName)
             {
                String data = "";
              try {
                   data = new String(Files.readAllBytes(Paths.get(fileName)));
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return data;
    }

        public static void checkforsupport(File file)
        {

            if (file.getName().endsWith(".txt")) {
                System.out.println("file supported");

            }

            else
                {
                    System.out.println("file not supported");
                }
        }

        public static void checkforexistance(File file) {
            if (file.exists()) {

                System.out.println("file exists");
            }
            else {
                System.out.println("file doesn't found ");
            }
        }
        public static void avoidspecialchar(String data)
        {
            data = data.replaceAll("[^a-zA-Z0-9@]", " ");
        }
            public static void searchTheWord(String data,String searchword) {
                StringTokenizer st = new StringTokenizer(data);
                int count = 0;
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






