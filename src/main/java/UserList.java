import java.awt.*;
import java.util.ArrayList;
public class UserList {
    public ArrayList<User> userList;

    UserList(){
        userList = new ArrayList<User>();
    }

    public void addUser(User newUser){
        userList.add(newUser);
    }

    public void addUser(String username, String password){
        User newUser= new User(username,password);
        userList.add(newUser);
    }

    public void removeUser(String username){
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i))){
                userList.remove(i);
                break;
            }
        }
        System.out.println("No user found");

    }

}
