package fr.isika.projet1Front.interfaces;

public interface InterfaceTaille {

	// Déclaration du nombre de caractère maximum pour chaque attirbut dans le
	// fichier STAGIAIRES.bin
	public final static int TAILLE_NOM = 20;
	public final static int TAILLE_PRENOM = 15;
	public final static int TAILLE_DEPARTEMENT = 2;
	public final static int TAILLE_UNIVERSITE = 10;
	public final static int TAILLE_ANNEE = 4;

	// Taille maximum en octet que doit avoir mon chaque objet stagiaire dans le
	// fichier STAGIARES.bin
	public final static int TAILLE_NOEUD = (TAILLE_NOM + TAILLE_PRENOM + TAILLE_DEPARTEMENT + TAILLE_UNIVERSITE
			+ TAILLE_ANNEE) * 2 + 16;
	public final static int REF_DOUBLON = TAILLE_NOEUD - 4;
	public final static int REF_FD = TAILLE_NOEUD - 8;
	public final static int REF_FG = TAILLE_NOEUD - 12;
	public final static int REF_NOEUD = TAILLE_NOEUD - 16;
}
