package com.financeapp.backend.writers;

import com.financeapp.backend.data.User;

public class TXTWriter extends FileExtensionWriter {
    public TXTWriter(String filePath, User user)
    {
        super(filePath, user);
    }

    @Override
    protected void exportFile() {

    }
}
