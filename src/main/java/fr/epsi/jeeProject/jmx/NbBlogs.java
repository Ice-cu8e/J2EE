package fr.epsi.jeeProject.jmx;

import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class NbBlogs implements NbBlogsMBean {
    private static Logger logger = LogManager.getLogger(NbBlogs.class);
    private IBlogDao blogDao=new BlogDao();
    public int getNbBlogs(){
        return 5;
    }
}
