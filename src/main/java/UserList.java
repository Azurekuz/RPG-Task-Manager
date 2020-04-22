import java.util.ArrayList;
public class UserList {
    public ArrayList<User> userList;

    UserList() {
        userList = new ArrayList<User>();
    }

    public void addUser(User newUser) {
        userList.add(newUser);
    }

    public void addUser(String username, String password) {
        User newUser = new User(username, password);
        userList.add(newUser);
    }

    public void removeUser(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                userList.remove(i);
                return;
            }
        }
        System.out.println("No user found");

    }

    public boolean confirmCredentials(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())){
                if (password.equals(userList.get(i).getPassword())){
                    return true;
                }
                else{
                    return false;
                }

            }
        }
        return false;
    }
}
