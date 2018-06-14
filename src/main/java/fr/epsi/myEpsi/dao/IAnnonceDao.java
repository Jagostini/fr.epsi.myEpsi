package fr.epsi.myEpsi.dao;

import java.util.List;

import fr.epsi.myEpsi.beans.Annonce;
import fr.epsi.myEpsi.beans.Utilisateur;

public interface IAnnonceDao {

	int create(Annonce annonce);
	int update(Annonce annonce);
	boolean delete(long id);
	List<Annonce> getMesAnnonces(String id);
	List<Annonce> get(Utilisateur utilisateur);

}
