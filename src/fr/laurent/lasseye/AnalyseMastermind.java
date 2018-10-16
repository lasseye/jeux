/*
 * La classe "AnalyseMastermind" analyse uniquement "Le Mastermind". Elle détermine :
 *      - les couleurs placées;
 *      - les couleurs présentes (mais non placées); 
 *      - les couleurs absentes. 
 * Puis en fonction du résultat, elle analyse :
 *      - si la partie est gagnée;
 *      - si la partie doit continuer;
 *      - si la partie est perdue. 
 */     

package fr.laurent.lasseye;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JWindow;

public class AnalyseMastermind extends AnalyseChoix {

	public AnalyseMastermind(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi, int nbreChifCoul, Random nbreAlea, int index, ArrayList<Integer> indexAleas, int nbreEssais,
		   Bouton[] ICONES, Bouton OK, Bouton ecranMessages, Bouton[] MESSAGES, Bouton[] ecranInvites, Bouton[] INVITES, Bouton[] curseur,
		   Bouton[][] zoneJeu, int[] coordBoutonsJeu, Bouton[] choixJoueur, int[] comptChoixJoueur, Bouton[][] zoneAnalyse, int[] coordBoutonsAnalyse, Bouton[] combiOrdi,
		   Bouton[] SOLUTION, Bouton[][] combiSecrete, int[] comptCombiSecrete, Bouton[] validJoueur, FenetreJeu[] fenetresJeu, JWindow[] fenetresInvitation) {
		     
		   
		super(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu,
			  coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneAnalyse, coordBoutonsAnalyse, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, validJoueur, fenetresJeu, fenetresInvitation);
		
		
		
		logger.info("--> Analyse le Mastermind"); // -------> log
		
		/* 1. Détermine les couleurs placées. */		
		for (int i = 0; i < tailleCombi; i++) {
			if(choixJoueur[i].getIcon().toString() == (combiOrdi[i].getIcon().toString())) 
				comptPlacees++;
			
			logger.info(combiOrdi[i].getIcon() + "----------" + choixJoueur[i].getIcon()); // -------> log
		
		}

		
		/* 2. Détermine les couleurs présentes. */
		for (int i = 0; i < tailleCombi; i++) {	
			for (int j = 0; j < tailleCombi; j++) {
				if (choixJoueur[i].getIcon().toString() == (combiOrdi[j].getIcon().toString()))
					comptPresentes++;
			}
		}	
		comptPresentes = comptPresentes - comptPlacees;
		
		
		/* 3. Détermine les couleurs absentes. */
		comptAbsentes = tailleCombi - (comptPresentes + comptPlacees);


		/* 4. Affiche les bonnes et/ou mauvaises réponses. */
		for (int i = 0; i < comptPlacees; i++) { // Affiche autant d'étoiles pleines que de couleurs bien placées.
			zoneAnalyse[coordBoutonsAnalyse[0]][coordBoutonsAnalyse[1]].setIcon(ICONES[26].getIcon());
			coordBoutonsAnalyse[1] = coordBoutonsAnalyse[1] + 1;
		}
		for (int i = 0; i < comptPresentes; i++) { // Affiche autant d'étoiles vides que de couleurs présentes.
			zoneAnalyse[coordBoutonsAnalyse[0]][coordBoutonsAnalyse[1]].setIcon(ICONES[27].getIcon());
			coordBoutonsAnalyse[1] = coordBoutonsAnalyse[1] + 1;
		}
		for (int i = 0; i < comptAbsentes; i++) { // Affiche autant de croix que de couleurs absentes.
			zoneAnalyse[coordBoutonsAnalyse[0]][coordBoutonsAnalyse[1]].setIcon(ICONES[28].getIcon());
			coordBoutonsAnalyse[1] = coordBoutonsAnalyse[1] + 1;
		}
	
		logger.info(comptPlacees + " placées" + "   " + comptPresentes + " présentes" + "   " + comptAbsentes + " absentes"); // -----> log
		
		
		/* 5. Analyse l'état du jeu.
		      SI la partie est gagnée pour le joueur. */
		if (comptPlacees == tailleCombi || comptEgales == tailleCombi ) {
			
			afficherResultat(ecranMessages, MESSAGES, 9, SOLUTION, fenetresInvitation);
		
			logger.info("C'EST GAGNE POUR LE JOUEUR !"); // -------> log
			
			/* Supprime les écouteurs de la zone "chiffres ou couleurs" car elle n'a plus d'utilité à ce stade du jeu. */
			supprimerEcouteur(choixDuJeu, ICONES);	
			
		}
		
		/* SINON SI la partie continue. */
		else if (coordBoutonsJeu[0] < (nbreEssais - 1) && coordBoutonsJeu[1] < tailleCombi) {
			
			continuerPartie();
		
			logger.info("LA PARTIE CONTINUE !"); // -------> log
			
			/* Bascule d'une fenêtre à l'autre en mode "Duel". */
			if (choixDuMode == 2) {
				
				logger.info("Choix du mode : Duel --> fenêtre Défendeur"); // -------> log
				
				basculerDeFenetres(false, true, false, true);
				
			}	
			
		}
		
		/* SINON la partie est perdue pour le joueur. */
		else {
		
			afficherResultat(ecranMessages, MESSAGES, 10, SOLUTION, fenetresInvitation);
			
			logger.info("C'EST PERDU POUR LE JOUEUR !"); // -------> log
			
			/* Supprime les écouteurs de la zone "chiffres ou couleurs" car elle n'a plus d'utilité à ce stade du jeu. */
			supprimerEcouteur(choixDuJeu, ICONES);
			
		}
		
	}

}
