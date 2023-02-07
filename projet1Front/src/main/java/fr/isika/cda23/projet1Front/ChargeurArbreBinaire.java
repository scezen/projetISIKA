package fr.isika.cda23.projet1Front;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fr.isika.projet1Front.modele.ArbreBinaire;
import fr.isika.projet1Front.modele.FichierBinaire;
import fr.isika.projet1Front.modele.Stagiaire;


public class ChargeurArbreBinaire extends FichierBinaire {

	private static List<Stagiaire> listeStagiaires;
	private static ArbreBinaire arbreBinaire;
	private static FileReader fr;
	private static BufferedReader br;

	public ChargeurArbreBinaire() throws IOException {

		super();

		listeStagiaires = new ArrayList<>();

		// Ouverture du fichier STAGIAIRES.DON qui sera copié dans le fichier
		// STAGIAIRES.bin

		 fr = new FileReader(
				"W:\\ISIKA\\STAGIAIRES.DON");
		 br = new BufferedReader(fr);
		 
		 this.chargeArbre();

		// this.supprimePosition(0, arbreBinaire);

		// Fermerture de tous les flux
		br.close();
		fr.close();
		this.close();
	}

	public ChargeurArbreBinaire(String nom, String prenom, String departement, String universite, String date)
			throws IOException {

		int reference = (int) (this.length() / TAILLE_NOEUD) + 1;
		Stagiaire stagiaire = new Stagiaire(nom, prenom, departement, universite, date, reference);
		arbreBinaire.ajouteNoeud(stagiaire);
	}
	
public  void chargeArbre() throws IOException {
		
		if (br.ready() && this.length() == 0) {

			arbreBinaire = new ArbreBinaire();

			String line = br.readLine();

			// Récupération des attributs de chaque stagiaire pour remplir la List
			// listeStagiaires
			if (line != null && !line.startsWith("*")) {

				String nom = line.trim();
				String prenom = br.readLine().trim();
				String departement = br.readLine().trim();
				String universite = br.readLine().trim();
				String annee = br.readLine().trim();

				Stagiaire premierStagiaireExtrait = new Stagiaire(nom, prenom, departement, universite, annee, 0);

				listeStagiaires.add(premierStagiaireExtrait);
			}

			int compteur = 1;
			while ((line = br.readLine()) != null && compteur < 7) {

				if (line.startsWith("*")) {
					continue;
				}

				String nom = line.trim();
				String prenom = br.readLine().trim();
				String departement = br.readLine().trim();
				String universite = br.readLine().trim();
				String annee = br.readLine().trim();

				Stagiaire stagiaireExtrait = new Stagiaire(nom, prenom, departement, universite, annee, compteur);

				listeStagiaires.add(stagiaireExtrait);
				compteur++;
			}

			System.out.println("Creation du fichier binaire...");
			
			for (Stagiaire stagiaire : listeStagiaires) {
				arbreBinaire.ajouteNoeud(stagiaire);
			}

		} else {
			System.out.println("Chargement du fichier binaire...");
			arbreBinaire = new ArbreBinaire(this.recupereNoeud(0));
			
			for (int i = 0; i < this.length() / TAILLE_NOEUD; i++) {
				listeStagiaires.add(this.recupereNoeud(i).getStagiaire());
			}
		}
	}

public ArbreBinaire supprimePosition(int position, ArbreBinaire arbre) throws IOException {
	arbre.getRacine().supprimeNoeud(this.recupereNoeud(position).getStagiaire());
	return arbre;
}

public static List<Stagiaire> getListeStagiaires() {
	return listeStagiaires;
}

public static void setListeStagiaires(List<Stagiaire> listeStagiaires) {
	ChargeurArbreBinaire.listeStagiaires = listeStagiaires;
}


public static void setArbreBinaire(ArbreBinaire arbreBinaire) {
	ChargeurArbreBinaire.arbreBinaire = arbreBinaire;
}


public  ArbreBinaire getArbreBinaire() throws IOException {
	return arbreBinaire;
}

@Override
public void ecris(Stagiaire stagiaire) {
	// TODO Auto-generated method stub
	
}
}
