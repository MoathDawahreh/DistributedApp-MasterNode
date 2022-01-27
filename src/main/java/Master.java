import MasterData.CompanyDbDao;
import Models.Company;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "Master", value = "/Master")

public class Master extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext context = getServletContext();

        String roleContext = (String) context.getAttribute("role");
        if (roleContext == null || !roleContext.equals("admin")) {
            resp.setStatus(403);
        } else {


            resp.setStatus(200);
//        HttpSession session = req.getSession();
//
//        Object roleSession = session.getAttribute("role");


            System.out.println(roleContext + " " + roleContext.equals("admin"));
            resp.setHeader("Content-Type", "application/json");
            CompanyDbDao com = new CompanyDbDao();
            List companies = com.getCompanies();

            PrintWriter out = resp.getWriter();

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8082/Load-Balancer/test"))
//                .build();

//        try {
//            HttpResponse<String> response =
//                    client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//            out.println(response.body());
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            //  resp.getOutputStream().println(json);

            out.println(companies);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context = getServletContext();


        String roleContext = (String) context.getAttribute("role");
        if (roleContext == null || !roleContext.equals("admin")){
            resp.setStatus(403);
        }else {


        resp.setStatus(201);
        //resp.getOutputStream().println("Hello, World from Post Method");
        resp.setContentType("application/json");

        //BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

        Company company = GSON.fromJson(input, Company.class);
        //    System.out.println("input iss"+company );

        // System.out.println("input iss"+company.getNumberOfStaff() );
        CompanyDbDao dao = new CompanyDbDao();
        dao.addCompany(company);

        //resp.getOutputStream().println(in);
        }
    }


}

