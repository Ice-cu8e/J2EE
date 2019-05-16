package fr.epsi.jeeProject.servlets;

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

import static fr.epsi.jeeProject.listener.StartupListener.connection;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = "/CreateUser", name = "CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(CreateUserServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();

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
        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String createpassword = request.getParameter("createpassword");
        String secondpassword = request.getParameter("secondpassword");
        Boolean alreadyexist=false;
        if(createpassword.equals(secondpassword)){
            try {
                PreparedStatement prep = connection.prepareStatement("SELECT * FROM jee.public.user Where email=?");
                prep.setString(1, email);
                ResultSet resultSet = prep.executeQuery();
                while (resultSet.next()) {
                    alreadyexist=true;
                }
                if (!alreadyexist){
                    PreparedStatement insert = connection.prepareStatement("INSERT INTO jee.public.user VALUES(?,?,?,?,?)");
                    insert.setString(1, email);
                    insert.setString(2, nom);
                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    insert.setDate(3, date);
                    insert.setString(4, createpassword);
                    insert.setBoolean(5, false);
                    insert.executeUpdate();
                    insert.close();
                    HttpSession session=request.getSession();
                    session.setAttribute("usercreated","Utilisateur "+ email+" créé !");
                    Logger.info("Utilisateur "+ email+" créé !");
                    RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
                    RequetsDispatcherObj.forward(request, response);
                }else{
                    HttpSession session=request.getSession();
                    session.setAttribute("createuser","Un compte existe deja avec cet email");
                    Logger.info("Création utilisateur refusé : Un compte existe deja avec cet email");
                    RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
                    RequetsDispatcherObj.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            HttpSession session=request.getSession();
            session.setAttribute("createuser","Les mots de passes ne sont pas identiques");
            Logger.info("Création utilisateur refusé : Les mots de passes ne sont pas identiques");
            RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
    }
}
