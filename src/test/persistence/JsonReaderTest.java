package persistence;

import model.Account;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUser() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUser.json");
        try {
            User user = reader.read();
            assertEquals(1234, user.getLogin());
            assertEquals(0, user.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals(1234, user.getLogin());
            List<Account> accountList = user.getAccountList();
            assertEquals(2, accountList.size());
            checkAccount("facebook", "123456",
                    "cheillie", "cherry@gmail.com", accountList.get(0));
            checkAccount("instagram", "654321",
                    "cherry", "cherry@hotmail.com", accountList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
