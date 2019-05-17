package fr.epsi.jeeProject;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.UtilisateurDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DeleteOtherBlog {
    @Test
    public void deleteOtherBlog() throws SQLException {
        IBlogDao blogDao=new BlogDao();
        Utilisateur user1=new Utilisateur();
        Utilisateur user2=new Utilisateur();
        user1.setEmail("yann.cloarec@epsi.fr");
        user2.setEmail("user2@email.com");
        Statut statut=new Statut();
        statut.setId(1);
        Blog blog=new Blog();
        blog.setId(9999);
        blog.setCreateur(user1);
        blog.setTitre("TITRE TEST");
        blog.setStatut(statut);
        blogDao.createBlogWithId(blog);
        blogDao.deleteBlogFromId(9999,user2);
        Assert.assertEquals(blogDao.getBlog(9999).getTitre(),"TITRE TEST");
        blogDao.deleteBlogFromId(9999,user1);

    }
}
