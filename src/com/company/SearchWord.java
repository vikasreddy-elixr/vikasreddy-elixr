package com.company;

import com.dbconnectors.DbConnector;

import java.util.StringTokenizer;

public class SearchWord {
    public static void searchTheWord(String data, int count) {

        StringTokenizer st = new StringTokenizer(data);

        while (st.hasMoreTokens()) {
            if (Constants.searchWord.equalsIgnoreCase(st.nextToken())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println(Constants.errorMessage5);
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, Constants.status, Constants.errorMessage5);
            database.start();
            return;

        } else {
            System.out.println("The word has been found");
            System.out.println("The word has been repeated for " + count + " times");
            DbConnector database = new DbConnector(Constants.txtFilePath, Constants.searchWord, count, "Success", "null");
            database.start();
            return;
        }
    }
}
