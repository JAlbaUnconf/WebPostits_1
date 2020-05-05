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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostitsServlet extends javax.servlet.http.HttpServlet {

    private static List<Map<String, String>> topics;
    private static final String QIQOCHAT_URL = "https://jalba.qiqochat.com";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    /**
     * currently any GET request refreshes the servlet's data from the
     * conversations page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        refresh();
    }

    private static synchronized void refresh() {
        topics = new ArrayList<>();
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
            String imgSrc = authorImageUrl.attributes().get("src");
            if (imgSrc.startsWith("/")) imgSrc = QIQOCHAT_URL+imgSrc;
            String authorName = authorImageUrl.attributes().get("alt");
            topicElements.put("authorImg", imgSrc);
            topicElements.put("author", authorName);
            topics.add(topicElements);
            System.out.println(topics);
        }
    }

    public static synchronized List<Map<String, String>> getTopicData() {
        if (topics == null) {
            refresh();
        }
        return topics;
    }
}
