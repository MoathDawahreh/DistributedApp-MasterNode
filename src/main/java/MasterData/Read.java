package MasterData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Read {


    public JSONArray read() {
        JSONParser jsonParser = new JSONParser();
        JSONArray employeeList = new JSONArray();
        try (FileReader reader = new FileReader("DB.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("company");

        String companyName = (String) employeeObject.get("companyName");

        String NumberOfStaff = (String) employeeObject.get("NumberOfStaff");

        String website = (String) employeeObject.get("website");
    }


}
