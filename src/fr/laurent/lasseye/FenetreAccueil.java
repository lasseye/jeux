/*
 * La classe "FenetreAccueil" crée la 1ère page de l'application. Cette page donne la possibilité de choisir 
 * entre 2 jeux ("Le Nombre d'Or" ou "Le Mastermind") et 3 façons de jouer ("Challenger", "Défendeur" et "Duel").
 */
 
package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JWindow;



public class FenetreAccueil extends ElementsJeu implements ActionListener{
	
	/* Attributs. */
	/* Permettent de construire la fenêtre "accueil". */
	private final Bouton TITRES[] = {new Bouton(new ImageIcon("0.ChoisissezJeu.jpg")), new Bouton(new ImageIcon("1.ChoisissezMode.jpg"))};
	private final RadioBouton JEUX[] = {new RadioBouton(), new RadioBouton()};
	private final Bouton CHOIX_JEUX[] = {new Bouton(new ImageIcon("0.LeNombreOr.jpg")), new Bouton(new ImageIcon("1.LeMastermind.jpg"))};
	private ButtonGroup groupeBoutons1 = new ButtonGroup();
	private final RadioBouton MODES[] = {new RadioBouton(), new RadioBouton(), new RadioBouton()};
	private final Bouton CHOIX_MODE[] = {new Bouton(new ImageIcon("0.Challenger.jpg")), new Bouton(new ImageIcon("1.Defendeur.jpg")), new Bouton(new ImageIcon("2.Duel.jpg"))};
	private ButtonGroup groupeBoutons2 = new ButtonGroup();
	private final Bouton GO = new Bouton(new ImageIcon("0.Go.jpg"));
	private final Bouton EXIT = new Bouton(new ImageIcon("0.QuitterJeu.jpg"));
	
	/* Permettent de construire et de stocker les fenêtres "jeu" et les panneaux "invitation" au jeu. */
	private FenetreJeu fenetresJeu[] = new FenetreJeu[2];
	private JWindow fenetresInvitation[] = {new PanneauStandard(-463), new PanneauStandard(10)};	
	private final Bouton INVITATIONS[] = {new Bouton(new ImageIcon("0.CestAVous.jpg")), new Bouton(new ImageIcon("1.CestAOrdi.jpg"))};
		
	

	/* Constructeur - Lance la création de la fenêtre "accueil". */
	public FenetreAccueil(String titreFenetre) {
		
		/* 1. Crée la fenêtre de base. */
		construireFenetreStandard(titreFenetre);
		
		
		/* 2. Ajoute les autres éléments de la fenêtre. */
		ajouterBouton(TITRES, 2, 10, 25, 433, 40, Color.DARK_GRAY, 0, 185);
		
		ajouterBouton(JEUX, 50, 70, 350, 40, 60, groupeBoutons1);
		
		ajouterBouton(CHOIX_JEUX, 2, 25, 70, 400, 40, Color.DARK_GRAY, 0, 60);
		
		ajouterBouton(MODES, 100, 255, 350, 40, 60, groupeBoutons2);
		
		ajouterBouton(CHOIX_MODE, 3, 95, 255, 350, 40, Color.DARK_GRAY, 0, 60);
		
		ajouterBouton(GO, 149, 500, 155, 40, Color.DARK_GRAY);
		GO.addActionListener(this);
		
		ajouterBouton(EXIT, 10, 700, 433, 40, Color.DARK_GRAY);
		EXIT.addActionListener(this);
		
		
		/* Sélectionne par défaut le jeu "Le Mastermind" en mode "Challenger". */
		JEUX[1].setSelected(true);
		MODES[0].setSelected(true);	
		
	}
	
	
	
	/* Interface - Gère les écouteurs. */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		/* 1. La source offre 2 OPTIONS. */
		Object source = e.getSource(); 
	
		
		/* 1ère OPTION --> Le bouton "go" ouvre le jeu sélectionné selon le mode choisi. */
		if (source == GO) {
		
			logger.info("--> GO actionné"); // -------> log
			
			/* a. Précise le paramètre "choix du jeu". Ce paramètre détermine les icones à afficher : 0 = les chiffres, 10 = les couleurs. */
			if (JEUX[0].isSelected() == true) choixDuJeu = 0;
			if (JEUX[1].isSelected() == true) choixDuJeu = 10;
			
			if (choixDuJeu == 0) { // -------> log
				logger.info("Choix du jeu : Le Nombre d'Or");
			}
			else {
			logger.info("Choix du jeu : Le Mastermind");
			}
			
			
			/* b. Précise le paramètre "mode du jeu" : 0 = "Challenger", 1 = "Défendeur" et 2 = "Duel".
			      Ce paramètre détermine la ou les fenêtres à afficher. */
			int i = 0;
			choixDuMode = 0;
			while (MODES[i].isSelected() == false) {
				choixDuMode = choixDuMode + 1;
				i++;
			}
			
			switch(choixDuMode) { // -------> log
				case 0 :
					logger.info("Choix du mode : Challenger");
					break;
				case 1 :
					logger.info("Choix du mode : Défendeur");
					break;
				case 2 :
					logger.info("Choix du mode : Duel --> fenêtre Challenger");
					break;
			}
			
					
			/* c. Crée les fenêtres "jeu" puis les stocke dans un tableau. */	
			if (choixDuMode == 0 || choixDuMode == 2) {
				fenetresJeu[0] = new FenetreJeu(choixDuJeu, choixDuMode, comptModeDuel, fenetresJeu, fenetresInvitation); // En mode "Challenger".
				comptModeDuel = 1; // En mode "Duel", ce compteur différencie la fenêtre en mode "Challenger" de la fenêtre en mode "Défenseur".
			}
			
			if (choixDuMode == 1 || choixDuMode == 2) {
				fenetresJeu[1] = new FenetreJeu( choixDuJeu, choixDuMode, comptModeDuel, fenetresJeu, fenetresInvitation); // En mode "Défendeur".
			}
			
			
			/* d. Crée en mode "Duel" (uniquement) les panneaux "invitation" à jouer (le joueur ou l'ordinateur). */
			if (choixDuMode == 2)  {
				fenetresInvitation[0].add(INVITATIONS[0].formaterBouton(10, 10, 433, 746, Color.DARK_GRAY));
				fenetresInvitation[1].add(INVITATIONS[1].formaterBouton(10, 10, 433, 746, Color.DARK_GRAY));
			}	
			
							
			/* e. Affiche le jeu sélectionné ("Le Chiffre d'Or" ou "Le Mastermind") selon le mode choisi ("Challenger", "Défendeur" ou "Duel").
			      Attention : En mode "Duel", la fenêtre du jeu s'affiche d'abord en mode "Challenger". */
			gererVisibiliteFenetres(choixDuMode, fenetresJeu, fenetresInvitation, true, true, false, true);
			
		}
		
		
		/* 2ème OPTION --> Le bouton "quitter" ferme définitivement le jeu. */
		if (source == EXIT) {
			
			logger.info("QUITTER L'APPLICATION"); // ------> log
			
			System.exit(0);
		}	
		
		
		/* 2. Rend invisible la fenêtre "accueil" précédemment ouverte.  */
		setVisible(false);
	
	}
	
}
