package ObserverPattern;

import static org.junit.Assert.assertEquals;

public class test {
    public static void main(String[] args) {



        MasterUpdate observable = new MasterUpdate();
        NewsReader observer = new NewsReader();

        observable.addObserver(observer);
        observable.setNews("news");
        assertEquals(observer.getNews(), "news");
 //        observable.setNews("m");'
//        System.out.println(observer.getNews());
        observable.setNews("m");

        NewsReader t = new NewsReader();
        observable.addObserver(t);

//        observable.addObserver(t);






    }
}
