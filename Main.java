package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Main {


    public static String readFileAsString(String fileName)throws IOException
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }


    public static void main(String[] args) throws IOException{
        String txtfile = args[0];


        File file = new File(txtfile);
        if (file.exists())
        {
            int count=0;
            System.out.println("file exists");
            String searchword = args[1];
            System.out.println("processing................");
            String data = readFileAsString(txtfile);

            String[] arraydata = data.split(" ");

            for(int i=0;i< arraydata.length-1;i++)
            {

                if(searchword.equals(arraydata[i]))
                {
                    count++;
                }

           }
            if(count==0)
            {
                System.out.println("Word not found");
            }
            else
            {
                System.out.println("The word has been found");
                System.out.println("the word has been repeated for " + count + " times");
            }
        }
        else
        {
            System.out.println("file doesn't found ");
        }


    }


}





