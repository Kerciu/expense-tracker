package com.financeapp.backend.writers;

import com.financeapp.backend.data.User;

public class TXTExporter extends FileExporter {
    public TXTExporter(String filePath, User user)
    {
        super(filePath, user);
    }

    @Override
    public void exportFile() {

    }
}
