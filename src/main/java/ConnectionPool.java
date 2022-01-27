import org.json.simple.JSONArray;

public interface ConnectionPool {

    Reader getConnection();

    boolean releaseConnection(Reader reader);


    String getUrl();

    String getUser();

    JSONArray readCompanies();
    String getPassword();
}