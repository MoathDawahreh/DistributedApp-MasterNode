package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class MasterUpdate {
    private String news;
    private List<Reader> readers = new ArrayList<>();

    public void addObserver(Reader reader) {
        this.readers.add(reader);
    }

    public void removeObserver(Reader reader) {
        this.readers.remove(reader);
    }

    public void setNews(String news) {
        this.news = news;
        for (Reader reader : this.readers) {
            reader.update(this.news);
         }
    }
}