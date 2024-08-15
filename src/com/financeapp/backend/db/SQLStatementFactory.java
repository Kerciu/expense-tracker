package com.financeapp.backend.db;

public class SQLStatementFactory {
    public static String constructUserDataStatement(String userName, String userPassword)
    {
        return "SELECT * FROM \"user_data\" WHERE \"username\" = '"+ userName+"' AND \"password\" = '"+ userPassword+"';";
    }
}
