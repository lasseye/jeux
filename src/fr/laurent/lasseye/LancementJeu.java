package fr.laurent.lasseye;

import java.io.IOException;



public class LancementJeu {
	
	public static void main(String[] args) throws IOException {
		
		/* 1. Configure l'application en fonction des propriétés du dossier "config.properties". */
		ConfigurationJeu configuration = new ConfigurationJeu();
			
		
		/* 2. Lance l'application en affichant d'abord la fenêtre "accueil". */
		FenetreAccueil fenetreAccueil = new FenetreAccueil("LA COMBINAISON SECRETE");
		fenetreAccueil.setVisible(true);
	
		}
	
}
