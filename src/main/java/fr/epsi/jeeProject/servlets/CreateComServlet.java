package fr.epsi.jeeProject.servlets;


import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Reponse;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.BlogDao;
import fr.epsi.jeeProject.dao.IBlogDao;
import org.apache.logging.log4j.LogManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(value = "/CreateCom", name = "CreateComServlet")
public class CreateComServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(CreateComServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateComServlet() {
        super();
        Logger.info("Exécution de :"+ CreateComServlet.class);
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
        String referer = null;
        try {
            referer = new URI(request.getHeader("referer")).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String[] uriNames = referer.split("/");
        String jspPageName = uriNames[uriNames.length-1];

        String com = request.getParameter("btnCom");
        int id=Integer.parseInt(request.getParameter("blog"));
        IBlogDao blogDao=new BlogDao();
        Blog blog=blogDao.getBlog(id);
        java.util.Date d = new java.util.Date();
        Date date = new Date(d.getTime());
        HttpSession session = request.getSession();
        Utilisateur user=(Utilisateur)session.getAttribute("myUser");

        if (com != null && user != null) {
            Reponse r = new Reponse();
            r.setCommentaire(com);
            r.setPublication(date);
            r.setBlog(blog);
            r.setBlogger(user);
            try {
                blogDao.addReponse(r);
                Logger.info("Insertion commentaire OK ");
            } catch (SQLException e) {
                Logger.error("Erreur insertion commentaire ");
            }finally {
                String url;
                if (jspPageName.equals("Blog")) {
                    url = "/Blog?ID=" + blog.getId();
                } else if (jspPageName.equals("BlogList")) {
                    url = "/Blogs";
                } else {
                    url = "/Blogs";
                }
                response.sendRedirect(request.getContextPath() + url);
            }
        } else {
            Logger.error("Utilisateur déconnecté ");
            RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/Index.jsp");
            RequetsDispatcherObj.forward(request, response);
        }
    }
}
