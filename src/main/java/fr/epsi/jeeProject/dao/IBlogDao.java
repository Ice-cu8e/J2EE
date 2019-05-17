package fr.epsi.jeeProject.dao;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IBlogDao {

	Blog getBlog(Integer id);
	void createBlog(Blog blog) throws SQLException;
	void createBlogWithId(Blog blog) throws SQLException;
	void updateStatut(Blog blog)throws SQLException;
	void updateBlog(Blog blog) throws SQLException;
	void deleteBlog(Blog blog) throws SQLException;
	void deleteBlogFromId(int id,Utilisateur user) throws SQLException;
	void addReponse(Reponse reponse) throws SQLException;
	List<Reponse> getResponses(Blog blog);
	List<Blog> getAllBlogs();
	int getNbBlogs();
	List<Blog> getAllVisibleBlogs(Utilisateur user);
	
}
