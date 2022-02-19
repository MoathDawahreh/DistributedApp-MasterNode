package Cache;

import Models.Company;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface Cache {
    public boolean get(String key);

    public void refer(String key);

    public void display();

    public void put(String key);

}