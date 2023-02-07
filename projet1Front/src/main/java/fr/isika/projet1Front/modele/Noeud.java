package fr.isika.projet1Front.modele;

import java.io.IOException;
import java.util.List;

// Référence du fils gauche et et fils droit de chaque stagiare dans le fichier
// STAGIAIRES.bin

public class Noeud extends FichierBinaire {

	protected Stagiaire stagiaire;
	protected int referenceFG;
	protected int referenceFD;
	protected int referenceDoublon;

	public Noeud(Stagiaire stagiaire, int referenceFG, int referenceFD, int referenceDoublon) throws IOException {

		super();
		this.stagiaire = stagiaire;

		// Création des références avec des valeurs négatives considérées comme valeurs
		// nulles
		this.referenceFG = referenceFG;
		this.referenceFD = referenceFD;
		this.referenceDoublon = referenceDoublon;
	}

	// Méthode qui permet d'ajouter des noeuds autre que la racine de l'arbre
	public void ajouteNoeudNonRacine(Stagiaire stagiaire) throws IOException {

		int comparaison = stagiaire.getNom().compareTo(this.getStagiaire().getNom());
		int positionFG = this.getStagiaire().getReferenceNoeud() * TAILLE_NOEUD + REF_FG;
		int positionFD = this.getStagiaire().getReferenceNoeud() * TAILLE_NOEUD + REF_FD;

		if (comparaison != 0) {
			if (comparaison < 0) {

				if (this.getReferenceFG() == -1) {

					// On donne au fils gauche de la racine courante la référence du stagiaire à sauvegarder
					this.setReferenceFG(stagiaire.getReferenceNoeud());
					this.seek(positionFG);
					this.writeInt(stagiaire.getReferenceNoeud());

					// On se positionne au bon endroit dans le fichier
					this.positionnePointeur(stagiaire.getReferenceNoeud());
					this.ecris(stagiaire);

				} else {
					this.recupereNoeud(this.getReferenceFG()).ajouteNoeudNonRacine(stagiaire);
				}
			} else {

				if (this.getReferenceFD() == -1) {

					this.setReferenceFD(stagiaire.getReferenceNoeud());
					this.seek(positionFD);
					this.writeInt(stagiaire.getReferenceNoeud());

					this.positionnePointeur(stagiaire.getReferenceNoeud());
					this.ecris(stagiaire);

				} else {

					this.recupereNoeud(this.getReferenceFD()).ajouteNoeudNonRacine(stagiaire);
				}
			}
		} else {

			this.ajouteDoublon(stagiaire);
		}
	}

	public void ajouteDoublon(Stagiaire stagiaire) throws IOException {

		int ancienneReference = this.getReferenceDoublon();
		int position = (this.getStagiaire().getReferenceNoeud() * TAILLE_NOEUD + REF_DOUBLON);

		if (ancienneReference == -1) {

			// position noeud stagiaire a ajouter
			this.positionnePointeur(stagiaire.getReferenceNoeud());
			this.ecris(stagiaire);

			this.setReferenceDoublon(stagiaire.getReferenceNoeud());

			// position reference doublon du noeud actuel
			this.seek(position);
			this.writeInt(stagiaire.getReferenceNoeud());

		} else {

			this.setReferenceDoublon(stagiaire.getReferenceNoeud());
			Noeud noeudSuivant = this.recupereNoeud(ancienneReference);
			noeudSuivant.ajouteDoublon(stagiaire);
		}
	}

	public void supprimeNoeud(Stagiaire stagiaire) throws IOException {
		int comparaison = stagiaire.getNom().compareTo(this.getStagiaire().getNom());
		System.out.println("Methode supprimeNoeud");

		if (comparaison == 0) {
			System.out.println("Noeud trouvé");
			this.supprimeRacine();

		} else if (comparaison < 0) {
			System.out.println("Continue FG");
			if (this.getReferenceFG() != -1) {
				this.recupereNoeud(this.getReferenceFG()).supprimeNoeud(stagiaire);
			}

		} else {
			System.out.println("Continue FD");
			if (this.getReferenceFD() != -1) {
				this.recupereNoeud(this.getReferenceFD()).supprimeNoeud(stagiaire);
			}
		}
	}

	public void supprimeRacine() throws IOException {
		
		System.out.println("Methode supprimeRacine");
		
		int positionRef = this.getStagiaire().getReferenceNoeud() * TAILLE_NOEUD + REF_NOEUD;
		int positionFG = this.getReferenceFG() * TAILLE_NOEUD + REF_FG;
		int positionFD = this.getReferenceFD() * TAILLE_NOEUD + REF_FD;

		if (this.getReferenceFG() == -1 && this.getReferenceFD() == -1) {
			System.out.println("La racine est une feuille");
			 
			this.seek(positionRef);
			this.writeInt(-1); 

		} else if (this.getReferenceFG() != -1 && this.getReferenceFD() == -1) {
			// On récupère la reference du fils droit
			// tu le remplace par la valeur de son fils droit
			
			System.out.println("La racine a un FG");
			
			int reference = this.getStagiaire().getReferenceNoeud();
			Noeud noeudFG = this.recupereNoeud(this.getReferenceFG());
			noeudFG.getStagiaire().setReferenceNoeud(reference);
			this.getStagiaire().setReferenceNoeud(-1);
			
			this.seek(positionRef);
			this.writeInt(-1);
			
			this.seek(positionFG);
			this.writeInt(noeudFG.getStagiaire().getReferenceNoeud());
			
		} else if (this.getReferenceFD() != -1 && this.getReferenceFD() == -1) {
			// On récupère la reference du fils gauche
			// tu le remplace par la valeur de son fils droit
			
			System.out.println("La racine a un FD");
			
			int reference = this.getStagiaire().getReferenceNoeud();
			Noeud noeudFD = this.recupereNoeud(this.getReferenceFD());
			noeudFD.getStagiaire().setReferenceNoeud(reference);
			this.getStagiaire().setReferenceNoeud(-1);
			
			this.seek(positionRef);
			this.writeInt(-1);
			
			this.seek(positionFD);
			this.writeInt(noeudFD.getStagiaire().getReferenceNoeud());
			
		} else {
			// On remplace le noeud dans le quel on se trouve par le contenue de son
			// successeur;			
			//this.setStagiaire(this.noeudSuccesseur().getStagiaire());
			
			System.out.println("La racine a deux fils");
			
			Noeud noeud = this.noeudSuccesseur();
			int positionRefSucc = noeud.getStagiaire().getReferenceNoeud() * TAILLE_NOEUD + REF_NOEUD;
			
			this.setStagiaire(noeud.getStagiaire());
			this.positionnePointeur(this.getStagiaire().getReferenceNoeud());
			this.remplace(noeud.getStagiaire());
			
			this.seek(positionRefSucc);
			this.writeInt(-1);
		} 
	}

	@SuppressWarnings("resource")
	public Noeud noeudSuccesseur() throws IOException {
		System.out.println("Methode noeudSuccesseur");
		
		Noeud noeudCourant = this.recupereNoeud(this.getReferenceFD());
		while (noeudCourant.getReferenceFG() != -1) {
			noeudCourant = this.recupereNoeud(noeudCourant.getReferenceFG());
		}
		return noeudCourant;
	}

	public List<Stagiaire> parcoursInfixe(List<Stagiaire> liste) throws IOException {

		if (this.getReferenceFG() != -1) {
			this.recupereNoeud(this.getReferenceFG()).parcoursInfixe(liste);
		}

		if (this.getReferenceDoublon() == -1) {
			liste.add(this.getStagiaire());
		} else {
			liste.add(this.getStagiaire());
			Noeud noeudSuivant = this.recupereNoeud(this.getReferenceDoublon());
			liste.add(noeudSuivant.getStagiaire());
		}

		if (this.getReferenceFD() != -1) {
			this.recupereNoeud(this.getReferenceFD()).parcoursInfixe(liste);
		}
		return liste;
	}
	
	public void remplace(Stagiaire stagiaire) {
		try {
			this.writeChars(stagiaire.attributLong(TAILLE_NOM, stagiaire.getNom()));
			this.writeChars(stagiaire.attributLong(TAILLE_PRENOM, stagiaire.getPrenom()));
			this.writeChars(stagiaire.attributLong(TAILLE_DEPARTEMENT, stagiaire.getDepartement()));
			this.writeChars(stagiaire.attributLong(TAILLE_UNIVERSITE, stagiaire.getUniversite()));
			this.writeChars(stagiaire.attributLong(TAILLE_ANNEE, stagiaire.getAnnee()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ecris(Stagiaire stagiaire) {
		try {
			this.writeChars(stagiaire.attributLong(TAILLE_NOM, stagiaire.getNom()));
			this.writeChars(stagiaire.attributLong(TAILLE_PRENOM, stagiaire.getPrenom()));
			this.writeChars(stagiaire.attributLong(TAILLE_DEPARTEMENT, stagiaire.getDepartement()));
			this.writeChars(stagiaire.attributLong(TAILLE_UNIVERSITE, stagiaire.getUniversite()));
			this.writeChars(stagiaire.attributLong(TAILLE_ANNEE, stagiaire.getAnnee()));

			this.writeInt(stagiaire.getReferenceNoeud());
			this.writeInt(-1);
			this.writeInt(-1);
			this.writeInt(-1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Noeud [stagiaire=" + stagiaire + ", referenceFG=" + referenceFG + ", referenceFD=" + referenceFD
				+ ", referenceDoublon=" + referenceDoublon + "]";
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getReferenceFG() {
		return referenceFG;
	}

	public void setReferenceFG(int referenceFG) {
		this.referenceFG = referenceFG;
	}

	public int getReferenceFD() {
		return referenceFD;
	}

	public void setReferenceFD(int referenceFD) {
		this.referenceFD = referenceFD;
	}

	public int getReferenceDoublon() {
		return referenceDoublon;
	}

	public void setReferenceDoublon(int referenceDoublon) {
		this.referenceDoublon = referenceDoublon;
	}
}
