package persistence;

import model.Account;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User(1234);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            User user = new User(1234);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUser.json");
            user = reader.read();
            assertEquals(1234, user.getLogin());
            assertEquals(0, user.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User(1234);
            user.addAccount(new Account("facebook", "123456",
                    "cheillie", "cherry@gmail.com"));
            user.addAccount(new Account("instagram", "654321",
                    "cherry", "cherry@hotmail.com"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.read();
            assertEquals(1234, user.getLogin());
            List<Account> accountList = user.getAccountList();
            assertEquals(2, accountList.size());
            checkAccount("facebook", "123456",
                    "cheillie", "cherry@gmail.com", accountList.get(0));
            checkAccount("instagram", "654321",
                    "cherry", "cherry@hotmail.com", accountList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
