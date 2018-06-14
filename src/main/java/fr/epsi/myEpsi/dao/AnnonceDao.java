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
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnnonceDao implements IAnnonceDao {
	
	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
	public List<Annonce> get(Utilisateur utilisateur) {
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
	    	logger.debug("Fonction de get Annonce DAO");
	    	try {
	    		// Connexion BDD
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.info("Connexion OK");
	    		
	    		// Requete BDD
	    		ResultSet resultats = null;
	    		String requete = "SELECT * FROM ANNONCES";
	    		Statement stmt = con.createStatement();
	    		resultats = stmt.executeQuery(requete);
	    		
	    		// Traitement Requete
	    		while(resultats.next()) {
	    			Annonce a = new Annonce();
	    			a.setTitre((String) resultats.getObject("TITLE"));
	    			a.setId((long)resultats.getObject("ID"));
	    			a.setDescription((String)resultats.getObject("CONTENT"));
	    			a.setPrix((int)resultats.getObject("PRIX"));
	    			a.setStatut((String)resultats.getObject("STATUS"));
	    			a.setModification((Timestamp)resultats.getObject("UPDATE_DATE"));
	    			a.setCreation((Timestamp)resultats.getObject("CREATION_DATE"));
	    			// Timestamp t = null;
	    			// System.out.println(t.getTime());
	    			
	    			Utilisateur user = new Utilisateur();
	    			user.setId((String)resultats.getObject("USER_ID"));
	    			a.setVendeur(user);
	    			if(a.getStatut().equals("publie")) {
	    				annonces.add(a);
	    			}
	    			
	    		}
	    		// logger.error(annonces.get(0));
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		return annonces;
	}
	
	@Override
	public boolean delete(long id) {
		logger.debug("Fonction de suppression Annonce DAO" + id);
		boolean verifDelete = false;
    		try {
    			// Connexion BDD
    			Class.forName("org.hsqldb.jdbcDriver");
    			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    			logger.info("Connexion OK");
    		
    			// Requete BDD
    			// ResultSet resultats = null;
    			String requete = "DELETE FROM ANNONCES WHERE ID =" + id  + "";
    			
    			Statement stmt = con.createStatement();
    			int nbMaj = stmt.executeUpdate(requete);
    			con.close();
    		} catch (ClassNotFoundException | SQLException e){
    			logger.error("Connexion impossible " + e.getMessage());
    		}
    		return verifDelete;
	}

	@Override
	public int create(Annonce annonce) {
		// TODO Auto-generated method stub
		int nbMaj=0;
		try {
			// Connexion BDD
			Class.forName("org.hsqldb.jdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
			logger.info("Connexion OK");
		
			// Requete BDD
			String requete = "INSERT INTO ANNONCES VALUES(" + annonce.getId() + ",'" + annonce.getTitre() +"','" + annonce.getDescription() + "','" + annonce.getVendeur().getId() + "','" + annonce.getCreation() +"',NULL," + annonce.getPrix() + ",'publie',NULL,NULL,NULL)";
			
			Statement stmt = con.createStatement();
			nbMaj = stmt.executeUpdate(requete);
			con.close();
		} catch (ClassNotFoundException | SQLException e){
			logger.error("Connexion impossible " + e.getMessage());
		}
		return nbMaj;
	}

	@Override
	public int update(Annonce annonce) {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public List<Annonce> getMesAnnonces(String id) {
		// TODO Auto-generated method stub
		
		ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    	logger.debug("Test de l'application");
    	try {
    		// Connexion BDD
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    		logger.info("Connexion OK");
    		
    		// Requete BDD
    		ResultSet resultats = null;
    		String requete = "SELECT * FROM ANNONCES WHERE USER_ID='" + id + "'";
    		Statement stmt = con.createStatement();
    		resultats = stmt.executeQuery(requete);
    		
    		
    		
    		// Traitement Requete
    		while(resultats.next()) {
    			Annonce a = new Annonce();
    			a.setTitre((String) resultats.getObject("TITLE"));
    			a.setId((long)resultats.getObject("ID"));
    			a.setDescription((String)resultats.getObject("CONTENT"));
    			a.setPrix((int)resultats.getObject("PRIX"));
    			a.setStatut((String)resultats.getObject("STATUS"));
    			// a.setNbVues((int)resultats.getObject("VUES"));
    			Utilisateur user = new Utilisateur();
    			user.setId((String)resultats.getObject("USER_ID"));
    			a.setVendeur(user);
    			annonces.add(a);
    		}
    		// logger.error(annonces.get(0));
        	con.close();
    	} catch (ClassNotFoundException | SQLException e){
    		logger.error("Connexion impossible " + e.getMessage());
    	}
		
		return annonces;
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
