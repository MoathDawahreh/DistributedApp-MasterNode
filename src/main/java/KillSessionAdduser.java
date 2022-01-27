import MasterData.CompanyDbDao;
import MasterData.UserDao;
import Models.Company;
import Models.User;
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
import java.util.stream.Collectors;


@WebServlet(name = "Clear&add", value = "/Clear&add")
public class KillSessionAdduser extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext context = getServletContext();
        context.removeAttribute("role");
        HttpSession session = req.getSession();
        session.removeAttribute("userName");
        session.invalidate();
        resp.setStatus(200);
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            resp.setStatus(201);
            //resp.getOutputStream().println("Hello, World from Post Method");
            resp.setContentType("application/json");

            //BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

            User user = GSON.fromJson(input, User.class);
            //    System.out.println("input iss"+user );

            // System.out.println("input iss"+user.getNumberOfStaff() );
            UserDao dao = new UserDao();
            dao.addUser(user);

            //resp.getOutputStream().println(in);
        }


    public void destroy() {
    }
}

