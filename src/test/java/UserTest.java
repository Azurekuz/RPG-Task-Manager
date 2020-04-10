import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void UserTest(){
        User christian = new User("christian","abc");
        assertEquals("christian", christian.getID());
    }
}
