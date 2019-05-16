package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IUtilisateurDao {

	Utilisateur getUtilisateur(String email);
	void createUtilisateur(Utilisateur utilisateur) throws SQLException;
	void updateUtilisateur(Utilisateur utilisateur) throws SQLException;
	void deleteUtilisateur(Utilisateur utilisateur) throws SQLException;
    void deleteUtilisateurByEmail(String email) throws SQLException;
    List<Utilisateur> getListOfUtilisateur() throws SQLException;
	public int getNbUtilisateurs() ;
}
