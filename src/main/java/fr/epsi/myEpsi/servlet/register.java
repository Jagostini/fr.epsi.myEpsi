package fr.epsi.myEpsi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epsi.myEpsi.beans.Utilisateur;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		request.getRequestDispatcher("login.html").forward(request, response);
	}

}
