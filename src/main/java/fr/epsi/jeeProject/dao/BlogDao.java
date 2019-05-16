package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IStatutDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;

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
    private IUtilisateurDao utilisateurDao = new UtilisateurDao();
    private IStatutDao statutDao = new StatutDao();
    private Connection c = getConnection();

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
        List<Blog> blogs = new ArrayList<Blog>();
        PreparedStatement p = null;
        try {
            p = c.prepareStatement("SELECT * FROM BLOG");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                blogs.add(resultSetToBlog(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    private Blog resultSetToBlog(ResultSet resultSet) {
        Blog b = new Blog();
        try {
            b.setId(resultSet.getInt(1));
            b.setDateModification(resultSet.getDate(6));
            b.setDescription(resultSet.getString(4));
            b.setTitre(resultSet.getString(2));
            b.setCreateur(null);
            b.setDateCreation(resultSet.getDate(5));
            b.setStatut(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Blog> getBlogs(Utilisateur utilisateur) {
        List<Blog> myBlogs = new ArrayList<Blog>();
        for (Blog b : getBlogs()) {
            if (b.getCreateur().getEmail().equals(utilisateur.getEmail())) {
                myBlogs.add(b);
            } else if (b.getStatut().getId().intValue() == IStatutDao.PUBLIE) {
                myBlogs.add(b);
            }
        }
        return myBlogs;
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
    public void addReponse(Blog blog, Reponse reponse) throws SQLException {
        for (Blog b : getBlogs()) {
            if (b.getId().intValue() == blog.getId().intValue()) {
                if (b.getListOfReponses() == null) {
                    b.setListOfReponses(new ArrayList<Reponse>());
                }
                b.getListOfReponses().add(reponse);
                return;
            }
        }
    }

    private List<Blog> getBlogs() {
        return listOfBlogs;
    }

}
