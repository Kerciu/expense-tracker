package com.financeapp.test;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.app.addframe.AddExpenseFrame;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AddExpenseFrameTest {
    private AddExpenseFrame frame;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User(1, "Test User", "Password", new BigDecimal(100)); // Assuming a User constructor exists
        frame = new AddExpenseFrame("Test Frame", mockUser, 800, 600);
    }

    @Test
    public void testAddExpenseFrameInitialization() {
        assertNotNull(frame.getAmountPanel());
        assertNotNull(frame.getTransactionTypePanel());
        assertNotNull(frame.getCategoryPanel());
        assertNotNull(frame.getDescriptionPanel());
        assertNotNull(frame.getButtonPanel());
    }

    @Test
    public void testPanelBounds() {
        assertEquals(0, frame.getAmountPanel().getBounds().x);
        assertEquals(80, frame.getAmountPanel().getBounds().y);
        assertEquals(800, frame.getAmountPanel().getBounds().width);
        assertEquals(80, frame.getAmountPanel().getBounds().height);

        assertEquals(0, frame.getTransactionTypePanel().getBounds().x);
        assertEquals(160, frame.getTransactionTypePanel().getBounds().y);
        assertEquals(800, frame.getTransactionTypePanel().getBounds().width);
        assertEquals(40, frame.getTransactionTypePanel().getBounds().height);

        assertEquals(0, frame.getCategoryPanel().getBounds().x);
        assertEquals(200, frame.getCategoryPanel().getBounds().y);
        assertEquals(800, frame.getCategoryPanel().getBounds().width);
        assertEquals(80, frame.getCategoryPanel().getBounds().height);

        assertEquals(0, frame.getDescriptionPanel().getBounds().x);
        assertEquals(280, frame.getDescriptionPanel().getBounds().y);
        assertEquals(800, frame.getDescriptionPanel().getBounds().width);
        assertEquals(220, frame.getDescriptionPanel().getBounds().height);

        assertEquals(0, frame.getButtonPanel().getBounds().x);
        assertEquals(500, frame.getButtonPanel().getBounds().y);
        assertEquals(800, frame.getButtonPanel().getBounds().width);
        assertEquals(40, frame.getButtonPanel().getBounds().height);
    }
}
