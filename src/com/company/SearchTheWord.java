package com.company;

import java.sql.SQLException;
import java.util.StringTokenizer;

import static com.company.Main.*;

public class SearchTheWord extends Thread {
    String data;
    int count;
    String searchWord;
    String txtFilePath;

    public SearchTheWord(String data, int count, String searchWord, String txtFilePath) {
        this.data = data;
        this.count = count;
        this.searchWord=searchWord;
        this.txtFilePath=txtFilePath;
    }

    public void run() {
        StringTokenizer st = new StringTokenizer(data);

        while (st.hasMoreTokens()) {
            if (searchWord.equalsIgnoreCase(st.nextToken())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println(ErrorMessage.ERROR_MESSAGE_WORD_NOT_FOUND);
            try {
                database.databaseConnector(txtFilePath, searchWord, count, Constants.status, ErrorMessage.ERROR_MESSAGE_WORD_NOT_FOUND);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("The word has been found");
            System.out.println("The word has been repeated for " + count + " times");
            try {
                database.databaseConnector(txtFilePath, searchWord, count, "Success", "null");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
