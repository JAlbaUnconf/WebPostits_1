package scot.jalba;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PostitsServlet extends javax.servlet.http.HttpServlet {

    public static synchronized List<List<String>> getNames() {
        if (names.isEmpty()) {
            refresh();
        }
        System.out.println(LocalTime.now() + " getNames() called");
        return names;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException { }

    private static List<List<String>> names = new ArrayList<>();

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        refresh();
    }

    private static synchronized void refresh() {
        names = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://qiqochat.com/c/pmtyyRCS/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elementsByClass = doc.getElementsByClass("conversation-starter-label");
        for (Element byClass : elementsByClass) {
            List<String> conversationStarter = new ArrayList<>();
            names.add(conversationStarter);
            TextNode node = ((TextNode) byClass.childNodes().get(0));
            conversationStarter.add(node.getWholeText());
            Element img = byClass.siblingElements().get(0).children().get(0);
            System.out.println(img.attributes().get("src"));
            String alt = img.attributes().get("alt");
            conversationStarter.add(alt);
            System.out.println("refresh: name: " + alt);
            Elements p = byClass.parent().getElementsByTag("p");
            conversationStarter.add(((TextNode)p.get(0).childNodes().get(0)).getWholeText());
            System.out.println("-------------");
        }
    }
}
