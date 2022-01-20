package DB.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CompanyDao implements Company {


    @Override
    public JSONArray getCompanies() {

        JSONParser jsonParser = new JSONParser();
        JSONArray employeeList = new JSONArray();
        try (FileReader reader = new FileReader("DB.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }


    private  void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("company");

        //Get companyName
        String companyName = (String) employeeObject.get("companyName");

        //Get NumberOfStaff
        String NumberOfStaff = (String) employeeObject.get("NumberOfStaff");

        //Get website name
        String website = (String) employeeObject.get("website");
    }


//    public   void write () {
//        //Creating a JSONObject object
//        JSONObject jsonObject = new JSONObject();
//        //Inserting key-value pairs into the json object
//        jsonObject.put("ID", "1");
//        jsonObject.put("First_Name", "Shikhar");
//        jsonObject.put("Last_Name", "Dhawan");
//        jsonObject.put("Date_Of_Birth", "1981-12-05");
//        jsonObject.put("Place_Of_Birth", "Delhi");
//        jsonObject.put("Country", "India");
//        try {
//            FileWriter file = new FileWriter("output.json",true);
//            file.write(jsonObject.toJSONString());
//            file.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        System.out.println("JSON file created: "+jsonObject);
//    }
}