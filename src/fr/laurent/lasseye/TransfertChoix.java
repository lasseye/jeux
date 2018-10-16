/*
 * La classe "TransfertChoix" permet via ses sous-classes de transférer :
 *      - les icones "chiffres ou couleurs" vers la zone "jeu" (en mode "Challenger") :
 * 		       --> Proposition du joueur pour trouver la combinaison de l'ordinateur;
 *      - les icones "chiffres ou couleurs" vers la zone "combinaison secrète" (en mode "Défendeur") :
 * 			   --> Combinaison secrète choisie par le joueur;
 *      - les icones "moins/égale/plus" ou "étoile pleine/ étoile vide/croix" vers la zone "analyse" (en mode "défendeur") :
 * 		       --> Analyse du joueur en réponse aux propositions de l'ordinateur.
 */

package fr.laurent.lasseye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TransfertChoix extends ElementsJeu implements ActionListener {
	
	/* Attributs. */
	/* Permettent de paramétrer le jeu. */
	protected int choixDuJeu, choixDuMode, comptModeDuel, tailleCombi;
		
	/* Permettent de vérifier la présence de doublons avant le transfert des icones. */
	protected int comptDoublon, coordBoutonsJeu[];
	protected Bouton ICONES[], icone, zoneJeu[][];
		
	/* Permet de valider les choix du joueur. */
	protected Bouton OK;
	
	/* Permettent d'informer le joueur sur l'état du jeu en cours. */
	protected Bouton ecranInvites[], INVITES[], ecranMessages, MESSAGES[]; 
	
	/* Permettent de stocker les propositions du joueur (en mode "Challenger") ou sa combinaison secrète (en mode "Défendeur"). */
	protected int comptChoixJoueur[], comptCombiSecrete[];
	protected Bouton choixJoueur[], combiSecrete[][];
		
	/* Permettent de gérer le transfert des icones "validation" vers la zone "validation". */
	protected int comptMoinsEgalePlus, comptEtoiles, coordBoutonsValidation[];
	protected Bouton zoneValidation[][];
	
	
	
	/* Constructeur - Lance l'initialisation des attributs. */
	public TransfertChoix(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi, Bouton ICONES[], Bouton icone, Bouton OK, Bouton ecranMessages,
	       Bouton MESSAGES[], Bouton ecranInvites[], Bouton INVITES[], Bouton zoneJeu[][], int coordBoutonsJeu[], Bouton choixJoueur[], int comptChoixJoueur[],
		   Bouton zoneValidation[][], int coordBoutonsValidation[], Bouton combiSecrete[][], int comptCombiSecrete[]) {

		this.choixDuJeu = choixDuJeu;
		this.choixDuMode = choixDuMode;
		this.comptModeDuel = comptModeDuel;
		this.tailleCombi = tailleCombi;
		this.ICONES = ICONES;
		this.icone = icone;
		this.OK = OK;
		this.ecranMessages = ecranMessages;
		this.MESSAGES = MESSAGES;
		this.ecranInvites = ecranInvites;
		this.INVITES = INVITES;
		this.zoneJeu = zoneJeu;
		this.coordBoutonsJeu = coordBoutonsJeu;
		this.choixJoueur = choixJoueur;
		this.comptChoixJoueur = comptChoixJoueur;
		this.zoneValidation = zoneValidation;
		this.coordBoutonsValidation = coordBoutonsValidation;
		this.combiSecrete = combiSecrete;
		this.comptCombiSecrete = comptCombiSecrete;
	
	}

	
	
	/* Interface - Gère les écouteurs de la zone "chiffres ou couleurs" ou de la zone "validation". */
	@Override
	public void actionPerformed(ActionEvent e) {

		
		if (comptChoixJoueur[0] < tailleCombi) {

			/* 1. Affiche un message en fonction de l'état du jeu.
		       a. Avant la constitution de la combinaison secrète par le joueur. */
			if (comptCombiSecrete[0] == 0) { 
			
				/* SI le mode "Challenger" est activé. */
				if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) { 
								
					if  (coordBoutonsJeu[0] == 0) { // Au 1er tour.
						ajouterMessage1(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Choisissez chiffre ou couleur !"
					}
					else { // Après le 1er tour.
						ajouterMessage3(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Moins/Egale/Plus" ou "Placées/Présentes/Absentes"
					}
					
				}
			
				/* SINON en mode "Défendeur". */
				else {
					ajouterMessage2(ecranMessages, MESSAGES); // Affiche "Choisissez combinaison !"
				}
			}
		
			/* b. Après la constitution de la combinaison secrète par le joueur. */
			else { 
				ajouterMessage3(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Moins/Egale/Plus" ou "Placées/Présentes/Absentes"
			}
		
		
			/* 2. Transfère les icones en fonction de l'état du jeu. 
		   	   a. Avant la constitution de la combinaison secrète par le joueur. */
			if (comptCombiSecrete[0] == 0) {
			
				/* SI le mode "Challenger" est activé - Transfère vers la zone "jeu". */
				if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) {
				
					logger.info("--> Transfert vers zone jeu"); // ------->log
				
					new TransfertVersJeu(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, icone, OK, 
					    ecranMessages, MESSAGES, ecranInvites, INVITES, zoneJeu, coordBoutonsJeu, choixJoueur,
						comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiSecrete, comptCombiSecrete);
				}
			
				/* SINON en mode "Défendeur" - Transfère vers la zone "combinaison secrète". */
				else { 
				
					logger.info("--> Transfert vers combinaison secrète"); // -------> log
				
					new TransfertVersCombi(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, icone, OK, 
					    ecranMessages, MESSAGES, ecranInvites, INVITES, zoneJeu, coordBoutonsJeu, choixJoueur, 
				        comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiSecrete, comptCombiSecrete);				
				}
			}
		
			/* b. Après la constitution de la combinaison secrète par le joueur - Transfère vers la zone "validation". */
			else {
			
				logger.info("--> Transfert vers zone validation"); // -------> log
			
				new TransfertVersValidation(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, icone, OK, 
				    ecranMessages, MESSAGES, ecranInvites, INVITES, zoneJeu, coordBoutonsJeu, choixJoueur,
			        comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiSecrete, comptCombiSecrete);
			}
		}
		else {
			logger.info("LIGNE COMPLETE"); // -------> log
		}
	
}	
	
	
	
	/*                                                           *
	 * Les METHODES propres à la classe "TranfertChoix" et à ses *
	 * sous-classes : "TransfertVerJeu", "TransfertVerCombi" et  *
	 * "TransfertVersValidation".                                *
	 *                                                           */
		
	/* Détecte les cases vides dans la zone "jeu", dans la zone "combinaison" ou dans la zone "validation". */
	protected void detecterCaseVide(Bouton boutons[][], int coordBoutons[], int numeroIcone) {
		while (boutons[coordBoutons[0]][coordBoutons[1]].getIcon().toString() != (ICONES[numeroIcone].getIcon().toString())) {	
			coordBoutons[1] = coordBoutons[1] + 1;
		}
	}
	
		
	/* Détecte les doublons dans la zone "jeu" ou dans la zone "combinaison". */
	protected void detecterDoublon(Bouton icone, Bouton boutons[][], int coordBoutons[]) {
		for (int i = 0; i < tailleCombi; i++) {	
			if (icone.getIcon().toString() == boutons[coordBoutons[0]][i].getIcon().toString())
				comptDoublon++;	
		}
	}
	
	
	/* Affiche un message en cas de doublon. */
	protected void afficherMessage() {
		if (choixDuJeu == 0) ecranMessages.setIcon(MESSAGES[4].getIcon()); // Affiche "Pas deux fois le même chiffre !".
		if (choixDuJeu == 10) ecranMessages.setIcon(MESSAGES[5].getIcon()); // Affiche "Pas deux fois la même couleur !".
	}
	
	
	/* Transfère les icones "chiffres ou couleurs" vers la zone "jeu". */
	protected void transfererIcone(Bouton boutons[][], int coordBoutons[]) {
		
		boutons[coordBoutons[0]][coordBoutons[1]].setIcon(icone.getIcon());
		choixJoueur[coordBoutons[1]] = icone; // Stocke les choix du joueur pour une comparaison ultérieure.
		
		if (comptCombiSecrete[0] == 0) { // -------> log
			logger.info("Choix : " + icone.getIcon() + " en " + coordBoutonsJeu[0] + coordBoutonsJeu[1]);
		}
		else {
			if (choixDuJeu == 0) {
				logger.info("Choix : " + icone.getIcon() + " en " + coordBoutonsValidation[0] + coordBoutonsValidation[1]);
			}
			else {
				switch(icone.getIcon().toString()) {				
				case "1" :
					logger.info("Choix : Croix en " + coordBoutonsValidation[0] + coordBoutonsValidation[1]);
					break;
				case "2" :
					logger.info("Choix : Etoile vide en " + coordBoutonsValidation[0] + coordBoutonsValidation[1]);
					break;
				case "3" :
					logger.info("Choix : Etoile pleine en " + coordBoutonsValidation[0] + coordBoutonsValidation[1]);
					break;
				}
			}
		}
	}
	
	
	/* Pose un écouteur "ModifierChoix()". */
	protected void poserEcouteur(Bouton boutons[][], int coordBoutons[]) {
		boutons[coordBoutons[0]][coordBoutons[1]].addActionListener(new ModificationChoix(choixDuJeu, choixDuMode, ICONES, boutons[coordBoutons[0]][coordBoutons[1]],
		OK, ecranInvites, ecranMessages, MESSAGES, coordBoutonsJeu, comptChoixJoueur, coordBoutonsValidation, comptCombiSecrete));
	}
	
	
	/* Active le bouton "ok". */
	protected void activerOK() {	
		ecranInvites[0].setIcon(INVITES[0].getIcon()); // Affiche "Cliquez sur".
		ecranInvites[1].setIcon(INVITES[1].getIcon()); // Affiche "pour valider !".
		ecranMessages.setIcon(ICONES[22].getIcon());
		OK.setVisible(true);
	}
		
}
