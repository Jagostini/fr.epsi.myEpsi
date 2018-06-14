package fr.epsi.myEpsi.servlet;

import java.awt.List;
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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		// Recuperate param
		String pseudo = request.getParameter("pseudo");
		String pwd = request.getParameter("pwd");
		boolean page = false;
		
		// Create user
		Utilisateur utilisateur = new Utilisateur();
		
		// Take users
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		IUserDao userDao = new UserDao();
		users = (ArrayList<Utilisateur>) userDao.getAllUtilisateur();
		
		for(Utilisateur u : users) {
			if(u.getPassword().equals(pwd) && u.getId().equals(pseudo))
			{
				utilisateur = u;
				page = true;		
			}
		}
		Constantes.PARAM_UTILISATEURS = utilisateur.getId();
		
		// Take Announce
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
		IAnnonceDao annonceDao = new AnnonceDao();
		annonces = (ArrayList<Annonce>) annonceDao.get(utilisateur);
		logger.info("Servlet logging");
		
		request.setAttribute("lesAnnonces", annonces);
		
		if(page)	{
			// Logger connexion reussis
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		} else {
			// Logger connexion échoué
			request.getRequestDispatcher("login.html").forward(request, response);
		}

	}

}
