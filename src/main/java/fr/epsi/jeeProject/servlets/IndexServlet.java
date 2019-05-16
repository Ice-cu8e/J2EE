package fr.epsi.jeeProject.servlets;

import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Integer.parseInt;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = "/", name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(IndexServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        Logger.info("Exécution de :"+ IndexServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session=request.getSession();
        Logger.debug(session.getAttribute("myUser"));
        if(!(session.getAttribute("myUser")==null)){
            Logger.info("Utilisateur déja connecté ");
            RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/BlogPage.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
        else{
            RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/Index.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
