import MasterData.CompanyDbDao;
import org.json.simple.JSONArray;

public class Reader {
    private String userName;
    private String password;
    private String url;

    public Reader(String userName, String password, String url) {
        this.userName = userName;
        this.password = password;
        this.url = url;
    }

    public Reader() {

    }

    public String getUserName() {
        return userName;
    }

    public JSONArray readCompanies() {
        CompanyDbDao companies = new CompanyDbDao();
        return companies.getCompanies();

    }

    public String getPassword() {
        return password;
    }
//    public void connection(){
//        CompanyDbDao read = new CompanyDbDao();
//        read.getCompanies();
//    }

    public Reader connection(String url, String user, String password) {
        CompanyDbDao read = new CompanyDbDao();
        read.getCompanies();
        Reader rd = new Reader();

        return rd;
    }
}
