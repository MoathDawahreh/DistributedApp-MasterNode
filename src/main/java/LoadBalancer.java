import java.util.*;

public abstract class LoadBalancer {
    final List <String> ipList;

    public LoadBalancer(List<String> ipList) {
        this.ipList = Collections.unmodifiableList(ipList);
    }

    abstract String getIp();
}