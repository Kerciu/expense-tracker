package com.expenseTracker.backend.repositories;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PreparedStatementParametersSetter {
    public static PreparedStatement create(Connection connection, String query, String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        return preparedStatement;
    }

    public static void setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException
    {
        for (int i = 0; i < parameters.length; ++i)
        {
            int position = i + 1;
            distributeParametersAccordingly(preparedStatement, parameters[i], position);
        }
    }

    private static void distributeParametersAccordingly(PreparedStatement preparedStatement, Object parameter, int position) throws SQLException {
        if (parameter instanceof String)
        {
            preparedStatement.setString(position, (String) parameter);
        }
        else if (parameter instanceof BigDecimal)
        {
            preparedStatement.setBigDecimal(position, (BigDecimal) parameter);
        }
        else if (parameter instanceof Integer)
        {
            preparedStatement.setInt(position, (Integer) parameter);
        }
        else if (parameter instanceof LocalDate)
        {
            preparedStatement.setDate(position, Date.valueOf((LocalDate) parameter) );
        }
    }
}
