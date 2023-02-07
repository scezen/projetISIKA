package fr.isika.projet1Front.modele;

import java.io.IOException;
import java.io.RandomAccessFile;
import fr.isika.projet1Front.interfaces.InterfaceTaille;



public abstract class FichierBinaire extends RandomAccessFile implements InterfaceTaille {
	
	public FichierBinaire() throws IOException {
		super("W:\\ISIKA\\STAGIAIRES.bin", "rw");
	}

	public abstract void ecris(Stagiaire stagiaire);
	
	public void positionnePointeur(int reference) throws IOException {
		this.seek(reference * TAILLE_NOEUD);
	}
	
	public Noeud recupereNoeud(int reference) throws IOException {
		this.positionnePointeur(reference);
		
		String nom = "";
		for(int i = 0; i < TAILLE_NOM; i++) {
			nom += this.readChar();
		}
		
		String prenom = "";
		for(int i = 0; i < TAILLE_PRENOM; i++) {
			prenom += this.readChar();
		}
		
		String departement = "";
		for(int i = 0; i < TAILLE_DEPARTEMENT; i++) {
			departement += this.readChar();
		}
		
		String universite = "";
		for(int i = 0; i < TAILLE_UNIVERSITE; i++) {
			universite += this.readChar();
		}
		
		String annee = "";
		for(int i = 0; i < TAILLE_ANNEE; i++) {
			annee += this.readChar();
		}
		
		int referenceNoeud = this.readInt();
		int referenceFG = this.readInt();
		int referenceFD = this.readInt();
		int referenceDoublon = this.readInt();
		
		Stagiaire stagiaireExtrait = new Stagiaire( nom.trim(), prenom.trim(), departement.trim(), universite.trim(), annee.trim(), referenceNoeud);
		Noeud noeud = new Noeud(stagiaireExtrait, referenceFG, referenceFD, referenceDoublon);
		
		return noeud;
	}
}