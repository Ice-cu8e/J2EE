package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Statut;

import java.util.List;

public interface IStatutDao {

	public final static Integer TEMPORAIRE = 1;
	public final static Integer PUBLIE = 2;
	public final static Integer ARCHIVE = 3;
	public final static Integer ANNULE = 3;
	
	Statut getStatut(Integer id);
	List<Statut> getListOfStatuts();
}
