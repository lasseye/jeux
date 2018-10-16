/* La classe "AnalyseValidation" analyse uniquement la validation faite par le joueur
 * aux propositions de l'ordinateur (en mode "Défendeur uniquement") quel que soit le
 * jeu choisi ("Le Nombre d'Or" ou "Le Mastermind"). 
 * Puis en fonction du résultat, elle analyse :
 *      - si la partie est gagnée;
 *      - si la partie doit continuer;
 *      - si la partie est perdue.
 */

package fr.laurent.lasseye;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JWindow;



public class AnalyseValidation extends AnalyseChoix {

	public AnalyseValidation(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi, int nbreChifCoul, Random nbreAlea, int index, ArrayList<Integer> indexAleas, int nbreEssais,
		   Bouton[] ICONES, Bouton OK, Bouton ecranMessages, Bouton[] MESSAGES, Bouton[] ecranInvites, Bouton[] INVITES, Bouton[] curseur,
		   Bouton[][] zoneJeu, int[] coordBoutonsJeu, Bouton[] choixJoueur, int[] comptChoixJoueur, Bouton[][] zoneValidation, int[] coordBoutonsValidation, Bouton[] combiOrdi,
		   Bouton[] SOLUTION, Bouton[][] combiSecrete, int[] comptCombiSecrete, Bouton[] iconesValidation, FenetreJeu[] fenetresJeu, JWindow[] fenetresInvitation) {
		     
		   
		super(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu,
			  coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation);
		
		
		
		logger.info("--> Analyse la validation du joueur"); // -------> log
		
		/* 1. Comptabilise les bonnes et/ou mauvaises réponses. */					
		for (int i = 0; i < tailleCombi ; i++) {
			
			if (choixDuJeu == 0) { // -------> log
				logger.info(combiOrdi[i].getIcon() + "----------" + choixJoueur[i].getIcon());
			}
			else {
				switch(choixJoueur[i].getIcon().toString()) {				
				case "1" :
					logger.info(combiOrdi[i].getIcon() + "----------" + "Croix");
					break;
				case "2" :
					logger.info(combiOrdi[i].getIcon() + "----------" + "Etoile vide");
					break;
				case "3" :
					logger.info(combiOrdi[i].getIcon() + "----------" + "Etoile pleine");
					break;
				}
			}
			
			if ((zoneValidation[coordBoutonsValidation[0]][i].getIcon().toString() == ICONES[24].getIcon().toString()) || 
			   (zoneValidation[coordBoutonsValidation[0]][i].getIcon().toString() == ICONES[26].getIcon().toString())) {
					comptEgales++;
			}
		}
		
		
		/* 2. Analyse l'état du jeu.
	       SI la partie est gagnée pour l'ordinateur. */
		if (comptEgales == tailleCombi) {
			
			afficherResultat(ecranMessages, MESSAGES, 10, SOLUTION, fenetresInvitation);
			
			/* Supprime les écouteurs de la zone "validation" car elle n'a plus d'utilité à ce stade du jeu. */
			for (int i = 0 ; i < 3; i++) {
				iconesValidation[i].removeActionListener(iconesValidation[i].getActionListeners()[0]);
			}
			
			logger.info("C'EST PERDU POUR LE JOUEUR !"); // -------> log
		
		}
		
		/* SINON SI la partie continue. */
		else if (coordBoutonsValidation[0] < (nbreEssais - 1) && coordBoutonsValidation[1] < tailleCombi) {
			
			continuerPartie();
			
			logger.info("LA PARTIE CONTINUE !"); // -------> log
			
			/* Demane à l'ordinateur de proposer un nouvelle combinaison. */
			indexAleas.clear();
			Combinaison combinaison = new Combinaison(choixDuJeu, choixDuMode, comptModeDuel,  zoneJeu, coordBoutonsJeu, combiOrdi, combiSecrete);
			
			/* Bascule d'une fenêtre à l'autre. */
			if (choixDuMode == 2) {
				
				logger.info("Choix du mode : Duel --> fenêtre Challenger"); // -------> log
				
				basculerDeFenetres(true, false, true, false);
				
			}
			
		}
		
		/* SINON La partie est perdue pour l'ordinateur. */
		else {
			
			afficherResultat(ecranMessages, MESSAGES, 9, SOLUTION, fenetresInvitation);
			
			logger.info("C'EST GAGNE POUR LE JOUEUR !"); // -------> log
			
			/* Supprime les écouteurs de la zone "validation" car elle n'a plus d'utilité à ce stade du jeu. */
			for (int i = 0 ; i < 3; i++) {
				iconesValidation[i].removeActionListener(iconesValidation[i].getActionListeners()[0]);
				
			}
		
		}
		
	}
	
}
	