package com.company;

import java.sql.SQLException;
import java.util.StringTokenizer;

import static com.company.Main.*;

public class SearchTheWord extends Thread {
    String data;
    int count;

    public SearchTheWord(String data, int count) {
        this.data = data;
        this.count = count;
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
