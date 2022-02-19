import DatabaseDAO.UserDBDao;
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


@WebServlet(name = "SuperAdmin", value = "/SuperAdmin")
public class SuperAdmin extends HttpServlet {
    private Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext context = getServletContext();
        context.removeAttribute("role");
        HttpSession session = req.getSession();
        session.removeAttribute("userName");
        session.invalidate();
//        synchronized(getServletContext()) {
//            getServletContext().removeAttribute("role");
//         }


        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            resp.setStatus(201);
            resp.setContentType("application/json");

            String input = new BufferedReader(new InputStreamReader(req.getInputStream())).lines().collect(Collectors.joining("\n"));

            User user = GSON.fromJson(input, User.class);
            UserDBDao dao = new UserDBDao();
            dao.addUser(user);

        }


    public void destroy() {
    }
}

