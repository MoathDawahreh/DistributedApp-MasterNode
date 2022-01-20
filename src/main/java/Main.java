
import DB.DAO.CompanyDao;
import DB.DAO.CompanyDbDao;
import DB.DAO.Read;
import org.json.simple.JSONObject;

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

       // System.out.println(uniqueID);
        //load();
//        Read read = new Read();
//        read.read();
        CompanyDao dao = new CompanyDbDao();
       // dao.addCompany();

//        WriteTest writeTest = new WriteTest();
//        writeTest.write();

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



//
//
// FROM openjdk:8-jdk-alpine
//# Install Maven
//        RUN apk add --no-cache curl tar bash
//        ARG MAVEN_VERSION=3.3.9
//        ARG USER_HOME_DIR="/root"
//        RUN mkdir -p /usr/share/maven && \
//        curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
//        ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
//        ENV MAVEN_HOME /usr/share/maven
//        ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
//        # speed up Maven JVM a bit
//        ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
//        ENTRYPOINT ["/usr/bin/mvn"
//
//        # Install project dependencies and keep sources
//        # make source folder
//        RUN mkdir -p /usr/src/app
//        WORKDIR /usr/src/app
//        # install maven dependency packages (keep in image)
//        COPY pom.xml /usr/src/app
//        RUN mvn -T 1C install && rm -rf target
//
//        # Adding source, compile and package into a fat jar
//        ADD src /code/src
//        RUN ["mvn", "package"]
//
//        EXPOSE 8080
//        CMD ["mvn", "tomcat7:run"]

// .......................
//FROM tomcat
//    COPY target/DocumentDB-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/DocumentDB-1.0-SNAPSHOT.war
//
