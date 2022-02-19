package DatabaseDAO;

import Models.Company;
import org.json.simple.JSONArray;

import java.util.concurrent.ConcurrentHashMap;


public interface CompanyDao {

    JSONArray getCompanies();

    void addCompany(Company company);


}
