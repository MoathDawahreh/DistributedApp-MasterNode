package MasterData;

import Models.User;
import org.json.simple.JSONArray;

public interface UserDao {
    void addUser(User user);
    User getUser(String username);
    JSONArray getUsers();

}
