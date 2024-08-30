package com.expenseTracker.backend.writers;

import com.expenseTracker.backend.data.User;

public class XLSXExporter extends FileExporter{
    public XLSXExporter(String filePath, User user) {
        super(filePath, user);
    }

    @Override
    public void exportFile() {

    }
}
