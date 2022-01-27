package MasterData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import Models.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static org.junit.Assert.assertThat;

public class UserDao {

    private List<User> userList = new ArrayList<>();
    private HashMap<String,User> hashMap = new HashMap<>();


    public void addUser() {

        //First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("userName", "Thawahreh");
        employeeDetails.put("password", "122");
        employeeDetails.put("role", "reader");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("user", employeeDetails);

        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("userName", "Jef");
        employeeDetails2.put("password", "33");
        employeeDetails2.put("role", "admin");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("user", employeeDetails2);

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        //Write JSON file
//        try (FileWriter file = new FileWriter("users.json")) {
//          //  File tempFile = new File("users.json");
//           // System.out.println(tempFile.exists());
//            //We can write any JSONArray or JSONObject instance to the file
//            file.write(employeeList.toJSONString());
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

        //............................

        try {
            // File tempFile = new File("temp.txt");
//            File file = new File("users.json");
            FileReader reader = new FileReader("users.json");
            System.out.println("reader: " + reader);
            FileWriter file = new FileWriter("users.json");
            //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public User getUser(String username) {
        User user = new User();

        JSONParser jsonParser = new JSONParser();
        JSONArray users = new JSONArray();
        try (FileReader reader = new FileReader("users.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            users = (JSONArray) obj;
            List us = users;

            // System.out.println(users.get(1));
            users.forEach(u -> parseEmployeeObject((JSONObject) u));
         //   userList.forEach(t-> System.out.println(t.getUserName()));
         //  System.out.println(hashMap.get("Thawahreh").getRole());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

         return hashMap.get(username);
    }


    private void parseEmployeeObject(JSONObject user) {
//        List<User> userList = new ArrayList<>();
        //Get a company in from the list
        JSONObject userObject = (JSONObject) user.get("user");

//        System.out.println(userObject);
        String userName = (String) userObject.get("userName");
//        if (userName.equals()){
//
//        }
        //    System.out.println(userName.equals("Jef"));
        String password = (String) userObject.get("password");

        String role = (String) userObject.get("role");
        User u = new User(userName, password, role);
       // userList.add(u);
        hashMap.put(userName,u);

    }


}
