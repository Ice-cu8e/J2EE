package fr.epsi.jeeProject.jmx;

import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NbBlogs implements NbBlogsMBean {
    private static Logger logger = LogManager.getLogger(NbBlogs.class);
    private IBlogDao blogDao=new BlogDao();
    public int getNbBlogs(){
        int vretour=blogDao.getNbBlogs();
        return vretour;
    }
}
