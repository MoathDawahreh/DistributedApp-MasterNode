package ObserverPattern;

public class NewsReader implements Reader {
    private String news;

    public String getNews() {
        return news;
    }

    private void setNews(String news) {
        this.news = news;
        System.out.println("update the news: "+news);

    }

    @Override
    public void update(Object news) {
        this.setNews((String) news);
    }
}