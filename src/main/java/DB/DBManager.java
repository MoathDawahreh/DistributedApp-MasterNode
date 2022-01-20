package DB;

import DB.DAO.CompanyDbDao;
import DB.DAO.Read;

import Models.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "Companies", value = "/Companies")

public class DBManager extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        Read read = new Read();
        CompanyDbDao com = new CompanyDbDao();
        List companies = com.getCompanies();
       // List companies = read.read();
      //  String json = GSON.toJson( read.read());

        PrintWriter out = resp.getWriter();


      //  resp.getOutputStream().println(json);

        out.println(companies);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setStatus(201);
        //resp.getOutputStream().println("Hello, World from Post Method");
        resp.setContentType("application/json");

        //BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

        Company company = GSON.fromJson(input, Company.class);
        System.out.println("input iss"+company );

        System.out.println("input iss"+company.getNumberOfStaff() );
        CompanyDbDao dao = new CompanyDbDao();
         dao.addCompany(company);

        //resp.getOutputStream().println(in);

    }






}

