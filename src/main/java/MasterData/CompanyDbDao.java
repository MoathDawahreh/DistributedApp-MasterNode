package MasterData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import Models.Company;
 import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CompanyDbDao implements CompanyDao {
    private ConcurrentHashMap<String, Company> concurrentHashMaphashMap = new ConcurrentHashMap<>();


    @Override
    public JSONArray getCompanies() {

        JSONParser jsonParser = new JSONParser();
        JSONArray companiesList = new JSONArray();
        try (FileReader reader = new FileReader("C:\\Users\\moath\\Desktop\\Database\\Companies.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            companiesList = (JSONArray) obj;
            // System.out.println(companiesList);
             //Iterate over employee array
            companiesList.forEach(emp -> parseEmployeeObject((JSONObject) emp));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return companiesList;
    }

    @Override
    public void addCompany(Company company) {
        JSONArray companiesList = getCompanies();


        JSONObject companyDetails = new JSONObject();
        companyDetails.put("companyName", company.getCompanyName());
        companyDetails.put("numberOfStaff", company.getNumberOfStaff());
        companyDetails.put("website", company.getWebsite());

        JSONObject companyObject = new JSONObject();
        companyObject.put("company", companyDetails);


        //Add a company to list
        companiesList.add(companyObject);


        try {
            FileWriter file = new FileWriter("DB.json");
            file.write(companiesList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseEmployeeObject(JSONObject employee) {
        //Get a company in from the list
        JSONObject companyObject = (JSONObject) employee.get("company");

        String companyName = (String) companyObject.get("companyName");

        String NumberOfStaff = (String) companyObject.get("numberOfStaff");

        String website = (String) companyObject.get("website");

        Company company = new Company(companyName, NumberOfStaff, website);
       // System.out.println(company);
        concurrentHashMaphashMap.put(companyName,company);

      //  concurrentHashMaphashMap.forEach( (k,v)-> System.out.println( ));


    }

}