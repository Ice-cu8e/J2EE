package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Statut;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fr.epsi.jeeProject.listener.StartupListener.connection;

public class StatutDao implements IStatutDao {
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogDao.class);


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
        List<Statut> status = new ArrayList<Statut>();
        PreparedStatement p = null;
        try {
            p = connection.prepareStatement("SELECT * FROM statut");
            ResultSet resultSet = p.executeQuery();
            while (resultSet.next()) {
                status.add(resultSetToStatut(resultSet));
            }
            Logger.debug("Requete getListOfStatuts OK");
        } catch (SQLException e) {
            Logger.debug("Requete getListOfStatuts KO"+e);
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
