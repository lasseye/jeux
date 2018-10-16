/*
 * La classe "ConfigurerModeDefendeur" agence la fenêtre pour jouer en mode "défendeur".
 * Elle ajoute les icones "validation" pour permettre d'analyser les propositions de 
 * l'ordinateur.
 */

package fr.laurent.lasseye;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JWindow;



public class ConfigurerModeDefendeur extends ElementsJeu implements ActionListener {

	/* Permettent de créer une combinaisons secrète de façon aléatoire puis de la stocker. */
	private int index = 0;
	private Random nbreAlea = new Random();
	private ArrayList<Integer> indexAleas = new ArrayList<>();
	private Bouton combiOrdi[];
	
	/* Permet de paramétrer le jeu. */
	private int choixDuJeu, choixDuMode, comptModeDuel;
	private int nbreChifCoul, tailleCombi, nbreEssais;

	/* Permet de valider les choix du joueur */
	private Bouton OK;
	
	/* Permettent d'informer le joueur sur l'état du jeu en cours. */
	private Bouton ecranInvites[], INVITES[], ecranMessages, MESSAGES[];
		
	/* Permet d'indiquer la ligne en cours de jeu. */
	private Bouton curseur[];
	
	/* Permettent de stocker les propositions du joueur (en mode "Challenger") ou sa combinaison secrète (en mode "Défendeur"). */
	private int comptCombiSecrete[];
	private Bouton choixJoueur[], combiSecrete[][];
	
	/* Permettent de recevoir les choix du joueur (en mode "Challenger") ou de l'ordinateur ("en mode "Défendeur"). */
	private int coordBoutonsJeu[], comptChoixJoueur[];
	private Bouton zoneJeu[][];	
	
	/* Permettent de recevoir l'analyse de l'ordinateur (en mode "Challenger") ou du joueur (en mode "défendeur"). */
	private int coordBoutonsAnalyse[];
	private Bouton ICONES[], zoneAnalyse[][];	
	
	/* Permet de cacher la combinaison secrète de l'ordinateur. */
	private Bouton SOLUTION[];

	/* Permet au joueur d'analyser les choix de l'ordinateur. */
	private final Bouton iconesValidation[];
	
	/* Permettent de construire et de stocker les fenêtres "jeu" et les panneaux "invitation" au jeu. */
	private FenetreJeu fenetresJeu[] =  new FenetreJeu[2];
	private JWindow fenetresInvitation[] = new JWindow[2];
	
	
	/* Constructeur de la fenêtre "JouerDefendeur". */
	public ConfigurerModeDefendeur(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi, int nbreChifCoul, int nbreEssais, Bouton ICONES[], Bouton OK, Bouton ecranMessages, Bouton MESSAGES[],  Bouton ecranInvites[], Bouton INVITES[], Bouton curseur[], 
		   Bouton zoneJeu[][], int coordBoutonsJeu[], Bouton choixJoueur[], int comptChoixJoueur[], Bouton zoneAnalyse[][], int coordBoutonsAnalyse[], Bouton combiOrdi[], Bouton SOLUTION[],  
		   Bouton combiSecrete[][], int comptCombiSecrete[], Bouton iconesValidation[], FenetreJeu fenetresJeu[], JWindow fenetresInvitation[]) {
						      
		this.choixDuJeu = choixDuJeu;
		this.choixDuMode = choixDuMode;
		this.comptModeDuel = comptModeDuel;
		this.tailleCombi = tailleCombi;
		this.nbreChifCoul = nbreChifCoul;
		this.nbreEssais = nbreEssais;
		this.combiOrdi = combiOrdi;
		this.choixJoueur = choixJoueur;
		this.combiSecrete = combiSecrete;
		this.comptCombiSecrete = comptCombiSecrete;
		this.OK = OK;
		this.ecranMessages = ecranMessages;
		this.MESSAGES = MESSAGES;
		this.ecranInvites = ecranInvites;
		this.INVITES = INVITES;
		this.curseur = curseur;
		this.ICONES = ICONES;
		this.zoneAnalyse = zoneAnalyse;
		this.coordBoutonsAnalyse = coordBoutonsAnalyse;
		this.zoneJeu = zoneJeu;
		this.coordBoutonsJeu = coordBoutonsJeu;
		this.comptChoixJoueur = comptChoixJoueur;
		this.SOLUTION = SOLUTION;
		this.iconesValidation = iconesValidation;
		this.fenetresJeu = fenetresJeu;
		this.fenetresInvitation = fenetresInvitation;
	
	}
	

	
	/* Interface - Gère l'écouteur du bouton "ok". */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		logger.info("--> OK"); // -------> log
		logger.info("--> Configuration du mode Défendeur");	// -------> log
		
		/* 1. Prévient le jeu que la "Combinaison joueur" a été choisie - le comptCombiSecrete passe à l'état 1. */
		comptCombiSecrete[0] = 1; 
		comptChoixJoueur[0] = 0;
		
		
		/* 2. Désactive le bouton "ok". */
		desactiverOK(ICONES, OK, ecranInvites);
		ecranMessages.setIcon(MESSAGES[7].getIcon()); // Affiche "Analysez le choix de l'ordinateur !".
		
		
		/* 3. Ajoute un nouvel écouteur au bouton "ok". Ce dernier va permettre plusieurs actions via la classe "AnalyserChoix". */ 
		OK.removeActionListener(OK.getActionListeners()[0]);
		OK.addActionListener(new AnalyseChoix(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas,
		nbreEssais, ICONES, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, 
		zoneAnalyse, coordBoutonsAnalyse, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation));
		
				   							   
		/* 4. Supprime les écouteurs de la zone "combinaison secrète" (celle choisie par le joueur) pour la "figer" le temps du jeu. */
		supprimerEcouteur(combiSecrete, coordBoutonsJeu);
		
		
		/* 5. Supprime les écouteurs de la zone "chiffres ou couleurs" car elle n'a plus d'utilité à ce stade du jeu. */
		supprimerEcouteur(choixDuJeu, ICONES);
		
		
		/* 6. Affiche les icones "Moins/Egale/Plus" ou "Etoile pleine/Etoile vide" pour permettre au joueur de valider les choix de l'ordinateur.
		      Chaque symbole dispose d'un écouteur qui permet son transfert vers la zone "validation".
		      Ce transfert se fait via la classe "TransfertChoix". */
		if (choixDuJeu == 0) { // "Le Nombre d'Or"
			for (int i = 0; i < 3; i++) {	
			iconesValidation[i].setIcon(ICONES[23 + i].getIcon());
			iconesValidation[i].addActionListener(new TransfertChoix(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, ICONES[23 + i], OK, ecranMessages, MESSAGES,
            ecranInvites, INVITES, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneAnalyse, coordBoutonsAnalyse, combiSecrete, comptCombiSecrete));        											 											
			}
		}
		else { // "Le Mastermind "
			for (int i = 0; i < 3; i++) {	
			iconesValidation[i].setIcon(ICONES[26 + i].getIcon());
			iconesValidation[i].addActionListener(new TransfertChoix(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, ICONES[26 + i], OK, ecranMessages, MESSAGES,
			ecranInvites, INVITES, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneAnalyse, coordBoutonsAnalyse, combiSecrete, comptCombiSecrete));
			}
		}
		
		
		/* 7. Demande à l'ordinateur d'établir une combinaison pour la zone "Jeu". */
		indexAleas.clear();
		Combinaison combinaison = new Combinaison(choixDuJeu, choixDuMode, comptModeDuel,  zoneJeu, coordBoutonsJeu, combiOrdi, combiSecrete);	
			
	}

}
