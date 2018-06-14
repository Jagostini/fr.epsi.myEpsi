package fr.epsi.myEpsi.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.Constantes;
import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.dao.AnnonceDao;
import fr.epsi.myEpsi.dao.IAnnonceDao;
import fr.epsi.myEpsi.dao.IUserDao;
import fr.epsi.myEpsi.dao.UserDao;
import fr.epsi.myEpsi.listeners.StartupListener;

/**
 * Servlet implementation class MesAnnonces
 */
@WebServlet("/MesAnnonces")
public class MesAnnonces extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesAnnonces() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//Annonce
		
		// Take Announce
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
		IAnnonceDao annonceDao = new AnnonceDao();
		annonces = (ArrayList<Annonce>) annonceDao.getMesAnnonces(Constantes.PARAM_UTILISATEURS);
		
		logger.info("Servlet Mes annonces");
		
		request.setAttribute("lesAnnonces", annonces);
		request.getRequestDispatcher("annonce.jsp").forward(request, response);
	}

}
