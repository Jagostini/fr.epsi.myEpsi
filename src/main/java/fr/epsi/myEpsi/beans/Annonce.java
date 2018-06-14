package fr.epsi.myEpsi.beans;

import java.util.Date;

public class Annonce {
	
	private long id;
	private Utilisateur vendeur;
	private String titre;
	private String description;
	private String statut;
	private int prix;
	private long nbVues;
	private Date creation;
	private Date modification;
	private Utilisateur acheteur;
	private Date achat;
	
	public Annonce() {
		// statut = Statuts.TEMPORAIRE;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public long getNbVues() {
		return nbVues;
	}

	public void setNbVues(long nbVues) {
		this.nbVues = nbVues;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Date getAchat() {
		return achat;
	}

	public void setAchat(Date achat) {
		this.achat = achat;
	}

	@Override
	public String toString() {
		return "Annonce [id=" + id + ", vendeur=" + vendeur + ", titre=" + titre + ", description=" + description
				+ ", statut=" + statut + ", prix=" + prix + ", nbVues=" + nbVues + ", creation=" + creation
				+ ", modification=" + modification + ", acheteur=" + acheteur + ", achat=" + achat + "]";
	}
	
	
}
