package model;

import exceptions.DoesNotExistAccount;
import exceptions.EmptyAccountListException;
import exceptions.InvalidLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User testUser;
    private Account testAccount;
    private Account testAccount2;

    @BeforeEach
    public void runBefore() {
        testUser = new User(1234);
        testAccount = new Account("facebook", "123456", "cherry", "cherry@gmail.com");
        testAccount2 = new Account("instagram", "654321", "john", "john@gmail.com");
    }

    @Test
    public void testSetLoginNoException() {
        try {
            testUser.setLogin(4332);
        } catch (InvalidLoginException e) {
            fail("Should not have caught exception");
        }
    }

    @Test
    public void testSetLoginInvalidLoginException() {
        try {
            testUser.setLogin(12345);
            fail("Exception not thrown");
        } catch (InvalidLoginException e) {
            // pass
        }
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
    public void testRemoveAccountNoException() {
        testUser.addAccount(testAccount);
        try {
            testUser.removeAccount(testAccount);
            assertEquals(0, testUser.size());
            assertFalse(testUser.contains(testAccount));
            // pass
        } catch (EmptyAccountListException e) {
            fail("Should not have caught exception");
        } catch (DoesNotExistAccount doesNotExistAccount) {
            fail("Should not have caught exception");
        }
    }

    @Test
    public void testRemoveAccountEmptyAccountListException() {
        try {
            testUser.removeAccount(testAccount);
            fail("Exception not thrown");
        } catch (EmptyAccountListException e) {
            // pass
        } catch (DoesNotExistAccount doesNotExistAccount) {
            fail("Should not have caught exception");
        }
    }

    @Test
    public void testRemoveAccountDoesNotExistAccount() {
        testUser.addAccount(testAccount);
        try {
            testUser.removeAccount(testAccount2);
            fail("Exception not thrown");
        } catch (EmptyAccountListException e) {
            fail("Should not have caught exception");
        } catch (DoesNotExistAccount doesNotExistAccount) {
            // pass
        }
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
