package fr.epsi.myEpsi.servlet;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

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
import fr.epsi.myEpsi.dao.IUserDao;
import fr.epsi.myEpsi.dao.UserDao;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		// Create user
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(pseudo);
		Constantes.PARAM_UTILISATEURS = pseudo;
		
		
		//request.getSession().setAttribute(Constantes.PARAM_UTILISATEURS, utilisateur);
		
		// Take announce
		// List annonce = new List();
		// Annonce ann = new Annonce();
		// IAnnonceDao annonceDAO = new AnnonceDao();
		// annonce = annonceDTO.get(utilisateur);
		// annonceDAO.get
		
		// Take users
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		IUserDao userDao = new UserDao();
		users = (ArrayList<Utilisateur>) userDao.getAllUtilisateur();
		
		
		/*UserDao userDTO = new UserDao();
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		users = userDTO.GetUsers();
		System.out.print(users);*/
		
		boolean page = false;
		
		// System.out.println("@@@@@@@@@" + annonceDTO.AddAnnonce(ann));
		// System.out.println(users);
		// users
		
		for(Utilisateur u : users) {
			if(u.getPassword().equals(pwd))
			{
				page = true;		
			}
		}
		request.setAttribute("lesAnnonces", users);
		if(page)	{
			// Logger connexion reussis
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		} else {
			// Logger connexion échoué
			request.getRequestDispatcher("login.html").forward(request, response);
		}

	}

}
