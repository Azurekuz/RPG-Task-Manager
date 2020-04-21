import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserListTest {
    @Test
    public void confirmCredentialsTest() {
        User christian = new User("christian","abc");
        UserList mylist = new UserList();
        mylist.addUser(christian);
        //correct credentials
        assertTrue(mylist.confirmCredentials("christian","abc"));
        //correct username wrong password
        assertFalse(mylist.confirmCredentials("christian","abbc"));
        //wrong username correct password
        assertFalse(mylist.confirmCredentials("christiann","abc"));
        //both username and password wrong
        assertFalse(mylist.confirmCredentials("christiann","abc123"));
    }

    @Test
    public void addUserTest(){
        User christian = new User("christian","abc");
        UserList mylist = new UserList();
        //adding user object check
        mylist.addUser(christian);
        assertEquals(1,mylist.userList.size());
        //adding user with name and pass
        mylist.addUser("martano","abc123");
        assertEquals(2,mylist.userList.size());
    }

    @Test
    public void removeUserTest(){
        User christian = new User("christian","abc");
        UserList mylist = new UserList();
        mylist.addUser(christian);
        mylist.addUser("martano","abc123");
        //clean remove
        mylist.removeUser("christian");
        assertEquals(1,mylist.userList.size());
        //incorrect username
        mylist.removeUser("cmartano");
        assertEquals(1,mylist.userList.size());
    }



}
