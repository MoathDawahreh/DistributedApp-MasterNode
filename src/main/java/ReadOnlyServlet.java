
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
 import java.util.stream.IntStream;


@WebServlet(name = "ReadOnlyServlet", value = "/Reader")

public class ReadOnlyServlet extends HttpServlet {
    private final Map<String, Integer> nodesPool = new HashMap<String, Integer>() {{
        put("http://localhost:8081/reader/read", 1);
        put("http://localhost:8082/reader/read", 2);
        put("http://localhost:8083/reader/read", 3);
    }};
    private LinkedList<String> weightedNodesPool = new LinkedList<String>();
    private int NUM_OF_REQUESTS = 0;
    private final int maxNodes = 30;
    //private LoadBalancer weightedRoundRobin = new WeightedRoundRobinLoadBalancer(nodesPool);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(200);
        LoadBalancer weightedRoundRobin = new WeightedRoundRobinLoadBalancer(nodesPool);
        NUM_OF_REQUESTS++;
        ServletContext context = getServletContext();

        resp.setHeader("Content-Type", "application/json");

//
        PrintWriter out = resp.getWriter();
//        HttpSession session = req.getSession();
//        Object roleSession = session.getAttribute("role");
        String roleContext = (String) context.getAttribute("role");
        if (roleContext == null || !roleContext.equals("user")) {
            resp.setStatus(403);
        } else {




        if (weightedNodesPool.size() < NUM_OF_REQUESTS) {
            GenerateConcurrentNodes(weightedRoundRobin, maxNodes);
        }


        out.println("NUM_OF_REQUESTS is: " + NUM_OF_REQUESTS + "\n" + "pool size is :" + weightedNodesPool.size() + "\n" + "server: " + weightedNodesPool.get(NUM_OF_REQUESTS - 1));

        try {
            HttpClient client = HttpClient.newHttpClient();

            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(URI.create(weightedNodesPool.get(NUM_OF_REQUESTS - 1)))
                    .header("Authorization", "redOnly")
                    //  .header("Authorization", basicAuth("username", "password"))

                    .build();


            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            out.println("\n" + response.body());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (weightedNodesPool.size() == NUM_OF_REQUESTS) {
            NUM_OF_REQUESTS = 0;
            weightedNodesPool.clear();
        }

        }
    }

    private void GenerateConcurrentNodes(LoadBalancer loadBalancer, int numOfCalls) {
        IntStream
                .range(0, numOfCalls)
                .parallel()
                .forEach(i ->
                        //  System.out.println(i)
                        //     pool.put(loadBalancer.getIp(),i)
                        weightedNodesPool.add(loadBalancer.getIp())

                );

        weightedNodesPool.forEach(e -> System.out.println(e));


    }


}

