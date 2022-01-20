package DB.DAO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Models.Company;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CompanyDbDao implements CompanyDao {


    @Override
    public JSONArray getCompanies() {

        JSONParser jsonParser = new JSONParser();
        JSONArray employeeList = new JSONArray();
        try (FileReader reader = new FileReader("DB.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            employeeList = (JSONArray) obj;
            // System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void addCompany(Company company) {
        JSONArray employeeList = getCompanies();


        //First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("companyName", company.getCompanyName());
        employeeDetails.put("numberOfStaff", company.getNumberOfStaff());
        employeeDetails.put("website", company.getWebsite());

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("company", employeeDetails);


        //Add employees to list
        employeeList.add(employeeObject);


        try {

            FileWriter file = new FileWriter("DB.json");
            file.write(employeeList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("company");

        //Get companyName
        String companyName = (String) employeeObject.get("companyName");

        //Get NumberOfStaff
        String NumberOfStaff = (String) employeeObject.get("numberOfStaff");

        //Get website name
        String website = (String) employeeObject.get("website");
    }

}