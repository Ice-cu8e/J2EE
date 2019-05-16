package fr.epsi.jeeProject;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.UtilisateurDao;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class DeleteAdmin {
    @Test
    public void deteleAdmin() throws SQLException {
        IUtilisateurDao userDao=new UtilisateurDao();
        Utilisateur admin=new Utilisateur();
        admin.setEmail("testAdmin@email.com");
        admin.setAdmin(true);
        admin.setNom("Admin");
        userDao.createUtilisateur(admin);
        userDao.deleteUtilisateurByEmail("testAdmin@email.com");
        Assert.assertEquals(userDao.getUtilisateur("testAdmin@email.com").getEmail(),admin.getEmail());
    }
}
