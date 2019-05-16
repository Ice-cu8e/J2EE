package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Statut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fr.epsi.jeeProject.listener.StartupListener.connection;

public class StatutDao implements IStatutDao {

    @Override
    public Statut getStatut(Integer id) {
        for (Statut s: getListOfStatuts()) {
            if (s.getId().intValue() == id.intValue()) {
                return s;
            }
        }
        return null;
    }
    @Override
    public List<Statut> getListOfStatuts() {
        return getPrivateListOfStatuts();
    }

    private List<Statut> getPrivateListOfStatuts() {
        List<Statut> status = new ArrayList<Statut>();
        PreparedStatement p = null;
        try {
            p = connection.prepareStatement("SELECT * FROM statut");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                status.add(resultSetToStatut(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private Statut resultSetToStatut(ResultSet resultSet) {
        Statut statut = new Statut();

        try {
            statut.setId(resultSet.getInt(1));
            statut.setDescription(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statut;
    }
}
