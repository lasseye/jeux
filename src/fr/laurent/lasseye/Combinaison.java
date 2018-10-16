/*
 * La classe "Combinaison" génère une combinaison de chiffres ou de couleurs :
 *      - en mode "Challenger", elle est tranférée dans la zone "combinaison secrète" :
 *      		--> c'est la combinaison que le joueur doit découvrir;
 *      - en mode "Défendeur", elle est transféré dans la zone "jeu" :
 *      		--> ce sont les propositions faites par l'ordinateur.
 */

package fr.laurent.lasseye;

import java.awt.Color;
import javax.swing.ImageIcon;



public class Combinaison extends ElementsJeu{

	public Combinaison(int choixDuJeu, int choixDuMode, int comptModeDuel, Bouton zoneJeu[][], int coordBoutonsJeu[], Bouton combiOrdi[], Bouton combiSecrete[][]) {

		/* 1. Crée un tableau d'objets pour le stockage de la combinaison. */
		for (int i = 0; i < 6; i++) {
			combiOrdi[i] = new Bouton(new ImageIcon("22.FondGris"));
			combiOrdi[i].formaterBouton(0, 0, 35, 35, Color.DARK_GRAY);	
		}
		
				
		/* 2. Crée la combinaison et la stocke pour une comparaison ultérieure. */
		for (int i = 0; i < tailleCombi ; i++) {
			
			if (choixDuJeu == 0) {
				index = nbreAlea.nextInt(nbreChifCoul);
			}
			else {
				do {
					index = nbreAlea.nextInt(nbreChifCoul); 
				}
				while (indexAleas.contains(index)); 
			}
				indexAleas.add(index); 						
				combiOrdi[i].setIcon(ICONES[index + choixDuJeu].getIcon());
		}
		
		
		/* 3. Vérifie pour "le Nombre d'Or" si le 1er chiffre de la combinaison n'est pas un zéro - si oui, affiche un "1". */
		if (combiOrdi[0].getIcon().toString() == "0") {
			System.out.println("a");
			combiOrdi[0].setIcon(ICONES[0].getIcon());
		}
		
		
		
		if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) { // -------> log
			logger.info("LA COMBINAISON SECRETE EST :");
		}
		else {
			logger.info("L'ORDINATEUR PROPOSE :");
		}	
		for (int i = 0; i < tailleCombi; i++) {
			logger.info(combiOrdi[i].getIcon().toString());
		}
								
	
		/* 4. SI le mode "Challenger" est activé - Affiche le choix de l'ordinateur dans la zone "combiSecrete". */
		if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) {
			for (int i = 0 ; i < tailleCombi; i++) {
				combiSecrete[0][i].setIcon(combiOrdi[i].getIcon());
			}
		}
		/* SINON en mode "Défendeur" - Affiche le choix de l'ordinateur dans la zone "jeu". */
		else {
			for (int i = 0; i < tailleCombi ; i++) {
			zoneJeu[coordBoutonsJeu[0]][i].setIcon(combiOrdi[i].getIcon());
			}
		}							
				
	}
		
}
	