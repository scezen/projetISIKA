package fr.isika.projet1Front.modele;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Stagiaire {

	private String nom;
	private String prenom;
	private String departement;
	private String universite;
	private String annee;	
	private int referenceNoeud;

	public Stagiaire(String nom, String prenom, String departement, String universite, String annee, int referenceNoeud) throws IOException {
		
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.universite = universite;
		this.annee = annee;
		this.referenceNoeud = referenceNoeud;
	}

	// Cette Méthode va permettre de donner une taille fixe pour chaque attribut
	// pour tous les stagiaires dans le fichier STAGIAIRES.bin;
	public String attributLong(int attributTailleMax, String attribut) {
		String attributLong = " ";
		if (attribut.length() < attributTailleMax) {
			attributLong = attribut;
			for (int i = attribut.length(); i < attributTailleMax; i++) {
				attributLong += " ";
			}
		} else {

			// prends la chaine de caractère comprise entre les index 0 inclus et la taille
			// maximum de l'attribut exclu
			attributLong = attribut.substring(0, attributTailleMax);
		}

		return attributLong;
	}
	
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", universite="
				+ universite + ", annee=" + annee + ", referenceNoeud=" + referenceNoeud + "]";
	}

	public int getReferenceNoeud() {
		return referenceNoeud;
	}

	public void setReferenceNoeud(int referenceNoeud) {
		this.referenceNoeud = referenceNoeud;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getUniversite() {
		return universite;
	}

	public void setUniversite(String universite) {
		this.universite = universite;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}
	
	public void ecrisDansFichierBinaire(Stagiaire stagiaire) {
		
	}
}