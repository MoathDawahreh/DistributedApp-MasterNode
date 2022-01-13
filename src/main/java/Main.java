
import DB.CRUD.Read;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


public class Main {
    protected static List list = new ArrayList<Integer>();


    public static void main(String[] args) {
        String uniqueID = UUID.randomUUID().toString();

        System.out.println(uniqueID);
        //load();
        Read read = new Read();
        read.read();

         //System.out.println(list);

    }


        protected static void load(){
        try (Stream<String> stream = Files.lines(Paths.get("numbers.txt"))) {

            stream.forEach(x -> list.add(Integer.parseInt(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    protected  static void  read (){
//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader("DB.json"))
//        {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray employeeList = (JSONArray) obj;
//            System.out.println(employeeList);
//
//            //Iterate over employee array
//            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }

    private static void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
    }



    private static void id(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee first name
        String firstName = (String) employeeObject.get("_id");
        System.out.println(firstName);

    }



}
