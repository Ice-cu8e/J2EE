package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Utilisateur;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static fr.epsi.jeeProject.server.PostgresServer.getConnection;

public class UtilisateurDao implements IUtilisateurDao {
    private Connection c = getConnection();
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogDao.class);

    @Override
    public Utilisateur getUtilisateur(String email) {
        for (Utilisateur u : getListOfUtilisateur()) {
            if (u.getEmail().equals(email))
                return u;
        }
        return null;
    }

    @Override
    public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
        try {
            PreparedStatement insert = c.prepareStatement("INSERT INTO jee.public.user VALUES(?,?,?,?,?)");
            insert.setString(1, utilisateur.getEmail());
            insert.setString(2, utilisateur.getNom());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            insert.setDate(3, date);
            insert.setString(4, utilisateur.getPassord());
            insert.setBoolean(5, utilisateur.getAdmin());
            insert.executeUpdate();
            insert.close();
        }catch (SQLException e){

        }
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
        for (Utilisateur u : getListOfUtilisateur()) {
            if (u.getEmail().equals(utilisateur.getEmail())) {
                u.setAdmin(utilisateur.getAdmin());
                u.setNom(utilisateur.getNom());
            }
        }
    }

    @Override
    public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
        PreparedStatement p = null;
        try {
            if (!utilisateur.getAdmin()) {
                Logger.debug("Début de la requete deleteUtilisateur");
                p = c.prepareStatement("DELETE from \"user\" where email=?");
                p.setString(1, utilisateur.getEmail());
                ResultSet resultSet = p.executeQuery();
                Logger.debug("Requete deleteUtilisateur OK");
            }
        }catch(SQLException e){
                Logger.debug("Requete deleteUtilisateur KO" + e);
        }
    }
    @Override
    public void deleteUtilisateurByEmail(String email) throws SQLException {
        Utilisateur myUser=getUtilisateur(email);
        deleteUtilisateur(myUser);
    }
@Override
    public List<Utilisateur> getListOfUtilisateur() {
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        PreparedStatement p = null;
        try {
            Logger.debug("Début de la requete getListOfUtilisateur");
            p = c.prepareStatement("SELECT * FROM \"user\"");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                users.add(resultsetToUser(resultSet));
            }
            Logger.debug("Requete getListOfUtilisateur OK");
        } catch (SQLException e) {
            Logger.debug("Requete getListOfUtilisateur KO"+e);
        }
        return users;
    }

    private Utilisateur resultsetToUser(ResultSet resultSet) {
        Utilisateur u = new Utilisateur();
        try {
            u.setAdmin(resultSet.getBoolean(5));
            u.setNom(resultSet.getString(2));
            u.setDateCreation(resultSet.getDate(3));
            u.setEmail(resultSet.getString(1));
            u.setPassord(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
