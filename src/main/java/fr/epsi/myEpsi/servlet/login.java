package fr.epsi.myEpsi.servlet;

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
import fr.epsi.myEpsi.dto.AnnonceDTO;
import fr.epsi.myEpsi.dto.UserDTO;

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
		String pseudo = request.getParameter("pseudo");
		String pwd = request.getParameter("pwd");
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(pseudo);
		//request.getSession().setAttribute(Constantes.PARAM_UTILISATEURS, utilisateur);
		ArrayList<Annonce> annonce = new ArrayList<Annonce>();
		Annonce ann = new Annonce();
		AnnonceDTO annonceDTO = new AnnonceDTO();
		annonce = annonceDTO.GetAnnonce();
		
		UserDTO userDTO = new UserDTO();
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		users = userDTO.GetUsers();
		
		boolean page = false;
		
		
		System.out.println("@@@@@@@@@" + annonceDTO.AddAnnonce(ann));
		
		System.out.println(users);
		// users
		for(Utilisateur u : users) {
			System.out.println(u.getPassword() + "  " + u.getNom());
			System.out.println(pwd + " " + pseudo);
			System.out.println(page);
			if(u.getPassword().equals(pwd))
			{
				page = true;
			}
		}
		request.setAttribute("lesAnnonces", annonce);
		if(page)	{
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}

	}

}
