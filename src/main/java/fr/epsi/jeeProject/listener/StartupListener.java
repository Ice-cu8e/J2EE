package fr.epsi.jeeProject.listener;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.mockImpl.MockBlogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

@WebListener()
public class StartupListener implements ServletContextListener {

    private static final Logger Logger = LogManager.getLogger(StartupListener.class);

    // Public constructor is required by servlet spec
    public StartupListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        Logger.error("Démarrage de l'application");

        //Test de la connextion a hqsql
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
            Logger.error("Driver hsql et connexion ok");
        } catch (Exception e) {
            Logger.error("Erreur de driver", e);
        }

        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:mem://localhost:9003", "SA", "");
            Logger.error("Connexion ok");
        } catch (SQLException e) {
            Logger.error("Error while connecting", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        out.println("Création de la session");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
