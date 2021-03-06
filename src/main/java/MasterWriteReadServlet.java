import Cache.LRUCache;
import DatabaseDAO.CompanyDbDao;
import Models.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "Master", value = "/Master")

public class MasterWriteReadServlet extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();
    private final LRUCache cache = new LRUCache();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        ServletContext context = getServletContext();

        String roleContext = (String) context.getAttribute("role");
        if (roleContext == null || !roleContext.equals("admin")) {
            resp.setStatus(403);
        } else {
            resp.setHeader("Content-Type", "application/json");
            CompanyDbDao com = new CompanyDbDao();
            List<Company> companies = com.getCompanies();

            out.println(companies);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context = getServletContext();


        String roleContext = (String) context.getAttribute("role");
        if (roleContext == null || !roleContext.equals("admin")) {
            resp.setStatus(403);
        } else {

            resp.setStatus(201);
            resp.setContentType("application/json");
            LRUCache.cachedData="";
            String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

            Company company = GSON.fromJson(input, Company.class);
            CompanyDbDao dao = new CompanyDbDao();
            dao.addCompany(company);
        }
    }


}

