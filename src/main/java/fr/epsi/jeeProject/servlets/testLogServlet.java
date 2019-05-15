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
 * Servlet implementation class PingServlet
 */
@WebServlet(value = "/testLog", name = "testLogServlet")
public class testLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger(StartupListener.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public testLogServlet() {
        super();
		// TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger.trace("msg de trace");
		Logger.debug("msg de debogage");
		Logger.info("msg d'information");
		Logger.warn("msg d'avertissement");
		Logger.error("msg d'erreur");
		Logger.fatal("msg d'erreur fatale");
	}
}
