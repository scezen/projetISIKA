package fr.isika.projet1Front.modele;

import java.io.IOException;

public class Doublon extends Noeud {
	
	public Doublon(Stagiaire stagiaire, int referenceFG, int referenceFD, int referenceDoublon) throws IOException {

		super(stagiaire, referenceFG, referenceFD, referenceDoublon);
	}
	

	@Override
	public void ecris(Stagiaire stagiaire) {
		// TODO Auto-generated method stub
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
}
