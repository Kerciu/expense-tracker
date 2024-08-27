package com.expenseTracker.backend.db;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    public static String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkIfPasswordMatches(String password, String hashedPassword)
    {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
