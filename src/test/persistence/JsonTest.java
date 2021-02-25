package persistence;

import model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAccount(String website, String password, String username,
                                String email, Account account) {
        assertEquals(website, account.getWebsite());
        assertEquals(password, account.getPassword());
        assertEquals(username, account.getUsername());
        assertEquals(email, account.getEmail());
    }
}
