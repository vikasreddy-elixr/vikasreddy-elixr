package com.company;

import com.dbconnectors.DbConnector;

import java.sql.SQLException;
import java.util.StringTokenizer;

import static com.company.ErrorMessage.SUCCESS_MESSAGE_WORD_FOUND;

public class SearchTheWord extends Thread {
    private DbConnector database;
    private String data;
    private int count;
    private String searchWord;
    private String txtFilePath;


    public SearchTheWord(String data, int count, String searchWord, String txtFilePath, DbConnector database) {
        this.data = data;
        this.count = count;
        this.searchWord = searchWord;
        this.txtFilePath = txtFilePath;
        this.database = database;
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
                database.databaseConnector(txtFilePath, searchWord, count, Constants.STATUS_FAILURE, ErrorMessage.ERROR_MESSAGE_WORD_NOT_FOUND);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println(SUCCESS_MESSAGE_WORD_FOUND);
            System.out.println("The word has been repeated for " + count + " times");
            try {
                database.databaseConnector(txtFilePath, searchWord, count, Constants.STATUS_SUCCESS, "null");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
