package fr.epsi.myEpsi.dao;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.listeners.StartupListener;
import fr.epsi.myEpsi.mbeans.Premier;

public class UserDao implements IUserDao {

	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
	public ArrayList<Utilisateur> GetUsers() {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur();
	    	logger.debug("Test de l'application");
	    	try {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.info("Connexion OK");
	    		
	    		ResultSet resultats = null;
	    		String requete = "SELECT * FROM USERS";
	    		Statement stmt = con.createStatement();
	    		resultats = stmt.executeQuery(requete);
	    		
	    		while(resultats.next()) {
	    			logger.info(resultats.getObject("PASSWORD"));
	    			logger.info(resultats.getObject("NOM"));
	    			String id = (String) resultats.getObject("ID");
	    			user.setId(id);
	    			String pwd = (String) resultats.getObject("PASSWORD");
	    			user.setPassword(pwd);
	    			String name = (String) resultats.getObject("NOM");
	    			user.setNom(name);
	    			String tel = (String) resultats.getObject("TELEPHONE");
	    			user.setTelephone(tel);
	    			logger.debug(user);
	    			users.add(user);
	    		}
	    		logger.debug(users.get(0));
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		return users;
	}
	
	public boolean AddUsers(Utilisateur user) {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
	    	logger.debug("Test de l'application");
	    	try {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.info("Connexion OK");
	    		
	    		String requete = "INSERT INTO USERS VALUES('" + user.getId() + "','" + user.getPassword() + "','" + user.getNom() + "',NULL,FALSE)";
	    		Statement stmt = con.createStatement();
	    		int nbMaj = stmt.executeUpdate(requete);
	    		System.out.println(nbMaj + "//////////////");
	    		
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		return true;
	}

	@Override
	public boolean create(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Utilisateur get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean check(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Utilisateur> getAllUtilisateur() {
		// TODO Auto-generated method stub
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
	    	logger.debug("Test de l'application");
	    	try {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
	    		logger.info("Connexion OK");
	    		
	    		ResultSet resultats = null;
	    		String requete = "SELECT * FROM USERS";
	    		Statement stmt = con.createStatement();
	    		resultats = stmt.executeQuery(requete);
	    		
	    		while(resultats.next()) {
	    			Utilisateur user = new Utilisateur();
	    			logger.debug(resultats.getObject("PASSWORD"));
	    			logger.debug(resultats.getObject("NOM"));
	    			
	    			// ID
	    			String id = (String) resultats.getObject("ID");
	    			user.setId(id);
	    			
	    			// PWD
	    			String pwd = (String) resultats.getObject("PASSWORD");
	    			user.setPassword(pwd);
	    			
	    			// NAME
	    			String name = (String) resultats.getObject("NOM");
	    			user.setNom(name);
	    			
	    			// TEL
	    			String tel = (String) resultats.getObject("TELEPHONE");
	    			user.setTelephone(tel);
	    			
	    			// ADD USER IN LIST
	    			logger.debug(user);
	    			users.add(user);
	    		}
	    		logger.debug(users.get(0));
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    	return users;
	}

}
