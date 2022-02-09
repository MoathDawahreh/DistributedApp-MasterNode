import Models.User;
import Services.LoginService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.stream.Collectors;


@WebServlet(name = "Controller", value = "/Controller")

public class Controller extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();
    private LoginService service = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext context = getServletContext();
         PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));
        User user = GSON.fromJson(input, User.class);

        boolean isValidUser = service.validateUser(user.getUserName(), user.getPassword(), user.getRole());
        if (isValidUser && user.getRole().equals("admin")) {
            resp.setStatus(201);

            context.setAttribute("role", user.getRole());
//            synchronized(getServletContext()) {
//                getServletContext().setAttribute("role", user.getRole());
//            }


        } else if (isValidUser && user.getRole().equals("user")) {
            resp.setStatus(201);

            context.setAttribute("role", user.getRole());

            // resp.sendRedirect("/DocumentDB-1.0-SNAPSHOT/Models.Reader");

        } else {
            req.setAttribute("errorMessage", "inValid user");
            out.println("inValid user");
            resp.setStatus(403);

        }

    }


}

