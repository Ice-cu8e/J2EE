package fr.epsi.jeeProject.listener;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.jmx.LevelChange;
import fr.epsi.jeeProject.jmx.NbBlogs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static fr.epsi.jeeProject.server.PostgresServer.getConnection;
import static java.lang.System.out;

@WebListener()
public class StartupListener implements ServletContextListener {
    public static Connection c;
    private static final Logger Logger = LogManager.getLogger(StartupListener.class);

    // this will force a reconfiguration

    // Public constructor is required by servlet spec
    public StartupListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = null;
        ObjectName name2 = null;

        Logger.trace("msg de trace");
        Logger.debug("msg de debogage");
        Logger.info("msg d'information");
        Logger.warn("msg d'avertissement");
        Logger.error("msg d'erreur");
        Logger.fatal("msg d'erreur fatale");
        Logger.info("Démarrage de l'application");

        //Test de la connextion a hqsql
        try {
            Class.forName("org.postgresql.Driver" );
            Logger.info("Driver postgresql ok");
        } catch (Exception e) {
            Logger.error("Erreur de driver", e);
        }

        Connection c = getConnection();
        Logger.info("Connexion ok");
        IBlogDao blogDao=new BlogDao();
        Logger.error("Nombre de blogs : "+blogDao.getNbBlogs());
        Logger.error("Nombre d'utilisateurs : ");

        try {
            name = new ObjectName("fr.epsi.jeeProject:type=LevelChangeMBean");
            LevelChange mbean = new LevelChange();

            mbs.registerMBean(mbean, name);
            name2 = new ObjectName("fr.epsi.jeeProject:type=NbBlogsMBean");
            NbBlogs mbean2 = new NbBlogs();

            mbs.registerMBean(mbean2, name2);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
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
