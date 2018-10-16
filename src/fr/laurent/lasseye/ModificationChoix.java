/*
 * La classe "ModifierChoix" permet d'annuler le transfert :
 * 		- des icones "chiffres" ou "couleurs" vers la zone "jeu" (en mode "Challenger");
 * 					--> retour à une case vide
 * 		- des icones "chiffres" ou "couleurs" vers la zone "combinaison secrète" (en mode "Défendeur");
 * 					--> retour à une case vide
 * 		- des icones "moins/égale/plus" ou "étoile pleine/ étoile vide/croix" vers la zone "analyse" (en mode "défendeur").
 * 					--> retour à un tiret vide
 */

package fr.laurent.lasseye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ModificationChoix extends ElementsJeu implements ActionListener {

	/* Attributs. */
	/* Permettent de paramétrer le jeu. */
	private int choixDuJeu, choixDuMode;
		
	/* Permettent de modifier les choix du joueur sur la zone "jeu" et la zone "combinaison secrète". */
	private int coordBoutonsJeu[], comptChoixJoueur[], comptCombiSecrete[];
	private Bouton ICONES[];
	private Bouton icone;
	
	/* Permet de valider les choix du joueur. */
	private Bouton OK;
	
	/* Permettent de renseigner le joueur. */
	private Bouton ecranMessages, MESSAGES[], ecranInvites[], INVITES[];
	
	/* Permettent de modifier les réponses du joueur sur la zone "validation". */
	private int coordBoutonsValidation[];
		
		
	
	/* Constructeur - Lance l'initialisation des attributs. */
	public ModificationChoix(int choixDuJeu, int choixDuMode, Bouton ICONES[], Bouton icone, Bouton OK, Bouton ecranInvites[], Bouton ecranMessages, 
		   Bouton MESSAGES[], int coordBoutonsJeu[], int comptChoixJoueur[], int coordBoutonsValidation[], int comptCombiSecrete[]) {
		
		this.choixDuJeu = choixDuJeu;
		this.choixDuMode = choixDuMode;
		this.ICONES = ICONES;
		this.icone = icone;
		this.OK = OK;
		this.ecranMessages = ecranMessages;
		this.MESSAGES = MESSAGES;
		this.ecranInvites = ecranInvites;
		this.coordBoutonsJeu = coordBoutonsJeu;
		this.comptChoixJoueur = comptChoixJoueur;
		this.coordBoutonsValidation = coordBoutonsValidation;
		this.comptCombiSecrete = comptCombiSecrete;
		
	}
	
	
	
	/* Interface - Gère les écouteurs de la zone "chiffres ou couleurs", de la zone "combinaion secrète" et de la zone "validation". */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		logger.info("--> Modification choix"); // -------> log
		
		/* 1. Désactive le bouton "ok" et le message qui l'accompagne. */
		desactiverOK(ICONES, OK, ecranInvites);
		
				
		/* 2. Affiche un message en fonction de l'état du jeu.
		      Avant la constitution de la combinaison secrète par le joueur. */
		if (comptCombiSecrete[0] == 0) { 
			
			/* SI le mode "Challenger" est activé. */
			if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) { 
				if  (coordBoutonsJeu[0] == 0) { // Au 1er tour.
					ajouterMessage1(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Choisissez chiffre ou couleur !"
				}
				else { // Après le 1er tour.
					ajouterMessage3(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Moins/Egale/Plus" ou "Placées/Présentes/Absentes".
				}
			}
			/* SINON en mode "Défendeur". */
			else {
				ajouterMessage2(ecranMessages, MESSAGES); // Affiche "Choisissez combinaison !".
			}
		}
		
		/* Après la constitution de la combinaison secrète par le joueur. */
		else { 
			ajouterMessage3(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Moins/Egale/Plus" ou "Placées/Présentes/Absentes".
		}
		
		
		/* 3. Modifie le choix du joueur. */	
		if (comptCombiSecrete[0] == 0) {		
			supprimerIcone(21); // Dans la zone "jeu" ou dans la zone "combinaison secrète".
		}	
		else {
			supprimerIcone(29); // Dans la zone "validation".
			
		}
	
		
		/* 4. Réinitialise 3 paramètres du jeu. */
		comptChoixJoueur[0] = comptChoixJoueur[0] - 1;
		coordBoutonsJeu[1] = 0;
		coordBoutonsValidation[1] = 0;
		
	}
		
	
	
	/*                                               *
	 * METHODES propres à la classe "ModifierChoix". *
	 *                                               */
		
	/* Supprime l'icone du bouton sélectionné et son écouteur. */
	public void supprimerIcone(int numeroIcone) {
		
		if (comptCombiSecrete[0] == 0) { // -------> log
			logger.info("Suppression : " + icone.getIcon());
		}
		else {
			switch(icone.getIcon().toString()) {				
				case "1" :
					logger.info("Suppression : Croix");
					break;
				case "2" :
					logger.info("Suppression : Etoile vide");
					break;
				case "3" :
					logger.info("Suppression : Etoile pleine");
					break;
			}
		}
		
		icone.setIcon(ICONES[numeroIcone].getIcon());
		icone.removeActionListener(icone.getActionListeners()[0]);
		
	}
		
}
