package fr.epsi.myEpsi.dto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.dao.UserDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDTO {
	
	public ArrayList<Utilisateur> GetUsers() {
		UserDTO users = new UserDTO();
		
		Logger.info("Test de l'application");
		try {
			Logger.error( "Chargement du driver..." );
	    	Class.forName("org.hsqldb.jdbcDriver");
	    	logger.error( "Driver charg� !" );
		} catch ( ClassNotFoundException e) {
		   	logger.error( "Erreur lors du chargement : le driver n'a pas �t� trouv� dans le classpath ! <br/>"+ e.getMessage() );
		}
		/* Connexion � la base de donn�es */
		String url = "jdbc:hsqldb:hsql://localhost:9003";
		String userbsd = "SA";
		String passwordbsd = "";
			
		/*D�claration objets*/
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		/*D�claration variables*/
		String idUtilisateur = "";
		String mdpUtilisateur = "";
		String isAdministrateur = "";
		String nomUtilisateur = "";
		
		Utilisateur users1 = new Utilisateur();
		//Array<Utilisateurs> users = new Array();
		    
		try {
		  	Logger.error( "Connexion � la base de donn�es..." );
		   	connexion = DriverManager.getConnection( url, userbsd, passwordbsd );
		    logger.error("Connexion OK");

		    /* Cr�ation des objets g�rants les requ�tes */
		    statement = connexion.createStatement();
		    logger.error( "Objet requ�te cr�� !" );
	            
		    /* Ex�cution d'une requ�te de lecture */
		    resultat = statement.executeQuery( "SELECT ID, PASSWORD, ISADMINISTRATOR, NAME FROM UTILISATEURS;" );
		    logger.error( "Requ�te \"SELECT ID, PASSWORD, ISADMINISTRATOR, NAME FROM UTILISATEURS;\" effectu�e !" );
		 
		    logger.error(resultat.toString());
		    ArrayList<Utilisateur> userss = new ArrayList<Utilisateur>();
	        
		    /* R�cup�ration des donn�es du r�sultat de la requ�te de lecture */
		    while ( resultat.next() ) {
		    	idUtilisateur = resultat.getString( "ID" );            
		    	mdpUtilisateur = resultat.getString( "PASSWORD" );
		    	isAdministrateur = resultat.getString( "ISADMINISTRATOR" );
		       	nomUtilisateur = resultat.getString( "NAME" );
		       	
		       	users1.setAdministrateur(resultat.getBoolean("ISADMINISTRATOR"));
		       	users1.setNom(resultat.getString("NAME"));
		       	users1.setId(idUtilisateur);
		       	users1.setPassword(mdpUtilisateur);
	            
		       	
		       	userss.add(users1);
		       	/* Formatage des donn�es pour affichage dans la JSP finale. */
		        logger.error( "Donn�es retourn�es par la requ�te : ID = " + idUtilisateur + ", PASSWORD = " + mdpUtilisateur + ", ISADMINISTRATOR = " + isAdministrateur + ", NAME = " + nomUtilisateur + "." );
		        System.out.println(users1.toString());    	        
		    }
		    
		    logger.error(nomUtilisateur);   
		    
		} catch ( SQLException e ) {
		   	//logger.error( "Erreur lors de la connexion : <br/>"+ e.getMessage() );
		} finally {
		   	//logger.error( "Fermeture de l'objet ResultSet." );
		    if ( resultat != null ) {
		    	try {
		    		resultat.close();
		        } catch ( SQLException ignore ) {}
		    }
		    //logger.error( "Fermeture de l'objet Statement." );
		    if ( statement != null ) {
		    	try {
		    		statement.close();
		        } catch ( SQLException ignore ) {}
		    }
		    //logger.error( "Fermeture de l'objet Connection." );
		    if ( connexion != null ) {
		    	try {
		    		connexion.close();
		        } catch ( SQLException ignore ) {}
		    }
		}
		
		
		return userss;
	}
	

}
