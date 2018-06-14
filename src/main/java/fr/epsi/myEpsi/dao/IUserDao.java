package fr.epsi.myEpsi.dao;

import java.util.List;

import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;

public interface IUserDao {

	boolean create(Utilisateur utilisateur);
	boolean delete(Utilisateur utilisateur);
	Utilisateur get(String id);
	boolean check(Utilisateur utilisateur);
	List<Utilisateur> getAllUtilisateur();
	
}
