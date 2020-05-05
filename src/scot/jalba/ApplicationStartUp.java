package scot.jalba;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationStartUp implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ApplicationStartUp.class.getName());

    public static final String TOPIC_REPOSITORY = "topic_repository";
    public static final String QIQO_CHAT_PAGE = "https://qiqochat.com/c/pmtyyRCS/";

    private Thread t = null;
    private ServletContext context;

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        context = contextEvent.getServletContext();
        context.setAttribute(TOPIC_REPOSITORY, new ArrayList<Map<String, Object>>());
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
        try {
            List<Map<String, Object>> topicsDto = (List<Map<String, Object>>) context.getAttribute(TOPIC_REPOSITORY);
            int topicCount = determineNextTopicId(topicsDto);
            Topics topics = new Topics(topicsDto);

            Document doc = Jsoup.connect(QIQO_CHAT_PAGE).get();
            Elements elementsByClass = doc.getElementsByClass("conversation-starter-label");

            for (Element topic : elementsByClass) {
                Topic t = createTopic(topicCount++, topic);
                topics.addOrUpdate(t);
            }
            topicsDto.clear();
            topicsDto.addAll(topics.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int determineNextTopicId(List<Map<String, Object>> topicsDto) {
        if (topicsDto.isEmpty()) {
            return 1;
        }
        return topicsDto.size() + 1;
    }

    private Topic createTopic(int topicCount, Element topic) {
        TextNode topicTitle = ((TextNode) topic.childNodes().get(0));

        Elements topicText = topic.parent().getElementsByTag("p");
        String desc = ((TextNode) topicText.get(0).childNodes().get(0)).getWholeText();

        Element authorImageUrl = topic.siblingElements().get(0).children().get(0);
        String authorName = authorImageUrl.attributes().get("alt");

        return new Topic(topicCount, topicTitle.getWholeText(), desc, authorName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        t.interrupt();
    }
}
