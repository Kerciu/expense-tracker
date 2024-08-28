package com.expenseTracker.backend.repositories;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PreparedStatementParametersSetter {
    public static void setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException
    {
        for (int i = 0; i < parameters.length; ++i)
        {
            int position = i + 1;
            distributeParametersAccordingly(preparedStatement, parameters[i], position);
        }
    }

    private static void distributeParametersAccordingly(PreparedStatement preparedStatement, Object parameter, int position) throws SQLException {
        if (parameter == null) {
            preparedStatement.setNull(position, java.sql.Types.NULL);
        }
        else if (parameter instanceof String)
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
        else {
            throw new SQLException("Unsupported parameter: " + parameter.getClass().getName());
        }
    }
}
