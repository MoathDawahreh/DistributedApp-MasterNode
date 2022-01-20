package DB.DAO;

import Models.Company;
import org.json.simple.JSONArray;

public interface CompanyDao {

    JSONArray getCompanies();
    void addCompany(Company company );

}
