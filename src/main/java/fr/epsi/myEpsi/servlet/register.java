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

import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.dao.IUserDao;
import fr.epsi.myEpsi.dao.UserDao;
import fr.epsi.myEpsi.listeners.StartupListener;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String pwdbis = request.getParameter("pwdBis");
		
		System.out.print(mail);
		System.out.print(tel);
		System.out.print(pwd);
		System.out.print(pseudo);
		Utilisateur user = new Utilisateur();
		user.setAdministrateur(false);
		user.setId(mail);
		user.setNom(pseudo);
		user.setPassword(pwd);
		user.setTelephone(tel);
		user.toString();
		
		boolean page = true;
		
		// Take users
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		IUserDao userDao = new UserDao();
		users = (ArrayList<Utilisateur>) userDao.getAllUtilisateur();
				
		for(Utilisateur u : users) {
			if(u.getId().equals(user.getId()))
			{
				page = false;		
			}
		}
		
		if(page && pwd.equals(pwdbis)) {
			UserDao AddUser = new UserDao();
			AddUser.AddUsers(user);
		}
		
		logger.info("Servlet enregistrement client");
		
		request.getRequestDispatcher("login.html").forward(request, response);
	}

}
