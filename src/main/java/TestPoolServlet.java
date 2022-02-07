//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.conn.HttpClientConnectionManager;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpResponse;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.IntStream;
//
//import static org.junit.Assert.assertTrue;
//
//@WebServlet(name = "test", value = "/test")
//
//public class TestPoolServlet extends HttpServlet {
//    private Gson GSON = new GsonBuilder().create();
//    private int NUM_OF_REQUESTS = 0;
//    private Map<String, Integer> ipPoolWeighted = new HashMap<String, Integer>() {{
//        put("http://localhost:8081/reader/read", 1);
//        put("http://localhost:8082/reader/read", 2);
//        put("http://localhost:8083/reader/read", 3);
//    }};
//    //private LoadBalancer weightedRoundRobin = new WeightedRoundRobinLoadBalancer(ipPoolWeighted);
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        NUM_OF_REQUESTS++;
//        resp.setStatus(200);
//        HttpClientConnectionManager poolingConnManager
//                = new PoolingHttpClientConnectionManager();
//        CloseableHttpClient client
//                = HttpClients.custom().setConnectionManager(poolingConnManager)
//                .build();
//        client.execute(new HttpGet("/"));
//        assertTrue(((PoolingHttpClientConnectionManager) poolingConnManager).getTotalStats().getLeased() == 1);
//
//
//
//
//
//
//
//
//    }
//
//    private void simulateConcurrentClientRequest(LoadBalancer loadBalancer, int numOfCalls) {
//
//        IntStream
//                .range(0, numOfCalls)
//                .parallel()
//                .forEach(i ->
//
//                        loadBalancer.getIp()
//
//                );
//    }
//
//}
//
