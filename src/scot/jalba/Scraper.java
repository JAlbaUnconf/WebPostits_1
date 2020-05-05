package scot.jalba;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Scraper implements ServletContextListener {

    private static final Logger log = Logger.getLogger( Scraper.class.getName() );

    public static final String TOPIC_REPOSITORY = "topic_repository";

    private Thread t = null;
    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        context = contextEvent.getServletContext();
        context.setAttribute(TOPIC_REPOSITORY, new ArrayList<Map<String, String>>());
        t = new Thread(() -> {
            try {
                while (true) {

                    long time = System.currentTimeMillis();
                    try {
                        log.info("start scraping...");
                        scrapeQiqochat();
                    } finally {
                        time = System.currentTimeMillis() - time;
                        log.info("scraped in " + time + "ms");
                    }
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

    }

    private void scrapeQiqochat() {
        List<Map<String, String>> topics = (List<Map<String, String>>) context.getAttribute(TOPIC_REPOSITORY);
        topics.clear();
        Document doc = null;
        try {
            //Qiqochat conversations page for pilot event
            doc = Jsoup.connect("https://qiqochat.com/c/pmtyyRCS/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elementsByClass = doc.getElementsByClass("conversation-starter-label");
        int topicCount = 1;
        for (Element topic : elementsByClass) {
            Map<String, String> topicElements = new HashMap<>();

            TextNode topicTitle = ((TextNode) topic.childNodes().get(0));
            topicElements.put("title", topicCount++ + " " + topicTitle.getWholeText());

            Elements topicText = topic.parent().getElementsByTag("p");
            topicElements.put("text", ((TextNode) topicText.get(0).childNodes().get(0)).getWholeText());

            Element authorImageUrl = topic.siblingElements().get(0).children().get(0);
            String authorName = authorImageUrl.attributes().get("alt");
            topicElements.put("author", authorName);

            topics.add(topicElements);
            //log.info(topics);
        }
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
        t.interrupt();
    }
}
