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
        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/BlogPage.jsp");
        RequetsDispatcherObj.forward(request, response);
    }
}
