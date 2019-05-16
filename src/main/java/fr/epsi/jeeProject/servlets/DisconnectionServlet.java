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
import java.util.Calendar;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = "/Disconnection", name = "DisconnectionServlet")
public class DisconnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(DisconnectionServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisconnectionServlet() {
        super();
        Logger.info("Exécution de :"+ DisconnectionServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session=request.getSession();
        Utilisateur myUser=(Utilisateur)session.getAttribute("myUser");
        Logger.info("Utilisateur "+myUser.getEmail()+ " déconnecté");
        session.setAttribute("myUser",null);
        RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
        RequetsDispatcherObj.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
