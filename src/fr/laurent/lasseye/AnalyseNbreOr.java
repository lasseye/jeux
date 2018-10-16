/* La classe "AnalyseNbreOr" analyse uniquement "Le Nombre d'Or". Elle détermine si chaque
 * chiffre est plus petit, égale ou plus grand que ceux de la combinaison secrète.
 * Puis en fonction du résultat, elle analyse :
 *      - si la partie est gagnée;
 *      - si la partie doit continuer;
 *      - si la partie est perdue.
 */

package fr.laurent.lasseye;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JWindow;



public class AnalyseNbreOr extends AnalyseChoix {

	public AnalyseNbreOr(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi, int nbreChifCoul, Random nbreAlea, int index, ArrayList<Integer> indexAleas, int nbreEssais,
		   Bouton[] ICONES, Bouton OK, Bouton ecranMessages, Bouton[] MESSAGES, Bouton[] ecranInvites, Bouton[] INVITES, Bouton[] curseur,
		   Bouton[][] zoneJeu, int[] coordBoutonsJeu, Bouton[] choixJoueur, int[] comptChoixJoueur, Bouton[][] zoneAnalyse, int[] coordBoutonsAnalyse, Bouton[] combiOrdi,
		   Bouton[] SOLUTION, Bouton[][] combiSecrete, int[] comptCombiSecrete, Bouton[] validJoueur, FenetreJeu[] fenetresJeu, JWindow[] fenetresInvitation) {
		     
		   
		super(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu,
			  coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneAnalyse, coordBoutonsAnalyse, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, validJoueur, fenetresJeu, fenetresInvitation);
	
		
		
		logger.info("--> Analyse le Nombre d'Or"); // -------> log
		
		/* 1. Affche les bonnes ou mauvaises nouvelles. */					
		for (int i = 0; i < tailleCombi ; i++) {
			
			logger.info(combiOrdi[i].getIcon().toString() + "----------" + choixJoueur[i].getIcon().toString()); // -------> log
			
			/* SI les chiffres sont égaux - Affiche le signe "=". */
			if (choixJoueur[i].getIcon().toString() == combiOrdi[i].getIcon().toString()) {
				zoneAnalyse[coordBoutonsAnalyse[0]][coordBoutonsAnalyse[1]].setIcon(ICONES[24].getIcon());
				coordBoutonsAnalyse[1] = coordBoutonsAnalyse[1] + 1;
				comptEgales++;
				
				logger.info("C'est " + ICONES[24].getIcon().toString()); // -------> log
				
			}
			
			/* SINON SI le chiffre est trop petit - Affiche le signe "+". */
			else if (Integer.parseInt(choixJoueur[i].getIcon().toString()) < Integer.parseInt(combiOrdi[i].getIcon().toString())) {
				zoneAnalyse[coordBoutonsAnalyse[0]][coordBoutonsAnalyse[1]].setIcon(ICONES[25].getIcon());
				coordBoutonsAnalyse[1] = coordBoutonsAnalyse[1] + 1;
				
				logger.info("C'est " + ICONES[25].getIcon().toString()); // -------> log
			
			}
			
			/* SINON le chiffre est trop grand - Affiche le signe "-". */
			else {
				zoneAnalyse[coordBoutonsAnalyse[0]][coordBoutonsAnalyse[1]].setIcon(ICONES[23].getIcon());
				coordBoutonsAnalyse[1] = coordBoutonsAnalyse[1] + 1;
				
				logger.info("C'est " + ICONES[23].getIcon().toString()); // -------> log

			}
		}
		
		
		/* 2. Analyse l'état du jeu.
	       SI la partie est gagnée pour le joueur. */
		if (comptEgales == tailleCombi) {
			
			afficherResultat(ecranMessages, MESSAGES, 9, SOLUTION, fenetresInvitation);

			logger.info("C'EST GAGNE !"); // -------> log
			
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
	
			logger.info("C'EST PERDU !"); // -------> log
		
			/* Supprime les écouteurs de la zone "chiffres ou couleurs" car elle n'a plus d'utilité à ce stade du jeu. */
			supprimerEcouteur(choixDuJeu, ICONES);	

		}
	
	}
	
}
	