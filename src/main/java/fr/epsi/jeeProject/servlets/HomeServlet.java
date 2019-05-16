package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.beans.Utilisateur;
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


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(value = "/Home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(HomeServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();

        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub**
        RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
        RequetsDispatcherObj.forward(request, response);
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
            Connection connection = StartupListener.c;
            try {
                PreparedStatement prep = connection.prepareStatement("SELECT * FROM \"user\" Where email=? and password=?");
                prep.setString(1, login);
                prep.setString(2, password);
                ResultSet resultSet = prep.executeQuery();
                while (resultSet.next()) {
                    myUser.setEmail(resultSet.getString(1));
                    myUser.setNom(resultSet.getString(2));
                    myUser.setDateCreation(resultSet.getDate(3));
                    myUser.setPassord(resultSet.getString(4));
                    myUser.setAdmin(resultSet.getBoolean(5));
                }
            } catch (SQLException e) {
                Logger.error(e);
            }
            if (!(myUser.getEmail() == null)) {
                session.setAttribute("myUser", myUser);
                RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Home.jsp");
                RequetsDispatcherObj.forward(request, response);
            } else {
                Logger.info("Connexion refusé");
                session.setAttribute("connexionrefused","Connexion refusé, identifiant ou mot de passe invalide");
                RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
                RequetsDispatcherObj.forward(request, response);

            }
    }
}
