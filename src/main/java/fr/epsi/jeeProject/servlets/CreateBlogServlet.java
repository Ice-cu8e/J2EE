package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.*;
import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = "/CreateBlog", name = "CreateBlogServlet")
public class CreateBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(CreateBlogServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBlogServlet() {
        super();
        Logger.info("Exécution de :"+ CreateBlogServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        int statut=Integer.parseInt(request.getParameter("statut"));
        IStatutDao daoStatut=new StatutDao();
        IBlogDao daoBlog=new BlogDao();
        Blog myBlog=new Blog();
        HttpSession session = request.getSession();
        Utilisateur myUser=(Utilisateur) session.getAttribute("myUser");
        myBlog.setTitre(titre);
        myBlog.setDescription(description);
        myBlog.setCreateur(myUser);
        myBlog.setStatut(daoStatut.getStatut(statut));
        try{
            daoBlog.createBlog(myBlog);
            Logger.info("Blog créé !");
        } catch (SQLException e) {
            Logger.error("Bug a la création d'un blog :"+e);
        }finally {
            RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/BlogPage.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
    }
}
