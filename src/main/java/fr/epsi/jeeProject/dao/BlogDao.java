package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IStatutDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.server.PostgresServer;
import org.apache.logging.log4j.LogManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static fr.epsi.jeeProject.listener.StartupListener.connection;

public class BlogDao implements IBlogDao {
    private static List<Blog> blogList = new ArrayList<Blog>();
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogDao.class);

    @Override
    public Blog getBlog(Integer id) {
        for (Blog b : blogList) {
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
            p = connection.prepareStatement("SELECT * FROM BLOG");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                blogs.add(resultSetToBlog(resultSet));
            }
            Logger.debug("Requete getAllBlogs OK");
        } catch (SQLException e) {
            Logger.debug("Requete getAllBlogs KO :"+ e);
        }
        blogList = blogs;
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
    public void createBlog(Blog blog) throws SQLException {
        try {
            connection = PostgresServer.getConnection();  // INSTANCE POUR TEST
            PreparedStatement insert = connection.prepareStatement("INSERT INTO public.blog (\"TITRE\",\"DESCRIPTION\",\"EMAIL\",\"DATE_CREATION\",\"DATE_MODIFICATION\",\"STATUT\") VALUES(?,?,?,?,?,?)");
            insert.setString(1, blog.getTitre());
            insert.setString(2,blog.getDescription());
            insert.setString(3, blog.getCreateur().getEmail());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            insert.setDate(4, date);
            insert.setDate(5, date);
            insert.setInt(6,blog.getStatut().getId());
            insert.executeUpdate();
            insert.close();
        }catch (SQLException e){

        }
    }
    @Override
    public void createBlogWithId(Blog blog) throws SQLException {
        try {
            connection = PostgresServer.getConnection();  // INSTANCE POUR TEST
            PreparedStatement insert = connection.prepareStatement("INSERT INTO public.blog (\"TITRE\",\"DESCRIPTION\",\"EMAIL\",\"DATE_CREATION\",\"DATE_MODIFICATION\",\"STATUT\",\"id\") VALUES(?,?,?,?,?,?,?)");
            insert.setInt(7,blog.getId());
            insert.setString(1, blog.getTitre());
            insert.setString(2, blog.getDescription());
            insert.setString(3, blog.getCreateur().getEmail());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            insert.setDate(4, date);
            insert.setDate(5, date);
            insert.setInt(6, blog.getStatut().getId());
            insert.executeUpdate();
            insert.close();
        } catch (SQLException e) {

        }
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
        PreparedStatement p = null;
        try {
                Logger.debug("Début de la requete deleteBlog");
                p = connection.prepareStatement("DELETE from \"blog\" where id=?");
                p.setInt(1, blog.getId());
                ResultSet resultSet = p.executeQuery();
                Logger.debug("Requete deleteBlog OK");
        }catch(SQLException e){
            Logger.error("Requete deleteBlog KO" + e);
        }
    }
    @Override
    public void deleteBlogFromId(int id,Utilisateur user) throws SQLException {
        Blog bllog=getBlog(id);
        if (user.getEmail().equals(getBlog(id).getCreateur().getEmail())){
            deleteBlog(getBlog(id));
        }
    }
    @Override
    public void addReponse(Reponse reponse) {
        PreparedStatement p = null;
        try {
            p = connection.prepareStatement("INSERT INTO blog_commentaires (commentaire, email, date_creation, blog_id) VALUES (?, ?, ?, ?)");
            p.setString(1,reponse.getCommentaire());
            p.setString(2,reponse.getBlogger().getEmail());
            p.setDate(3,reponse.getPublication());
            p.setInt(4,reponse.getBlog().getId());

            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reponse> getResponses(Blog blog) {
        ResponseDao r = new ResponseDao();
        List<Reponse> reponses = r.getResponsesBlog(blog);
        return reponses;
    }

    private List<Blog> getBlogs() {
        return blogList;
    }

    public static List<Blog> getBlogList() {
        return blogList;
    }
}
