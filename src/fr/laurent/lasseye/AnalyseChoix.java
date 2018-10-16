/*
 * La classe "AnalyseChoix" permet via ses sous-classes d'analyser :
 *      - d'analyser les propositions du joueur pour "Le Mastermind" :
 *      		--> mode "Challenger";
 *      - d'analyser les propositions du joueur pour "Le Nombre d'Or" :
 *      		--> mode "Challenger";
 *      - d'analyser les validations du joueur aux propositions de l'ordinateur :
 *      		--> mode "Défendeur".
 */

package fr.laurent.lasseye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JWindow;



public class AnalyseChoix extends ElementsJeu implements ActionListener {
	
	
	/* Attributs. */
	/* Permettent d'agencer les fenêtres "jeu" (en mode "Challenger" et en mode "Défendeur"). */
	protected int choixDuJeu, choixDuMode, comptModeDuel, nbreChifCoul, tailleCombi, nbreEssais;
	
	/* Permettent de créer une combinaisons secrète de façon aléatoire puis de la stocker. */
	protected int index = 0;
	protected Random nbreAlea = new Random();
	protected ArrayList<Integer> indexAleas = new ArrayList<>();
	protected Bouton combiOrdi[];
	
	/* Permettent de comparer les choix du joueur avec ceux de l'ordinateur. */
	protected int comptCombiSecrete[], comptEgales = 0, comptPlacees = 0, comptPresentes = 0, comptAbsentes = 0;
	protected Bouton choixJoueur[], combiSecrete[][];
	
	/* Permet de valider les choix du joueur */
	protected Bouton OK;
	
	/* Permettent d'informer le joueur sur l'état du jeu en cours. */
	protected Bouton ecranInvites[], INVITES[], ecranMessages, MESSAGES[];
	
	/* Permet d'indiquer la ligne en cours de jeu. */
	protected Bouton curseur[];
	
	/* Permettent de recevoir les choix du joueur (en mode "Challenger") ou de l'ordinateur ("en mode "Défendeur"). */
	protected int coordBoutonsJeu[], comptChoixJoueur[];
	protected Bouton zoneJeu[][];	
	
	/* Permettent de recevoir l'analyse de l'ordinateur (en mode "Challenger") ou du joueur (en mode "défendeur"). */
	protected int coordBoutonsValidation[];
	protected Bouton ICONES[], zoneValidation[][];
	
	/* Permet de cacher la combinaison secrète de l'ordinateur. */
	protected Bouton SOLUTION[];
	
	/* Permet au joueur d'analyser les choix de l'ordinateur. */
	protected final Bouton iconesValidation[];

	/* Permettent de construire et de stocker les fenêtres "jeu" et les panneaux "invitation" au jeu. */
	protected FenetreJeu fenetresJeu[] = new FenetreJeu[2];
	protected JWindow fenetresInvitation[] = new JWindow[2];
	
	
	
	/* Constructeur - Lance l'initiation des attributs. */
	public AnalyseChoix(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi, int nbreChifCoul, Random nbreAlea, int index, ArrayList<Integer> indexAleas, int nbreEssais, Bouton ICONES[], Bouton OK, Bouton ecranMessages,
		   Bouton MESSAGES[], Bouton ecranInvites[], Bouton INVITES[], Bouton curseur[], Bouton zoneJeu[][], int coordBoutonsJeu[], Bouton choixJoueur[], int comptChoixJoueur[], Bouton zoneValidation[][], int coordBoutonsValidation[],
		   Bouton combiOrdi[], Bouton SOLUTION[], Bouton combiSecrete[][], int comptCombiSecrete[], Bouton iconesValidation[],FenetreJeu fenetresJeu[], JWindow fenetresInvitation[]) {
		
		
		this.choixDuJeu = choixDuJeu;
		this.choixDuMode = choixDuMode;
		this.comptModeDuel = comptModeDuel;
		this.tailleCombi = tailleCombi;
		this.nbreChifCoul = nbreChifCoul;
		this.nbreAlea = nbreAlea;
		this.index = index;
		this.indexAleas = indexAleas;
		this.nbreEssais = nbreEssais;
		this.ICONES = ICONES;
		this.OK = OK;
		this.ecranMessages = ecranMessages;
		this.MESSAGES = MESSAGES;
		this.ecranInvites = ecranInvites;
		this.INVITES = INVITES;
		this.curseur = curseur;
		this.zoneJeu = zoneJeu;
		this.coordBoutonsJeu = coordBoutonsJeu;
		this.choixJoueur = choixJoueur;
		this.comptChoixJoueur = comptChoixJoueur;
		this.zoneValidation = zoneValidation;
		this.coordBoutonsValidation = coordBoutonsValidation;
		this.combiOrdi = combiOrdi;
		this.SOLUTION = SOLUTION;
		this.combiSecrete = combiSecrete;
		this.comptCombiSecrete = comptCombiSecrete;
		this.iconesValidation = iconesValidation;
		this.fenetresJeu = fenetresJeu;
		this.fenetresInvitation = fenetresInvitation;
		
	}
	
	
	
	/* Interface - Gère l'écouteur du bouton "ok". */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		logger.info("--> OK actionné !"); // -------> log
		
		/* 1. Désactive le bouton "ok" (et le message qui l'accompagne) puis affiche un message pour aider le joueur. */ 
		desactiverOK(ICONES, OK, ecranInvites);
		
		ajouterMessage3(choixDuJeu, ecranMessages, MESSAGES); // Affiche "-/=/+" ou "Placées/Présentes/Absentes"

		
		/* 2. SI le mode "Challenger" est sélectionné. */ 
		if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) {
			
			/* a. Supprime les écouteurs de la ligne en cours de vérification. */
			supprimerEcouteur(zoneJeu, coordBoutonsJeu);
						   
			/* b. SI le jeu "Le Nbre d'Or" est sélectionné - Analyse "Le Nombre d'Or". */
			if (choixDuJeu == 0) {
				new AnalyseNbreOr(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES,
				    OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneValidation, 
				    coordBoutonsValidation, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation);  
			}	
			/* SINON - Analyse "Le Mastermind". */
			else {
				new AnalyseMastermind(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES,
				    OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneValidation, 
				    coordBoutonsValidation, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation);  	
			}
												
		}
		/* SINON en mode "Défendeur". */
		else {
			
			/* a. SI la combinaison secrète du joueur vient juste d'être constituée - Supprime les écouteurs de la combinaison. */
			if (comptCombiSecrete[0] == 0) {
				supprimerEcouteur(combiSecrete, coordBoutonsJeu);
			}
			/* SINON la combinaison secrète du joueur est déjà validée  - Supprime les écouteurs de l'analyse du joueur. */
			else {
				supprimerEcouteur(zoneValidation, coordBoutonsValidation);
			}	
			
			/* b. Analyse la zone "validation". */
			new AnalyseValidation(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES,
				OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneValidation, 
				coordBoutonsValidation, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation);
					
		}
				
	}

	
	
	/*                                                          *
	 * Les METHODES propres à la classe "AnalyseChoix" et à ses *
	 * sous-classes : "AnalyseNbreOr", "AnalyseMastermind" et   *
	 * "AnalyseValidation".                                     *
	 *                                                          */
	
	/* Méthode 1 - Affiche le résultat : gagné ou perdu. */
	public void afficherResultat(Bouton ecranMessages, Bouton MESSAGES[], int numeroMessage, Bouton SOLUTION[], JWindow fenetresInvitation[]) {
		fenetresInvitation[0].setVisible(false);
		fenetresInvitation[1].setVisible(false);
		
		if (choixDuMode == 0 || choixDuMode == 2) {
			fenetresJeu[0].setVisible(true);
			fenetresJeu[0].SOLUTION[0].setVisible(false);
			fenetresJeu[0].ecranMessages.setIcon(MESSAGES[numeroMessage].getIcon());
		}
		
		if (choixDuMode == 1 || choixDuMode == 2) {
			fenetresJeu[1].setVisible(true);
			fenetresJeu[1].ecranMessages.setIcon(MESSAGES[numeroMessage].getIcon());
		}
		
		if (choixDuMode == 2 && comptModeDuel == 0) {
			fenetresJeu[1].setEnabled(false);
		}
		
		if (choixDuMode == 2 && comptModeDuel == 1) {
			fenetresJeu[0].setEnabled(false);
		}
		
	}
	
	
	/* Méthode 2 - Déplace le curseur et réinitialise certains paramètres pour la poursuite de la partie. */
	public void continuerPartie() {
		
		/* Déplace le curseur. */
		curseur[coordBoutonsJeu[0]].setIcon(ICONES[22].getIcon());
		curseur[coordBoutonsJeu[0]+1].setIcon(ICONES[20].getIcon());
	
		
		/* Réinitialise la zone "jeu". */
		coordBoutonsJeu[0] = coordBoutonsJeu[0] + 1;
		coordBoutonsJeu[1] = 0;
		coordBoutonsValidation[0] = coordBoutonsValidation[0] + 1;
		coordBoutonsValidation[1] = 0;
		comptChoixJoueur[0] = 0;
		comptEgales = 0;
		comptPlacees = 0;
		comptPresentes = 0;
	}
	
	
	/* Méthode 3 - Bascule d'une fenêtre à l'autre en mode "Duel". */
	public void basculerDeFenetres(Boolean etat1, Boolean etat2, Boolean etat3, Boolean etat4) {
		fenetresInvitation[0].setVisible(etat1);
		fenetresInvitation[1].setVisible(etat2);
		
		if (comptModeDuel == 0) {
			fenetresJeu[0].setVisible(etat3);
			fenetresJeu[1].setVisible(etat4);
		}
		else {
			fenetresJeu[1].setVisible(etat4);
			fenetresJeu[0].setVisible(etat3);
		}
		
	}
	
}
