package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User testUser;
    private Account testAccount;

    @BeforeEach
    public void runBefore() {
        testUser = new User(1234);
        testAccount = new Account("facebook", "123456", "cherry", "cherry@gmail.com");
    }

    @Test
    public void testGetAccountList() {
        assertFalse(testUser.getAccountList().contains(testAccount));
        testUser.addAccount(testAccount);

        assertTrue(testUser.getAccountList().contains(testAccount));
    }

    @Test
    public void testAddAccount() {
        testUser.addAccount(testAccount);
        assertEquals(1, testUser.size());
        assertTrue(testUser.contains(testAccount));
    }

    @Test
    public void testRemoveAccount() {
        testUser.addAccount(testAccount);
        testUser.removeAccount(testAccount);
        assertEquals(0, testUser.size());
        assertFalse(testUser.contains(testAccount));
    }

    @Test
    public void testCheckLogin() {
        assertTrue(testUser.checkLogin(1234));
        assertFalse(testUser.checkLogin(4321));
    }

    @Test
    public void testSize() {
        testUser.addAccount(testAccount);
        testUser.addAccount(testAccount);
        assertEquals(2, testUser.size());
    }

    @Test
    public void testContains() {
        assertFalse(testUser.contains(testAccount));

        testUser.addAccount(testAccount);
        assertTrue(testUser.contains(testAccount));
    }
}
