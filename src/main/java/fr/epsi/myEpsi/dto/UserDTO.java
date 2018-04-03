package fr.epsi.myEpsi.dto;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

public class UserDTO {

	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
	public ArrayList<Utilisateur> GetUsers() {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur();
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
	    			String id = (String) resultats.getObject("ID");
	    			user.setId(id);
	    			String pwd = (String) resultats.getObject("PASSWORD");
	    			user.setPassword(pwd);
	    			String name = (String) resultats.getObject("NOM");
	    			user.setNom(name);
	    			String tel = (String) resultats.getObject("TELEPHONE");
	    			user.setTelephone(tel);
	    			//boolean isadmin = (boolean) resultats.getObject("ISADMINISTRATOR");
	    			//user.setAdministrateur(isadmin);
	    			//user
	    			logger.error(user);
	    			users.add(user);
	    		}
	    		logger.error(users.get(0));
	        	con.close();
	    	} catch (ClassNotFoundException | SQLException e){
	    		logger.error("Connexion impossible " + e.getMessage());
	    	}
	    	
	    		return users;
	}

}
