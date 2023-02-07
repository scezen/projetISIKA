package fr.isika.projet1Front.modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire extends FichierBinaire {

	private static Noeud racine;
//	private List<Stagiaire> listeOrdonnee;

	public ArbreBinaire() throws IOException {
		super();

		// Instenciation du stagiaire racine
		racine = null;
//		listeOrdonnee = new ArrayList<>();
	}

	public ArbreBinaire(Noeud racine) throws IOException {
		super();
		ArbreBinaire.racine = racine;
	}

	public void ajouteNoeud(Stagiaire stagiaire) throws IOException {
		if (this.length() == 0) {
			racine = new Noeud(stagiaire, -1, -1, -1);

			ecris(racine.getStagiaire());

		} else {
			
			racine.ajouteNoeudNonRacine(stagiaire);
		}
	}

	public List<Stagiaire> afficherArbre() throws IOException {
		
		List<Stagiaire> liste = new ArrayList<>();

		if (racine == null || this.length() == 0) {
			System.out.println("Le fichier est vide il n'y a rien à afficher");
			return liste;

		} else {
			
			racine.parcoursInfixe(liste);
			return liste;
		}
	}

	public void ecris(Stagiaire stagiaire) {
		// On applique la méthode attributLong() à chaque attribut, permet de garantire
		// le respect de la taille maximum prévue

		int reference = stagiaire.getReferenceNoeud() * TAILLE_NOEUD;

		try {
			seek(reference);

			this.writeChars(stagiaire.attributLong(TAILLE_NOM, stagiaire.getNom()));
			this.writeChars(stagiaire.attributLong(TAILLE_PRENOM, stagiaire.getPrenom()));
			this.writeChars(stagiaire.attributLong(TAILLE_DEPARTEMENT, stagiaire.getDepartement()));
			this.writeChars(stagiaire.attributLong(TAILLE_UNIVERSITE, stagiaire.getUniversite()));
			this.writeChars(stagiaire.attributLong(TAILLE_ANNEE, stagiaire.getAnnee()));

			// Références vers les fils gauche et droit, pour l'instant considéré comme
			// nulles "== -1"
			this.writeInt(stagiaire.getReferenceNoeud());
			this.writeInt(-1);
			this.writeInt(-1);
			this.writeInt(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
//	public List<Stagiaire> getArbreBinaire() {
//		return listeOrdonnee;
//	}

	public Noeud getRacine() {
		return racine;
	}
}