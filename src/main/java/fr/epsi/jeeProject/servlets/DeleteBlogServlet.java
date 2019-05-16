package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.UtilisateurDao;
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
@WebServlet(value = "/DeleteBlog", name = "DeleteUserServlet")
public class DeleteBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(DeleteBlogServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBlogServlet() {
        super();
        Logger.info("Exécution de :"+ DeleteBlogServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("ID"));
        IBlogDao blogDao=new BlogDao();
        try {
            blogDao.deleteBlogFromId(ID);
            Logger.info("Blog n°"+ID+" supprimé");
        } catch (SQLException e) {
            Logger.error("Erreur suppression blog n°"+ID+" : "+e);
        }
        RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/BlogPage.jsp");
        RequetsDispatcherObj.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
