package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatutDao implements IStatutDao {

    private static List<Statut> listOfStatuts;

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
        if (listOfStatuts == null) {
            listOfStatuts = new ArrayList<Statut>();
            Statut statut = new Statut();
            statut.setId(1);
            statut.setDescription("Temporaire");
            listOfStatuts.add(statut);

            statut = new Statut();
            statut.setId(2);
            statut.setDescription("Publi�");
            listOfStatuts.add(statut);

            statut = new Statut();
            statut.setId(3);
            statut.setDescription("Archiv�");
            listOfStatuts.add(statut);

            statut = new Statut();
            statut.setId(4);
            statut.setDescription("Annul�");
            listOfStatuts.add(statut);

        }
        return listOfStatuts;
    }
}
