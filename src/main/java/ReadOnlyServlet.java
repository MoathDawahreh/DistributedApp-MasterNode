
import Cache.LRUCache;
import DatabaseDAO.CompanyDbDao;
import LoadBalancer.LoadBalancer;
import LoadBalancer.WeightedRoundRobinLoadBalancer;
import Models.Company;

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
import java.util.concurrent.ConcurrentHashMap;
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
    //private final int maxNodes = 30;
    //private LoadBalancer.LoadBalancer weightedRoundRobin = new LoadBalancer.WeightedRoundRobinLoadBalancer(nodesPool);
    private  LRUCache cache = new LRUCache();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setStatus(200);
    //    LRUCache cache = new LRUCache();
        CompanyDbDao c = new CompanyDbDao();

        ConcurrentHashMap<String, Company> concurrentHashMaphashMap = c.concurrentHashMaphashMap;
         LoadBalancer weightedRoundRobin = new WeightedRoundRobinLoadBalancer(nodesPool);
        ServletContext context = getServletContext();
        resp.setHeader("Content-Type", "application/json");

        PrintWriter out = resp.getWriter();
//        HttpSession session = req.getSession();
//        Object roleSession = session.getAttribute("role");
        String roleContext = (String) context.getAttribute("role");

//        synchronized(getServletContext()) {
//            String roleContext = getServletContext().getAttribute("role");
//           }

        if (roleContext == null || !roleContext.equals("user")) {
            resp.setStatus(403);
        } else {
            NUM_OF_REQUESTS++;


            if (weightedNodesPool.size() < NUM_OF_REQUESTS) {

                GenerateWeightedNodes(weightedRoundRobin);
            }


//            out.println("NUM_OF_REQUESTS is: " + NUM_OF_REQUESTS + "\n" + "pool size is :" + weightedNodesPool.size() + "\n" + "server: " + weightedNodesPool.get(NUM_OF_REQUESTS - 1));
            if (!cache.get(LRUCache.cachedData)){

             try {
                HttpClient client = HttpClient.newHttpClient();

                java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                        .uri(URI.create(weightedNodesPool.get(NUM_OF_REQUESTS - 1)))
                        .header("Authorization", "redOnly")
                        //  .header("Authorization", basicAuth("username", "password"))
                        .build();


                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());
              out.println("\n" + "Not cached!! let's fetch some data :)");
              out.println("chace.get:"+cache.get(LRUCache.cachedData));
                LRUCache.cachedData= response.body();
                cache.refer( LRUCache.cachedData);
                   // out.println(LRUCache.cachedData);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            }

            out.println("This is cached data \n"+LRUCache.cachedData);
            //  resp.sendRedirect(weightedNodesPool.get(NUM_OF_REQUESTS - 1));

            if (weightedNodesPool.size() == NUM_OF_REQUESTS) {
                NUM_OF_REQUESTS = 0;
                weightedNodesPool.clear();
            }

        }
    }

    private void GenerateWeightedNodes(LoadBalancer loadBalancer) {
        IntStream
                .range(0, 30)
                .parallel()
                .forEach(i ->
                        weightedNodesPool.add(loadBalancer.getIp())
                );
        //  weightedNodesPool.forEach(e -> System.out.println(e));
    }


}

