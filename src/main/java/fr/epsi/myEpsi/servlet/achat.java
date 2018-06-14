package fr.epsi.myEpsi.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epsi.myEpsi.Constantes;
import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.dao.AnnonceDao;
import fr.epsi.myEpsi.dao.IAnnonceDao;

/**
 * Servlet implementation class achat
 */
@WebServlet("/achat")
public class achat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public achat() {
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
		
		String id = request.getParameter("id");
		Timestamp ts = new Timestamp(new Date().getTime());
		
		Annonce annonce = new Annonce();
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setId(Constantes.PARAM_UTILISATEURS);
		annonce.setAchat(ts);
		annonce.setId(Integer.parseInt(id));
		annonce.setStatut("vendu");
		annonce.setAcheteur(utilisateur);
		System.out.println(annonce.toString());
		
		IAnnonceDao annonceDao = new AnnonceDao();
		annonceDao.update(annonce);
		
		
		System.out.println(id);
		
		
		request.setAttribute("resultat", id);
		request.getRequestDispatcher("accueil.jsp").forward(request, response);
	}

}
