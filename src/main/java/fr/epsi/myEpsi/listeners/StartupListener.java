package fr.epsi.myEpsi.listeners;

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
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;
import fr.epsi.myEpsi.dao.AnnonceDao;
import fr.epsi.myEpsi.dao.IAnnonceDao;
import fr.epsi.myEpsi.dao.IUserDao;
import fr.epsi.myEpsi.dao.UserDao;
import fr.epsi.myEpsi.mbeans.ChangeLog;
import fr.epsi.myEpsi.mbeans.Premier;

/**
 * Application Lifecycle Listener implementation class StartupListener
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {

	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	
    /**
     * Default constructor. 
     */
    public StartupListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	logger.info("Test de l'application");
    	
    	ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    	IUserDao utilisateurDao = new UserDao();
    	utilisateurs = (ArrayList<Utilisateur>) utilisateurDao.getAllUtilisateur();
    	
    	logger.error("Nombre d'utilisateur " + utilisateurs.size());
    	
    	
    	// Take Announce
    	Utilisateur utilisateur = new Utilisateur();
    	ArrayList<Annonce> annonces = new ArrayList<Annonce>();
    	IAnnonceDao annonceDao = new AnnonceDao();
    	annonces = (ArrayList<Annonce>) annonceDao.get(utilisateur);
    	logger.error("Nombre d'annonce " + annonces.size());
    	try {
    		Class.forName("org.hsqldb.jdbcDriver");
    		Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003","SA","");
    		logger.info("Connexion OK");
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
    	    
    	    name = new ObjectName("fr.epsi.jmx:type=Changelog");
    	    ChangeLog Log = new ChangeLog();
    	    mbs.registerMBean(Log, name);

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
    	
    }
	
}
