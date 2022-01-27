import MasterData.CompanyDbDao;

import Models.Company;
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
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "Controller", value = "/Controller")

public class Controller extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();
//    private   ServletContext context = getServletContext();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LoginService service = new LoginService();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

        User user = GSON.fromJson(input, User.class);
//        System.out.println("userName: " + user.getUserName() + "  password: " + user.getPassword()
//                + " role: " + user.getRole());

        boolean isValidUser = service.validateUser(user.getUserName(), user.getPassword(), user.getRole());
        if (isValidUser && user.getRole().equals("admin")) {
            resp.setStatus(201);
            ServletContext context = getServletContext();
            context.setAttribute("role", user.getRole());

//            HttpSession session =req.getSession() ;
//            session.setAttribute("role",user.getRole());


            //  ServletContext context = getServletContext();

            //resp.encodeRedirectURL("/Master");
            //  resp.sendRedirect("/Master");
//            req.getRequestDispatcher("/Master").forward(
//                    req, resp);

        } else if (isValidUser && user.getRole().equals("reader")) {
            resp.setStatus(201);

            //  context.setAttribute("role",user.getRole());

        } else {
            req.setAttribute("errorMessage", "inValid user");
            out.println("<inValid user");
            resp.setStatus(403);

        }


    }

    public void destroy() {
    }

}

