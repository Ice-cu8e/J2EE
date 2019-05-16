package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet(value = "/BlogList", name = "BlogListServlet")
public class BlogListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogListServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogListServlet() {
        super();
        Logger.info("Exécution de :"+ BlogListServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Logger.info("OUI");
        String ID=request.getParameter( "ID" );
        request.setAttribute("ID",ID);
        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/BlogList.jsp");
        RequetsDispatcherObj.forward(request, response);

    }
}
