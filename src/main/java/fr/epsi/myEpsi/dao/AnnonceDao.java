package fr.epsi.myEpsi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.listeners.StartupListener;
import fr.epsi.myEpsi.mbeans.Premier;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnnonceDao implements IAnnonceDao {
	
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
	public List<Annonce> get(Utilisateur utilisateur) {
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
	    	logger.error("Test de l'application");
	    	try {
	    		// Connexion BDD
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.error("Connexion OK");
	    		
	    		// Requete BDD
	    		ResultSet resultats = null;
	    		String requete = "SELECT * FROM ANNONCES";
	    		Statement stmt = con.createStatement();
	    		resultats = stmt.executeQuery(requete);
	    		
	    		// Traitement Requete
	    		while(resultats.next()) {
	    			Annonce a = new Annonce();
	    			a.setTitre((String) resultats.getObject("TITLE"));
	    			a.setId((int)resultats.getObject("ID"));
	    			a.setDescription((String)resultats.getObject("content"));
	    			System.out.println(a.toString());
	    			annonces.add(a);
	    		}
	    		logger.error(annonces.get(0));
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.debug("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		return annonces;
	}

	@Override
	public boolean create(Annonce annonce) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Annonce annonce) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Annonce get(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	/*public boolean SetAnnonce(ArrayList<Annonce> annonce) {
		boolean updateAnnonce = true;
	    	logger.error("Set Annonce");
	    	try {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.error("Connexion OK");
	    		
	    		ResultSet resultats = null;
	    		String requete = "UPDATE * SET USERS";
	    		Statement stmt = con.createStatement();
	    		resultats = stmt.executeQuery(requete);
	    		
	    		// resultats sortir un boolean pour dire si c'est ok
	    		
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    	ObjectName name = null;

	    	try {
	    	    name = new ObjectName("fr.epsi.jmx:type=PremierMBean");
	    	    Premier mbean = new Premier();
	    	    mbs.registerMBean(mbean, name);

	    	}
	    	catch (MalformedObjectNameException e) {
	    	    e.printStackTrace();
	    	}
	    	catch (NullPointerException e) {
	    	    e.printStackTrace();
	    	}
	    	catch (InstanceAlreadyExistsException e) {
	    	    e.printStackTrace();
	    	}
	    	catch (MBeanRegistrationException e) {
	    	    e.printStackTrace();
	    	}
	    	catch (NotCompliantMBeanException e) {
	    	    e.printStackTrace();
	    	}
		return updateAnnonce;
		
	}
	
	public boolean AddAnnonce(Annonce annonce) {
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
		Annonce a = new Annonce();
		a.setTitre("ca marche");
		int nbMaj = 0;
	    	logger.error("Test de l'application");
	    	try {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.error("Connexion OK");
	    		
	    		String requete = "INSERT INTO ANNONCES VALUES(4,'" + a.getTitre() + "','content','USER_ID',NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
	    		Statement stmt = con.createStatement();
	    		nbMaj = stmt.executeUpdate(requete);
	    		System.out.println(nbMaj);
	    		
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		if(nbMaj > 0) {
	    			return true;
	    		} else {
	    			return false;
	    		}
	}*/
	
}
