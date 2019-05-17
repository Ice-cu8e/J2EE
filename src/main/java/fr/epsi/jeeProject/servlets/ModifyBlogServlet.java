package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IStatutDao;
import fr.epsi.jeeProject.dao.StatutDao;
import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = "/ModifyBlog", name = "ModifyBlogServlet")
public class ModifyBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(ModifyBlogServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyBlogServlet() {
        super();
        Logger.info("Exécution de :"+ ModifyBlogServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = null;
        try {
            referer = new URI(request.getHeader("referer")).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] uriNames = referer.split("/");
        String jspPageName = uriNames[uriNames.length-1];

        IBlogDao blogDao=new BlogDao();
        IStatutDao statutDao=new StatutDao();
        int idBlog=Integer.parseInt(request.getParameter("ID"));
        int id=Integer.parseInt(request.getParameter("type"));
        Blog myBlog =blogDao.getBlog(idBlog);
        Statut myStatut= statutDao.getStatut(id);
        myBlog.setStatut(myStatut);
        try {
            blogDao.updateStatut(myBlog);
            Logger.info("Blog N°"+myBlog.getId()+" updated");
        } catch (SQLException e) {
            Logger.error("Error update blog "+ e);
        }finally {
            RequestDispatcher RequetsDispatcherObj = null;
            String url = "";
            if (jspPageName.equals("Blog")) {
                url = "/Blog?ID=" + idBlog;
            } else if(jspPageName.equals("Blogs")) {
                url = "/Blogs";
            }
            response.sendRedirect(request.getContextPath() + url);
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
