package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account testAccount;

    @BeforeEach
    public void runBefore() {
        testAccount = new Account("facebook", "123456", "cherry", "cherry@gmail.com");
    }

    @Test
    public void testGetID() {
        testAccount.setId(1);
        assertEquals(1,testAccount.getId());
    }

    @Test
    public void testGetWebsite() {
        assertEquals("facebook", testAccount.getWebsite());
    }

    @Test
    public void testGetPassword() {
        assertEquals("123456", testAccount.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("cherry", testAccount.getUsername());
    }

    @Test
    public void testGetEmail() {
        assertEquals("cherry@gmail.com", testAccount.getEmail());
    }

    @Test
    public void testSetId() {
        testAccount.setId(1);
        assertEquals(1, testAccount.getId());
    }

    @Test
    public void testSetWebsite() {
        testAccount.setWebsite("instagram");
        assertEquals("instagram", testAccount.getWebsite());
    }

    @Test
    public void testSetPassword() {
        testAccount.setPassword("654321");
        assertEquals("654321", testAccount.getPassword());
    }

    @Test
    public void testSetUsername() {
        testAccount.setUsername("tim");
        assertEquals("tim", testAccount.getUsername());
    }

    @Test
    public void testSetEmail() {
        testAccount.setEmail("tim@gmail.com");
        assertEquals("tim@gmail.com", testAccount.getEmail());
    }
}