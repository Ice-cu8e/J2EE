package fr.epsi.jeeProject;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.UtilisateurDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class CreateUser {

    @Test
    public void createSameUser() throws SQLException {
        IUtilisateurDao userDao=new UtilisateurDao();
        Utilisateur user1=new Utilisateur();
        Utilisateur user2=new Utilisateur();
        user1.setEmail("test@email.com");
        user1.setAdmin(false);
        user1.setNom("1er utilisateur");
        userDao.createUtilisateur(user1);
        user2.setEmail("test@email.com");
        user2.setAdmin(false);
        user2.setNom("2eme utilisateur");
        userDao.createUtilisateur(user2);
        Assert.assertEquals(userDao.getUtilisateur("test@email.com").getNom(),"1er utilisateur");
        userDao.deleteUtilisateurByEmail("test@email.com");
    }
}