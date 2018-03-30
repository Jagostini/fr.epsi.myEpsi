package fr.epsi.myEpsi.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

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

public class AnnonceDTO {
	
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
	public ArrayList<Annonce> GetAnnonce() {
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
		Annonce a = new Annonce();
		a.setTitre("bonjour");
		a.setDescription("coucou");
	    	logger.error("Test de l'application");
	    	try {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.error("Connexion OK");
	    		
	    		ResultSet resultats = null;
	    		String requete = "SELECT * FROM USERS";
	    		Statement stmt = con.createStatement();
	    		resultats = stmt.executeQuery(requete);
	    		
	    		while(resultats.next()) {
	    			logger.error(resultats.getObject("PASSWORD"));
	    			logger.error(resultats.getObject("NOM"));
	    			logger.error(resultats.getObject("ISADMINISTRATOR"));
	    			String bino = (String) resultats.getObject("ID");
	    			a.setDescription(bino);
	    			annonces.add(a);
	    		}
	    		logger.error(annonces.get(0));
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		return annonces;
	}
	
	public boolean SetAnnonce(ArrayList<Annonce> annonce) {
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
	
}
