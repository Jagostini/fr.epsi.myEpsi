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
import fr.epsi.myEpsi.dao.AnnonceDao;
import fr.epsi.myEpsi.dao.IAnnonceDao;

/**
 * Servlet implementation class DeleteAnnonce
 */
@WebServlet("/DeleteAnnonce")
public class DeleteAnnonce extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAnnonce() {
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
		
		boolean annoncesAdd = true;
		boolean page = false;
		String result;
		
		// Take Announce
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
		IAnnonceDao annonceDao = new AnnonceDao();
		annonces = (ArrayList<Annonce>) annonceDao.getMesAnnonces(Constantes.PARAM_UTILISATEURS);
		
		for(Annonce a : annonces) {
			if(a.getId() == (Long.parseLong(id))) {
				page = true;
			}
		}
		
		if(page) {
			// annonces = new ArrayList<Annonce>();
			IAnnonceDao annonceDaos = new AnnonceDao();
			annoncesAdd = (boolean) annonceDaos.delete(Integer.parseInt(id));
		}
		
		
		
		request.setAttribute("resultat", page);
		request.getRequestDispatcher("response.jsp").forward(request, response);
	}

}
