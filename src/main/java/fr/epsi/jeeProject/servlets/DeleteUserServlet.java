package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Utilisateur;
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
@WebServlet(value = "/DeleteUser", name = "DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(DeleteUserServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        Logger.info("Exécution de :"+ DeleteUserServlet.class);
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
        String email = request.getParameter("nom");
        IUtilisateurDao userDao=new UtilisateurDao();
        try {
            userDao.deleteUtilisateurByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("usercreated", "Utilisateur " + email + " supprimé !");
            Logger.info("Utilisateur " + email + " supprimé !");

        } catch (SQLException e) {
            Logger.error("Erreur lors de la suppression de l'utilisateur "+email);
        }
        RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/BlogPage.jsp");
        RequetsDispatcherObj.forward(request, response);
    }

}
