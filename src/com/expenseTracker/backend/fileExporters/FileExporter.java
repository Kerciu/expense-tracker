package com.expenseTracker.backend.fileExporters;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.backend.utils.DateBoundPair;
import com.expenseTracker.backend.utils.UserBalanceAggregator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class FileExporter {
    protected String filePath;
    protected User user;
    protected List<Transaction> transactionList;

    protected final String[] headers = {"Date", "Type", "Category", "Amount", "Description"};

    public FileExporter(String filePath, User user)
    {
        this.filePath = filePath;
        this.user = user;
        fetchUserTransactions();
    }

    public abstract void exportFile();

    private void fetchUserTransactions()
    {
        List<Transaction> transactionDetails = null;
        try {
            transactionDetails = getTransactionDetails(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.transactionList = transactionDetails;
    }

    private List<Transaction> getTransactionDetails(User user) throws SQLException
    {
        return MySQLConnector.getTransactionHistoryDetails(user);
    }

    protected String createReportDateBoundsInfo() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateBoundPair dateBoundPair = fetchTransactionDateBounds();
        return "Transaction Dates: \n" +
                dateBoundPair.getFirst().format(dateTimeFormatter)
                + "—" +
                dateBoundPair.getSecond().format(dateTimeFormatter);
    }

    private DateBoundPair fetchTransactionDateBounds() {
        // transaction list is ordered by date descending
        return new DateBoundPair(
                transactionList.getLast().getDate(),
                transactionList.getFirst().getDate()
        );
    }

    protected String createReportGeneratedDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        return "Report Generated on:\n" + LocalDateTime.now().format(dateTimeFormatter);
    }

    protected String createUserBalanceInfo() {
        String userBalance = user.getBalance().toString();
        return "Balance:\n" + userBalance;
    }

    protected String createTotalProcessedExpensesInfo() {
        BigDecimal processedExpenses = UserBalanceAggregator.getSummedUserExpenses(transactionList);
        return "Total Expenses:\n" + processedExpenses.toString();
    }

    protected String createTotalProcessedIncomeInfo() {
        BigDecimal processedIncome = UserBalanceAggregator.getSummedUserIncome(transactionList);
        return "Total Income:\n" + processedIncome.toString();
    }
}
