package fr.epsi.jeeProject.servlets;

import fr.epsi.jeeProject.listener.StartupListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
@WebServlet(value = "/Blog", name = "BlogServlet")
public class BlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(BlogServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogServlet() {
        super();
        Logger.info("Exécution de :"+ BlogServlet.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String ID=request.getParameter( "ID" );
        request.setAttribute("ID",ID);
        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/Blog.jsp");
        RequetsDispatcherObj.forward(request, response);

    }
}
