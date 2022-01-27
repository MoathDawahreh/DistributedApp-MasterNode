import MasterData.CompanyDbDao;
import org.json.simple.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(name = "controller", value = "/controller")
public class BasicConnectionPool extends HttpServlet implements ConnectionPool {
    private Gson GSON = new GsonBuilder().create();


    private String url = "http://localhost:8080/DocumentDB-1.0-SNAPSHOT/Companies";
    private String user;
    private String password;
    private List<Reader> connectionPool;
    private List<Reader> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;







    public BasicConnectionPool(String url, String user, String password, List<Reader> pool) {
    }

    public static BasicConnectionPool create(
            String url, String user,
            String password) {

        List<Reader> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new BasicConnectionPool("url", user, password, pool);
    }

    // standard constructors

    @Override
    public Reader getConnection() {
        Reader connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Reader connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Reader createConnection(
            String url, String user, String password) {
        Reader read = new Reader();

        return read.connection(url, user, password);
    }

    @Override
    public JSONArray readCompanies() {
        CompanyDbDao companies = new CompanyDbDao();
        System.out.println(companies.getCompanies());
        return companies.getCompanies();

    }


    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    // standard getters
}