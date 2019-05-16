package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IStatutDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static fr.epsi.jeeProject.listener.StartupListener.c;
import static fr.epsi.jeeProject.server.PostgresServer.getConnection;

public class BlogDao implements IBlogDao {
    private static List<Blog> listOfBlogs;
    //private IUtilisateurDao utilisateurDao = new UtilisateurDao();
    //private IStatutDao statutDao = new StatutDao();
    private Connection c = getConnection();
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogDao.class);

    @Override
    public Blog getBlog(Integer id) {
        for (Blog b : getBlogs()) {
            if (b.getId().intValue() == id.intValue()) {
                return b;
            }
        }
        return null;
    }
    @Override
    public List<Blog> getAllBlogs(){
        Logger.debug("Début de la requete getAllBlogs");
        List<Blog> blogs = new ArrayList<Blog>();
        PreparedStatement p = null;
        try {

            p = c.prepareStatement("SELECT * FROM BLOG");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                blogs.add(resultSetToBlog(resultSet));
            }
            Logger.debug("Requete getAllBlogs OK");
        } catch (SQLException e) {
            Logger.debug("Requete getAllBlogs KO :"+ e);
        }
        return blogs;
    }
    public int getNbBlogs(){
        return getAllBlogs().size();
    }
    private Blog resultSetToBlog(ResultSet resultSet) {
        Blog b = new Blog();
        StatutDao s = new StatutDao();
        UtilisateurDao u = new UtilisateurDao();

        try {
            b.setId(resultSet.getInt(1));
            b.setDateModification(resultSet.getDate(6));
            b.setDescription(resultSet.getString(3));
            b.setTitre(resultSet.getString(2));
            b.setCreateur(u.getUtilisateur(resultSet.getString(4)));
            b.setDateCreation(resultSet.getDate(5));
            b.setStatut(s.getStatut(resultSet.getInt(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }


    @Override
    public Integer createBlog(Blog blog) throws SQLException {
        int max = 0;
        for (Blog b : getBlogs()) {
            if (b.getId().intValue() > max) {
                max = b.getId();
            }
        }
        max+=1;
        blog.setId(max);
        return max;
    }

    @Override
    public void updateBlog(Blog blog) throws SQLException {
        for (Blog b : getBlogs()) {
            if (b.getId().intValue() == blog.getId().intValue()) {
                b.setTitre(blog.getTitre());
                b.setDescription(blog.getDescription());
                blog.setDateModification(new java.sql.Date(new Date().getTime()));
            }
        }
    }

    @Override
    public void deleteBlog(Blog blog) throws SQLException {
        for (Blog b : getBlogs()) {
            if (b.getId().intValue() == blog.getId().intValue()) {
                getBlogs().remove(b);
                return;
            }
        }
    }

    @Override
    public void addReponse(Blog blog, Reponse reponse) {
        if (blog.getListOfReponses() == null) {
            blog.setListOfReponses(new ArrayList<Reponse>());
        }
        blog.getListOfReponses().add(reponse);
    }

    @Override
    public List<Reponse> getResponses(Blog blog) {
        ResponseDao r = new ResponseDao();
        List<Reponse> reponses = r.getResponsesBlog(blog);
        return reponses;
    }

    private List<Blog> getBlogs() {
        return listOfBlogs;
    }

}
