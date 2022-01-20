package DB.DAO;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertThat;

public class UserDao {


    public void addUser() {

        //First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Moath");
        employeeDetails.put("lastName", "Thawahreh");
        employeeDetails.put("website", "howtodoinjava.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Jef");
        employeeDetails2.put("lastName", "Dw");
        employeeDetails2.put("website", "example.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        //Write JSON file
//        try (FileWriter file = new FileWriter("employees.json")) {
//          //  File tempFile = new File("employees.json");
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
//            File file = new File("employees.json");
            FileReader reader = new FileReader("employees.json");
            System.out.println("reader: "+reader);
            FileWriter file = new FileWriter("employees.json");
             //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString());
             file.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void getUsers(){



    }


}
