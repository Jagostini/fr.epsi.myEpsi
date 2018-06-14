package fr.epsi.myEpsi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

import fr.epsi.myEpsi.Constantes;
import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.dao.AnnonceDao;
import fr.epsi.myEpsi.dao.IAnnonceDao;
import fr.epsi.myEpsi.listeners.StartupListener;

import java.sql.Timestamp;

/**
 * Servlet implementation class AjoutAnnonce
 */
@WebServlet("/AjoutAnnonce")
public class AjoutAnnonce extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutAnnonce() {
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
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(Constantes.PARAM_UTILISATEURS);
		
		String id = request.getParameter("id");
		String titre = request.getParameter("titre");
		String prix = request.getParameter("prix");
		String resume = request.getParameter("resume");
		
		Annonce 	annonce = new Annonce();
		annonce.setId(Integer.parseInt(id));
		annonce.setTitre(titre);
		annonce.setPrix(Integer.parseInt(prix));
		annonce.setDescription(resume);
		annonce.setVendeur(utilisateur);
		Timestamp ts = new Timestamp(new Date().getTime());
		annonce.setCreation(ts);
		
		// annonces = new ArrayList<Annonce>();
		IAnnonceDao annonceDao = new AnnonceDao();
				
		int annoncesAdd = annonceDao.create(annonce);
		
		logger.info("Servlet ajout annonce");
		request.setAttribute("resultat", annoncesAdd);
		request.getRequestDispatcher("response.jsp").forward(request, response);
	}

}
