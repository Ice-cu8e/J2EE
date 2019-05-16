package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fr.epsi.jeeProject.listener.StartupListener.connection;

public class ResponseDao {

    //@Override
    public Reponse getResponse(Integer id, Blog blog) {
        for (Reponse r: blog.getListOfReponses()) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    //@Override
    public List<Reponse> getResponsesBlog(Blog blog) {
        List<Reponse> status = new ArrayList<Reponse>();
        PreparedStatement p = null;
        try {
            p = connection.prepareStatement("SELECT * FROM blog_commentaires WHERE blog_id = " + blog.getId());
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                status.add(resultSetToStatut(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private Reponse resultSetToStatut(ResultSet resultSet) {
        Reponse reponse = new Reponse();
        UtilisateurDao u = new UtilisateurDao();
        BlogDao b = new BlogDao();

        try {
            reponse.setId(resultSet.getInt(1));
            reponse.setBlog(b.getBlog(resultSet.getInt(5)));
            reponse.setBlogger(u.getUtilisateur(resultSet.getString(3)));
            reponse.setCommentaire(resultSet.getString(2));
            reponse.setPublication(resultSet.getDate(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reponse;
    }
}
