import MasterData.CompanyDbDao;

import Models.Company;
import Models.User;
import Services.LoginService;
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


@WebServlet(name = "Controller", value = "/Controller")

public class Controller extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LoginService service = new LoginService();

        //resp.getOutputStream().println("Hello, World from Post Method");
        resp.setContentType("application/json");

        //BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

        User user = GSON.fromJson(input, User.class);
        System.out.println("userName: " + user.getUserName() + "  password: " + user.getPassword()
                + " role: " + user.getRole());

        boolean isValidUser = service.validateUser(user.getUserName(), user.getPassword(), user.getRole());
        if (isValidUser) {
            resp.setStatus(201);
        }else {
            resp.setStatus(403);
        }



    }


}

