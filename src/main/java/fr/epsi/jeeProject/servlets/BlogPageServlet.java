package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.UtilisateurDao;
import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static fr.epsi.jeeProject.listener.StartupListener.connection;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet(value = "/Blogs", name = "BlogPageServlet")
public class BlogPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogPageServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogPageServlet() {
        super();
        Logger.info("Exécution de :"+ BlogPageServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session=request.getSession();
        if(!(session.getAttribute("myUser")==null)){
            String ID=request.getParameter( "ID" );
            request.setAttribute("ID",ID);
            RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/BlogPage.jsp");
            RequetsDispatcherObj.forward(request, response);
        }else {
            RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Utilisateur myUser = new Utilisateur();
        IUtilisateurDao daoUser=new UtilisateurDao();
        try {
            List<Utilisateur> utilisateurList = daoUser.getListOfUtilisateur();
            for(Utilisateur aUser : utilisateurList) {
                if (aUser.getEmail().equals(login) && aUser.getPassord().equals(password)) {
                    myUser.setEmail(aUser.getEmail());
                    myUser.setNom(aUser.getNom());
                    myUser.setDateCreation(aUser.getDateCreation());
                    myUser.setPassord(aUser.getPassord());
                    myUser.setAdmin(aUser.getAdmin());
                }
            }
        } catch (SQLException e) {
            Logger.error(e);
        }
        if (!(myUser.getEmail() == null)) {
            session.setAttribute("myUser", myUser);
            RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/BlogPage.jsp");
            RequetsDispatcherObj.forward(request, response);
        } else {
            Logger.error("Connexion refusé de : "+login);
            session.setAttribute("connexionrefused","Connexion refusé, identifiant ou mot de passe invalide");
            RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
    }
}
