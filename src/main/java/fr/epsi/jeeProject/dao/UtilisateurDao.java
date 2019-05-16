package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fr.epsi.jeeProject.listener.StartupListener.connection;

public class UtilisateurDao implements IUtilisateurDao {

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
        getListOfUtilisateur().add(utilisateur);
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
        for (Utilisateur u : getListOfUtilisateur()) {
            if (u.getEmail().equals(utilisateur.getEmail())) {
                getListOfUtilisateur().remove(u);
                return;
            }
        }
    }

    private List<Utilisateur> getListOfUtilisateur() {
        List<Utilisateur> users = new ArrayList<Utilisateur>();
        PreparedStatement p = null;
        try {
            p = connection.prepareStatement("SELECT * FROM \"user\"");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                users.add(resultsetToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
