package LoadBalancer;

import LoadBalancer.LoadBalancer;

import java.util.*;
import java.util.stream.Collectors;

import java.util.concurrent.locks.ReentrantLock;

public class WeightedRoundRobinLoadBalancer extends LoadBalancer {

    private int counter = 0;
    private final ReentrantLock lock;
    //   private  Map<String, Integer> ipPoolWeighted = new HashMap<>();

    @Override
    public String getIp() {
        lock.lock();
        try {
            String ip = ipList.get(counter);
            counter += 1;
            if (counter == ipList.size()) {
                counter = 0;
            }
            return ip;
        } finally {
            lock.unlock();
        }
    }

    public WeightedRoundRobinLoadBalancer(Map<String, Integer> ipMap) {
        super(
                ipMap.keySet()
                        .stream()
                        .map(ip -> {
                            List<String> tempList = new LinkedList<>();
                            for (int i = 0; i < ipMap.get(ip); i++) {
                                tempList.add(ip);
                            }
                            return tempList;
                        })
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );
        lock = new ReentrantLock();
    }
}