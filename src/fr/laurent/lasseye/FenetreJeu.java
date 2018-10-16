/*
 * La classe "FenetreJeu" crée les différentes fenêtres "jeu" : la fenêtre en mode "Challenger" et la fenêtre en mode "Défenseur".
 * Le mode "Duel" utilise tour à tour les deux fenêtres.
 */

package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JWindow;



public class FenetreJeu extends ElementsJeu implements ActionListener {
	
	/* Attributs - Permettent de construire les fenêtres "jeu" et de les stocker. */
	private int choixDuJeu, choixDuMode, comptModeDuel;
	private FenetreJeu fenetresJeu[] = new FenetreJeu[2];
	private JWindow fenetresInvitation[] = new JWindow[2];
	
	
	
	/* Constructeur - Lance la création de la (ou des) fenêtre(s) "jeu" selon le mode choisi. */
	public FenetreJeu(int choixDuJeu, int choixDuMode, int comptModeDuel, FenetreJeu fenetresJeu[], JWindow fenetresInvitation[]) {
					  
		this.choixDuJeu = choixDuJeu;
		this.choixDuMode = choixDuMode;
		this.comptModeDuel = comptModeDuel;
		this.fenetresJeu = fenetresJeu;
		this.fenetresInvitation = fenetresInvitation;
		

		/* 1. Crée la fenêtre de base selon le jeu sélectionné. */
		if (choixDuJeu == 0) {
			construireFenetreStandard("LE NOMBRE D'OR", choixDuMode, comptModeDuel);
		}
		else {
			construireFenetreStandard("LE MASTERMIND", choixDuMode, comptModeDuel);
		}
		
		
		/* 2. Ajoute la zone "chiffres ou couleurs".
		      Chaque icone dispose d'un écouteur qui permet son transfert :
		           - vers la zone "jeu" en mode "Challenger",
		   		   - vers la zone "combinaison secrète" (celle du joueur) en mode "Défendeur".
		      Ce transfert se fait via la classe "TransfertChoix". */
		ajouterBouton(ICONES, choixDuJeu, nbreChifCoul, abscisseChifCoul, ordonneeChifCoul, 35, 35, Color.DARK_GRAY, 40, 0);
		for (int i = 0; i < nbreChifCoul; i++) {
			ICONES[i + choixDuJeu].addActionListener(new TransfertChoix(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, ICONES[i + choixDuJeu], OK, ecranMessages, 
			MESSAGES, ecranInvites, INVITES, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiSecrete, comptCombiSecrete));			                                                 		                                               
		}
		
		
		/* 3. Ajoute le bouton "ok". Ce dernier dispose d'un ECOUTEUR différent selon le mode de jeu choisi. */		
		ajouterBouton(OK, 181, ordonneeGO, 91, 40, Color.DARK_GRAY);
		OK.setVisible(false);
		
		/* 1ère ECOUTEUR --> SI le mode "Challenger" est activé, l'écouteur du bouton "ok" lance la vérification par l'ordinateur des choix proposés par le joueur.
		   Cet écouteur est activé une fois terminé le transfert des icones "chiffres ou couleurs" vers la zone "jeu".
		   La vérification se fait via la classe "AnalyserChoix". */
		if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) {
			OK.addActionListener(new AnalyseChoix(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreAlea, index, indexAleas, nbreEssais, ICONES, OK, ecranMessages, MESSAGES, ecranInvites, INVITES,
			curseur, zoneJeu, coordBoutonsJeu, choixJoueur, comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation));
												   						       
		}	
		/* 2ème ECOUTEUR --> SINON en mode "Défendeur", l'écouteur du bouton "ok" modifie quelques éléments de la fenêtre.
		   Cet écouteur est activé une fois terminé le transfert des icones "chiffres ou couleurs" vers la zone "combinaison secrète" (celle du joueur).
		   Cette modification se fait via la classe "ConfigurerModeDefendeur". */
		else { 
			OK.addActionListener(new ConfigurerModeDefendeur(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, nbreChifCoul, nbreEssais, ICONES, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, curseur, zoneJeu,
			coordBoutonsJeu, choixJoueur, comptChoixJoueur,  zoneValidation, coordBoutonsValidation, combiOrdi, SOLUTION, combiSecrete, comptCombiSecrete, iconesValidation, fenetresJeu, fenetresInvitation));										
		}
		
		
		 /* 4. Ajoute le bouton "écran invite" et les boutons "invites". Ces boutons invitent à cliquer sur "ok". */
		INVITES[0].formaterBouton(10, ordonneeGO, 165, 40, Color.DARK_GRAY);
		INVITES[1].formaterBouton(278, ordonneeGO, 165, 40, Color.DARK_GRAY);
		ajouterBouton(ecranInvites, 2, 10, ordonneeGO, 165, 40, Color.DARK_GRAY, 268, 0);
		
		
		 /* 5. Ajoute le bouton "écran message" et les boutons "messages". Ces boutons informent le joueur de l'état du jeu. */
		for (Bouton bouton : MESSAGES) {
			bouton.formaterBouton(21, ordonneeZoneMessage, 411, 40, Color.DARK_GRAY);
		}
		ajouterBouton(ecranMessages, 21, ordonneeZoneMessage, 411, 40, Color.DARK_GRAY);
		
		
		/* 6. Affiche un message. */
		if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) {
			ajouterMessage1(choixDuJeu, ecranMessages, MESSAGES); // Affiche "Choisissez chiffre !" ou "Choisissez couleur !" en mode "Challenger"
		}
		else {
			ajouterMessage2(ecranMessages, MESSAGES); // Affiche "Choisissez combinaison !" en mode "Défendeur"
		}
		
		
		/* 7. Ajoute le bouton "curseur". Ce bouton indique la ligne à jouer. */
		ajouterBouton(curseur, nbreEssais, abscisseCurseur, ordonneeCurseur, 32, 35, Color.DARK_GRAY, 0, ecartCEJA);
		
			
		/* 8. Ajoute les boutons "nombre d'essais". Ces boutons indiquent le nombre d'essais. */
		ajouterBouton(NBRE_ESSAIS, nbreEssais, abscisseEssais, ordonneeEssais, 45, 35, Color.DARK_GRAY, 0, ecartCEJA);
		
		
		/* 9. Ajoute la zone "jeu" avec des cases vides. 
		   En mode "Challenger", chaque bouton dispose d'un écouteur pour modifier son état (supprimer le chiffre ou la couleur en cas d'erreur).
		   Ces écouteurs sont mis en place dans la classe "TransfertChoix". */
		ajouterBouton(zoneJeu, nbreEssais, tailleCombi, abscisseJeu, ordonneeJeu, 35, 35, Color.DARK_GRAY, 40, ecartCEJA, "21.CaseVide.jpg", "00");
		
		
		/* 10. Ajoute la zone "validation".
		       En mode "Défendeur", chaque bouton dispose d'un écouteur pour modifier son état (supprimer le symbole en cas d'erreur).
		       Ces écouteurs sont mis en place dans la classe "ConfigurerModeDefendeur" */
		ajouterBouton(zoneValidation, nbreEssais, tailleCombi, abscisseAnalyse, ordonneeAnalyse, 26, 35, Color.DARK_GRAY, 30, ecartCEJA, "29.Tiret.jpg", "0");
		
		
		/* 11. SI le mode "Challenger" est activé - Crée la zone "solution".
		       C'est une sorte de panneau qui cache la combinaison secrète choisie par l'ordinateur.
		       Ce panneau se positionne dans la fenêtre en fonction de la taille de la combinaison. */
		if((choixDuMode == 0 && etat == false) || ((choixDuMode == 2 && comptModeDuel == 0) && etat == false)) {
			SOLUTION[0] = new Bouton (new ImageIcon("0.Solution.jpg"));
			add(SOLUTION[0].formaterBouton(abscisseSolution, 650, 195, 37, Color.DARK_GRAY));
		}
		
		
		/* 12. Ajoute la zone "combinaison secrète". Pour l'instant, ce sont des cases vides. */
		ajouterBouton(combiSecrete, 1, tailleCombi, abscisseJeu, 650, 35, 35, Color.DARK_GRAY, 40, 0, "21.CaseVide.jpg", "00");
		
				
		/* 13. Ajoute les icones pour "validation".
		       Ces icones offrent au joueur des symboles (étoiles pleines ou vides - les signes moins, plus, égale) pour analyser les choix de l'ordinateur.
		       Cette zone s'active via la classe "ConfigurerModeDefendeur" (donc en mode "Défendeur"). */
		ajouterBouton(iconesValidation, 3, 323, 650, 35, 35, Color.DARK_GRAY, 40, 0);
		
				
		/* 14. Ajoute la zone "autres liens" : "accueil", "rejouer" et "quitter". */
		ajouterBouton(AUTRES_LIENS, 3,  5, 720, 120, 30, Color.DARK_GRAY, 161, 0);
		for (Bouton bouton : AUTRES_LIENS) {
			bouton.addActionListener(this);
		}	
			
		/* 15. Si le mode "Challenger" est activé - Lance la création d'une combinaison par l'ordinateur */
		if (choixDuMode == 0 || (choixDuMode == 2 && comptModeDuel == 0)) {
			Combinaison combinaison = new Combinaison(choixDuJeu, choixDuMode, comptModeDuel, zoneJeu, coordBoutonsJeu, combiOrdi, combiSecrete);
		}
		
	}

	
	
	/* Interface - Gère les écouteurs. */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* 1. Rend invisible toutes les fênetres initalement ouvertes. */
	    gererVisibiliteFenetres(choixDuMode, fenetresJeu, fenetresInvitation, false, false, false, false);
		
		
		/* 2. La source offre 3 SOURCES. */
		Object source = e.getSource(); 
			
		/* 1ère SOURCE --> Le bouton "accueil" permet de retourner à la fenêtre "accueil". */
		if (source == AUTRES_LIENS[0]) { 
			
			logger.info("----- ACCUEIL -----"); // -------> log
			
			FenetreAccueil fenetreAccueil = new FenetreAccueil("LA COMBINAISON SECRETE");							   
			fenetreAccueil.setVisible(true);
			
		}
		        
		
		/* 2ème SOURCE --> Le bouton "rejouer" remet à "zéro" le jeu en cours. */
		if (source == AUTRES_LIENS[1]) { 
			
			logger.info("----- REJOUER -----"); // -------> log
			
			comptModeDuel = 0;
			
			if (choixDuMode == 0 || choixDuMode == 2) {
				fenetresJeu[0] = new FenetreJeu(choixDuJeu, choixDuMode, comptModeDuel,fenetresJeu, fenetresInvitation); // En mode "Challenger".
				comptModeDuel = 1; // En mode "Duel", ce compteur différencie la fenêtre en mode "Challenger" de la fenêtre en mode "Défenseur".
			}
			
			if (choixDuMode == 1 || choixDuMode == 2) {
				fenetresJeu[1] = new FenetreJeu( choixDuJeu, choixDuMode, comptModeDuel, fenetresJeu, fenetresInvitation); // En mode "Défendeur".
			}
			
			gererVisibiliteFenetres(choixDuMode, fenetresJeu, fenetresInvitation, true, true, false, true);
			
		}
		
	
		/* 3ème SOURCE --> Le bouton "quitter" ferme définitivement l'application. */
		if (source == AUTRES_LIENS[2]) {
			
			logger.info("QUITTER L'APPLICATION"); // -------> log
		
			System.exit(0);
			
		}
	
	}
	
}
