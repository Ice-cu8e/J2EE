package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IBlogDao {

	Blog getBlog(Integer id);
	List<Blog> getBlogs(Utilisateur utilisateur);
	Integer createBlog(Blog blog) throws SQLException;
	void updateBlog(Blog blog) throws SQLException;
	void deleteBlog(Blog blog) throws SQLException;
	void addReponse(Blog blog, Reponse reponse) throws SQLException;
	List<Blog> getAllBlogs();
	int getNbBlogs();
	
}
