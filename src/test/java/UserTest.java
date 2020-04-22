import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void getIDTest(){
        User christian = new User("christian","abc");
        assertEquals("christian", christian.getUsername());
    }

    @Test
    public void getPasswordTest(){
        User christian = new User("christian","abc");
        assertEquals("abc", christian.getPassword());
    }

   /* @Test
      public void toStringTest(){
        User christian = new User("christian","abc");
        assertEquals("christian", christian.getID());
    }*/
}
