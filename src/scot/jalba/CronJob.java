package scot.jalba;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CronJob implements ServletContextListener {

    private Thread t = null;
    private ServletContext context;

    public void contextInitialized(ServletContextEvent contextEvent) {
        t = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Thread running every 10 seconds");
                    Thread.sleep(10000);
                    PostitsServlet.refresh();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        // keep because thats how we might be able to do dependency injection
        context = contextEvent.getServletContext();
        //context.setAttribute("TEST", "TEST_VALUE");
    }

    public void contextDestroyed(ServletContextEvent contextEvent) {
        t.interrupt();
    }
}
