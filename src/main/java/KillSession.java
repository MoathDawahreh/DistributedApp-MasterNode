import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "Clear", value = "/Clear")
public class KillSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext context = getServletContext();
        context.removeAttribute("role");
        HttpSession session = req.getSession();
        session.removeAttribute("userName");
        session.invalidate();
        resp.setStatus(200);
    }

    public void destroy() {
    }
}

