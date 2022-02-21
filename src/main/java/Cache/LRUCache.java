package Cache;

import DatabaseDAO.CompanyDbDao;
import Models.Company;
import org.json.simple.JSONArray;
//import org.json.simple.ConcurrentHashMap<String, Company>;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache implements Cache {

//    Set<String> cache;
    private final int capacity = 5;
        Set<String> cache= new LinkedHashSet<>(capacity);

    public static String cachedData="";

//    public LRUCache() {
//        this.cache = new LinkedHashSet<>(capacity);
//    }

    // This function returns false if key is not
    // present in cache. Else it moves the key to
    // front by first removing it and then adding
    // it, and returns true.
    public boolean get(String key) {
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        return true;
    }

    /* Refers key x with in the LRU cache */
    public void refer(String key) {
        if (!get(key))
            put(key);
    }

    // display contents of cache in Reverse Order
    public void display() {
        LinkedList<String> list = new LinkedList<>(cache);

        // The descendingIterator() method of java.util.LinkedList
        // class is used to return an iterator over the elements
        // in this LinkedList in reverse sequential order
        Iterator<String> itr = list.descendingIterator();

        while (itr.hasNext())
            System.out.print(itr.next() );
    }

    public void put(String key) {

        if (cache.size() == capacity) {
            String firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }

        cache.add(key);
    }

}
