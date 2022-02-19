package DatabaseDAO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class UserDBDao implements UserDao {

    private final List<User> userList = new ArrayList<>();
    private final HashMap<String, User> hashMap = new HashMap<>();

    @Override
    public void addUser(User user) {

        JSONArray companiesList = getUsers();

        JSONObject userDetails = new JSONObject();
        userDetails.put("userName", user.getUserName());
        userDetails.put("password", user.getPassword());
        userDetails.put("role", user.getRole());

        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetails);


        //Add a user to list
        companiesList.add(userObject);

        try {
            FileWriter file = new FileWriter("users.json");
            file.write(companiesList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public User getUser(String username) {
        User user = new User();

        JSONParser jsonParser = new JSONParser();
        JSONArray users = new JSONArray();
        try (FileReader reader = new FileReader("users.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            users = (JSONArray) obj;
            users.forEach(u -> parseUserObject((JSONObject) u));

            // userList.add(u);
            //   hashMap.put(userName, u);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return hashMap.get(username);
    }
    @Override
    public JSONArray getUsers() {
        JSONParser jsonParser = new JSONParser();
        JSONArray usersList = new JSONArray();
        try (FileReader reader = new FileReader("users.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            usersList = (JSONArray) obj;
            //  usersList.forEach(emp -> parseUserObject((JSONObject) emp));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return usersList;


    }


    private void parseUserObject(JSONObject user) {

        JSONObject userObject = (JSONObject) user.get("user");

        String userName = (String) userObject.get("userName");

        String password = (String) userObject.get("password");

        String role = (String) userObject.get("role");
        User u = new User(userName, password, role);
        hashMap.put(userName, u);


    }


}
