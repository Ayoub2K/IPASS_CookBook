package CookBook.listeners;

import CookBook.model.Book;
import CookBook.persistence.PersistenceManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        try {
            PersistenceManager.loadBookFromAzure();
            System.out.println("Book is geladen van Azure.");
        } catch (Exception e) {
            System.out.println("Error met laden van Book: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        try {
            PersistenceManager.saveBookToAzure();
            System.out.println("Book is opgeslagen in Azure.");
        } catch (Exception e) {
            System.out.println("Error met het opslaan van Book: " + e.getMessage());
        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
