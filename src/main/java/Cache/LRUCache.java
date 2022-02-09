package Cache;

import MasterData.CompanyDbDao;
import Models.Company;
//import org.json.simple.ConcurrentHashMap<String, Company>;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class LRUCache {

    Set<ConcurrentHashMap<String, Company>> cache;
    private final int capacity = 5;

    public LRUCache() {
        this.cache = new LinkedHashSet<ConcurrentHashMap<String, Company>>(capacity);
    }

    // This function returns false if key is not
    // present in cache. Else it moves the key to
    // front by first removing it and then adding
    // it, and returns true.
    public boolean get(ConcurrentHashMap<String, Company> key) {
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        return true;
    }

    /* Refers key x with in the LRU cache */
    public void refer(ConcurrentHashMap<String, Company> key) {
        if (get(key) == false)
            put(key);
    }

    // displays contents of cache in Reverse Order
    public void display() {
        LinkedList<ConcurrentHashMap<String, Company>> list = new LinkedList<ConcurrentHashMap<String, Company>>(cache);

        // The descendingIterator() method of java.util.LinkedList
        // class is used to return an iterator over the elements
        // in this LinkedList in reverse sequential order
        Iterator<ConcurrentHashMap<String, Company>> itr = list.descendingIterator();

        while (itr.hasNext())
            System.out.print(itr.next() + " *************  ");
    }

    public void put(ConcurrentHashMap<String, Company> key) {

        if (cache.size() == capacity) {
            ConcurrentHashMap<String, Company> firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }

        cache.add(key);
    }

    public static void main(String[] args) {
        CompanyDbDao c = new CompanyDbDao();
        ConcurrentHashMap<String, Company> concurrentHashMaphashMap = c.concurrentHashMaphashMap;
        Company company = new Company("maktoob", "5", "website");

        c.getCompanies();
        LRUCache cache = new LRUCache();
        cache.refer(concurrentHashMaphashMap);
        concurrentHashMaphashMap.put("maktoobz",company);
         cache.refer(concurrentHashMaphashMap);
//        cache.refer(3);
//        cache.refer(1);
//        cache.refer(4);
//        cache.refer(5);
        cache.display();
        System.out.println(cache.get(concurrentHashMaphashMap));

        //System.out.println(concurrentHashMaphashMap.get("Atypon").getCompanyName());
    }
}
