/*
 * La classe abstraite "ElementsJeu" dont héritent plusieurs classes 
 * contient les attributs et les méthodes communes à plusieurs classes.
 */

package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import org.apache.log4j.Logger;



public abstract class ElementsJeu extends FenetreStandard {
	
	/*																	       	           *
	 * Les ATTRIBUTS stockent tous les éléments nécessaires à la construction des fenêtres *
	 * "jeu" : la fenêtre en mode "Challenger" et la fenêtre en mode "Défendeur".          *
	 *                                                                                     */
	
	protected static final Logger logger = Logger.getLogger(LancementJeu.class);
	
	
	/* Permettent d'agencer les fenêtres "jeu" (en mode "Challenger" et en mode "Défendeur"). */
	protected int choixDuJeu = 0, choixDuMode = 0, comptModeDuel = 0;
	protected static int nbreChifCoul = 0, tailleCombi = 0, nbreEssais = 0;
	protected static Boolean etat = false;
	protected static int abscisseChifCoul = 149, abscisseCurseur = 30, abscisseEssais = 67, abscisseJeu = 167, abscisseAnalyse = 337, abscisseSolution = 127/*, abscisseValidation = 323*/;
	protected static int ordonneeChifCoul = 35, ordonneeGO = 100, ordonneeZoneMessage = 150, ordonneeCurseur = 255, ordonneeEssais = 255, ordonneeJeu = 255, ordonneeAnalyse = 255;
	protected static int ecartCEJA = 60;
			
	/* Permettent de créer une combinaisons secrète de façon aléatoire puis de la stocker. */
	protected int index = 0;
	protected Random nbreAlea = new Random();
	protected ArrayList<Integer> indexAleas = new ArrayList<>();
	protected Bouton combiOrdi[] = new Bouton[6];
	
	/* Stockent une partie des icones nécessaires au fonctionnement des 2 jeux de l'application. */
	protected final Bouton ICONES[] = {new Bouton(new ImageIcon("0.Un.jpg", "1")), new Bouton(new ImageIcon("1.Deux.jpg", "2")),
			        new Bouton(new ImageIcon("2.Trois.jpg", "3")), new Bouton(new ImageIcon("3.Quatre.jpg", "4")), new Bouton(new ImageIcon("4.Cinq.jpg", "5")),
			        new Bouton(new ImageIcon("5.Six.jpg", "6")), new Bouton(new ImageIcon("6.Sept.jpg", "7")), new Bouton(new ImageIcon("7.Huit.jpg", "8")),
			        new Bouton(new ImageIcon("8.Neuf.jpg", "9")), new Bouton(new ImageIcon("9.Zero.jpg", "0")), new Bouton(new ImageIcon("10.Rouge.jpg", "Rouge")),
			        new Bouton(new ImageIcon("11.BleuClair.jpg", "BleuClair")), new Bouton(new ImageIcon("12.Orange.jpg", "Orange")),
			        new Bouton(new ImageIcon("13.VertClair.jpg", "VertClair")), new Bouton(new ImageIcon("14.Jaune.jpg", "Jaune")),
			        new Bouton(new ImageIcon("15.Rose.jpg", "Rose")), new Bouton(new ImageIcon("16.Marron.jpg", "Marron")),
			        new Bouton(new ImageIcon("17.Violet.jpg", "Violet")), new Bouton(new ImageIcon("18.VertFonce.jpg", "VertFonce")),
			        new Bouton(new ImageIcon("19.BleuFonce.jpg", "BleuFonce")), new Bouton(new ImageIcon("20.Curseur.jpg")), new Bouton(new ImageIcon("21.CaseVide.jpg", "00")),
			        new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("23.Moins.jpg", "Moins")), new Bouton(new ImageIcon("24.Egale.jpg", "Egale")),
			        new Bouton(new ImageIcon("25.Plus.jpg", "Plus")), new Bouton(new ImageIcon("26.EtoilePleine.jpg", "3")), new Bouton(new ImageIcon("27.EtoileVide.jpg", "2")),
			        new Bouton(new ImageIcon("28.Croix.jpg", "1")), new Bouton(new ImageIcon("29.Tiret.jpg", "0"))}; 
		
	/* Permet de valider les choix du joueur */
	protected final Bouton OK = new Bouton(new ImageIcon("0.Ok.jpg"));	
	
	/* Permettent d'informer le joueur sur l'état du bouton "ok". */
	protected final Bouton INVITES[] = {new Bouton(new ImageIcon("0.CliquezSur.jpg")), new Bouton(new ImageIcon("1.PourValider.jpg"))};
	protected Bouton ecranInvites[] = {new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg"))};

	/* Permettent d'informer le joueur sur l'état du jeu en cours. */
	protected final Bouton MESSAGES[] = {new Bouton(new ImageIcon("0.ChoisissezChiffre.jpg")), new Bouton(new ImageIcon("1.ChoisissezCouleur.jpg")),
			        new Bouton(new ImageIcon("2.MoinsEgalePlus.jpg")), new Bouton(new ImageIcon("3.PlacePresentAbsent.jpg")),
			        new Bouton(new ImageIcon("4.Pas2xMemeChiffre.jpg")), new Bouton(new ImageIcon("5.Pas2xMemeCouleur.jpg")),
			        new Bouton(new ImageIcon("6.ChoisissezCombi.jpg")), new Bouton(new ImageIcon("7.AnalysezChoixOrdi.jpg")),
			        new Bouton(new ImageIcon("8.RespectezOrdre.jpg")), new Bouton(new ImageIcon("9.Gagne.jpg")),
			        new Bouton(new ImageIcon("10.Perdu.jpg"))};
	protected Bouton ecranMessages = new Bouton(new ImageIcon("22.Fond gris.jpg"));
	
	/* Permet d'indiquer la ligne en cours de jeu. */
	protected Bouton curseur[] = {new Bouton(new ImageIcon("0.Curseur.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg")),
			  new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg")),
			  new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg")),
			  new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg")),
			  new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg"))};
	  
	/* Permet d'indiquer le nombre d'essais (jusqu'à 10 essais). */
	protected final Bouton NBRE_ESSAIS[] = {new Bouton(new ImageIcon("0.Essai1.jpg")), new Bouton(new ImageIcon("1.Essai2.jpg")),
			        new Bouton(new ImageIcon("2.Essai3.jpg")), new Bouton(new ImageIcon("3.Essai4.jpg")),
			        new Bouton(new ImageIcon("4.Essai5.jpg")), new Bouton(new ImageIcon("5.Essai6.jpg")),
			        new Bouton(new ImageIcon("6.Essai7.jpg")), new Bouton(new ImageIcon("7.Essai8.jpg")),
			        new Bouton(new ImageIcon("8.Essai9.jpg")), new Bouton(new ImageIcon("9.Essai10.jpg")),};
										  
	/* Permettent de recevoir les choix du joueur (en mode "Challenger") ou de l'ordinateur ("en mode "Défendeur"). */
	protected int coordBoutonsJeu[] = {0, 0};
	protected Bouton zoneJeu[][] = new Bouton[10][5];
	
	
	/* Permettent de stocker les choix du joueur transférés vers la zone "jeu". */
	protected int comptChoixJoueur[] = {0};
	protected Bouton choixJoueur[] = new Bouton[5];
	
	
	/* Permettent de recevoir l'analyse de l'ordinateur (en mode "Challenger") ou du joueur (en mode "défendeur"). */
	protected int coordBoutonsValidation[] = {0, 0};
	protected Bouton zoneValidation[][] = new Bouton[10][5];
	

	/* Permet de cacher la combinaison secrète de l'ordinateur. */
	protected final Bouton SOLUTION[] = {new Bouton(new ImageIcon("0.Solution.jpg"))};
	
	/* Permettent de stocker la combinaison de l'ordinateur (en mode "Challenger") ou du joueur (en mode "Défendeur"). */
	protected int comptCombiSecrete[] = {0};
	protected Bouton combiSecrete[][] = new Bouton [1][5];
	
	
	/* Permet au joueur d'analyser les choix de l'ordinateur. */
	protected Bouton iconesValidation[] = {new Bouton(new ImageIcon("22.FondGris.jpg")), new Bouton(new ImageIcon("22.FondGris.jpg")),
			  new Bouton(new ImageIcon("22.FondGris.jpg"))};
	
	/* Permet de changer de jeu, de rejouer ou de quitter l'application. */
	protected final Bouton AUTRES_LIENS[] = {new Bouton(new ImageIcon("0.Accueil.jpg")), new Bouton(new ImageIcon("1.Rejouer.jpg")),
			        new Bouton(new ImageIcon("2.Quitter.jpg"))};
		
	/* Permet de stocker 1 fenêtre en mode "Challenger" et 1 fenêtre en mode "Défendeur" quel que soit le jeu choisi. */
	protected FenetreJeu fenetresJeu[] = new FenetreJeu[2];
	
	/* Permettent de créer puis de stocker les panneaux qui invitent tour à tour le joueur ou l'ordinateur à jouer (en mode "Duel" uniquement). */
	protected JWindow fenetresInvitation[] = new JWindow[2];
	protected final Bouton INVITATIONS[] = {new Bouton(new ImageIcon("0.CestAVous.jpg")), new Bouton(new ImageIcon("1.CestAOrdi"))};
	
	
	
	/*																	       	      		   *
	 * Les METHODES "ajouterBoutons()" ajoutent tous les éléments de la fenêtre "accueil" et   *
	 * des fenêtres "jeu" (la fenêtre en mode "Challenger" et la fenêtre en mode "défendeur"). *
	 *                                                                                         */
	
	/* Ajoute les radiosBoutons des tableaux à 1 dimension : "JEUX[]" et "MODES[]" */
	protected void ajouterBouton(RadioBouton tableau[], int abscisse, int ordonnee, int largeur,
	int hauteur, int ecart, ButtonGroup groupeBoutons) {
		for(RadioBouton radioBouton : tableau) {
			add(radioBouton.formaterRadioBouton(abscisse, ordonnee, largeur, hauteur));
			groupeBoutons.add(radioBouton);
			ordonnee = ordonnee + ecart;
		}
	}
	
	
	/* Ajoute les boutons uniques : "GO", "EXIT, "OK", "ecranMessages" et "SOLUTION" */
	protected void ajouterBouton(Bouton bouton, int abscisse, int ordonnee, int largeur,
	int hauteur, Color couleurBordure) {
		add(bouton.formaterBouton(abscisse, ordonnee, largeur, hauteur, couleurBordure));
	}
	
	
	/* Ajoute les boutons des tableaux à 1 dimension : "TITRES[]", "INVITES[]", "ecranInvites[]", 
	   "MESSAGES[]", "curseur[]", "ESSAIS[]", "AUTRES_LIENS[]" et "POLICES[]" */
	protected void ajouterBouton(Bouton tableau[], int nbreDeFois, int abscisse, int ordonnee, int largeur,  
	int hauteur, Color couleurBordure, int ecartAbscisse, int ecartOrdonnee) {
		for (int i = 0; i < nbreDeFois; i++) {
			add(tableau[i].formaterBouton(abscisse, ordonnee, largeur, hauteur, couleurBordure));
			abscisse = abscisse + ecartAbscisse;
			ordonnee = ordonnee + ecartOrdonnee;
		}
	}
	
	
	/* Ajoute les boutons du tableau SYMBOLES[] : c'est uniquement les icones "chiffres" ou "couleurs". */
	protected void ajouterBouton(Bouton tableau[], int icone, int nbreDeFois, int abscisse, int ordonnee, int largeur, 
	int hauteur, Color couleurBordure, int ecartAbscisse, int ecartOrdonnee) {
		for (int i = 0; i < nbreDeFois; i++) {
			add(tableau[i + icone].formaterBouton(abscisse, ordonnee, largeur, hauteur, couleurBordure));
			abscisse = abscisse + ecartAbscisse;
			ordonnee = ordonnee + ecartOrdonnee;
		}
	}
	
	
	/* Ajoute les boutons des tableaux à 2 dimensions : "zoneJeu[][]", "combiSecrete[][]", et "zoneValidation[][]". */
	protected void ajouterBouton(Bouton tableau[][], int nbreLignes, int nbreColonnes, int abscisse, int ordonnee, 
	int largeur, int hauteur, Color couleurBordure, int ecartAbscisse, int ecartOrdonnee, String nomIcone, String info) {
		for(int i = 0 ; i < nbreLignes; i++) {
			int newAbscisse = abscisse;
			for(int j = 0 ; j < nbreColonnes; j++) {
				tableau[i][j] = new Bouton(new ImageIcon(nomIcone, info));
				add(tableau[i][j].formaterBouton(newAbscisse, ordonnee, largeur, hauteur, couleurBordure));
				newAbscisse = newAbscisse + ecartAbscisse;
			}
			ordonnee = ordonnee + ecartOrdonnee;
		}
	}
	
	
		
	/*																	                 *
	 * La METHODE "gererVisibiliteFenetres" gère l'état visible (ou non) des différentes *
	 * fenêtres de l'application (la fenêtre "accueil" et les fenêtres "jeu") et des     *
	 * panneaux "invitation" au jeu en mode "Duel".                                      *
	 *                                                                                   */      

	protected void gererVisibiliteFenetres(int choixDuMode, FenetreStandard fenetresJeu[], JWindow fenetresInvitation[],
	Boolean etat1, Boolean etat2, Boolean etat3, Boolean etat4) {
		if (choixDuMode == 1 || choixDuMode == 2) fenetresJeu[1].setVisible(etat2);
		if (choixDuMode == 0 || choixDuMode == 2) fenetresJeu[0].setVisible(etat1);
		if (choixDuMode == 2) {
			fenetresInvitation[1].setVisible(etat3);
			fenetresInvitation[0].setVisible(etat4);
		}
	}
	
	
	
	/*														            *
	 * Les METHODES "ajouterMessageX" affichent les différents messages *
	 * qui informent le joueur sur l'état du jeu en cours.              *
	 *                                                                  */     
	
	/* Affiche "Choisissez une couleur !" ou "Choisissez un chiffre !". */
	protected void ajouterMessage1(int choixDuJeu, Bouton ecranMessages, Bouton MESSAGES[]) {
		ecranMessages.setIcon(MESSAGES[1].getIcon()); 
		if (choixDuJeu == 0) ecranMessages.setIcon(MESSAGES[0].getIcon());
	}
	
	
	/* Affiche "Choisissez votre combinaison !". */
	protected void ajouterMessage2(Bouton ecranMessages, Bouton MESSAGES[]) {
		ecranMessages.setIcon(MESSAGES[6].getIcon());
	}
	
	
	/* Affiche "Moins/Egale/Plus" ou "Placées/Présentes/Absentes". */
	protected void ajouterMessage3(int choixDuJeu, Bouton ecranMessages, Bouton MESSAGES[]) {
		ecranMessages.setIcon(MESSAGES[3].getIcon()); 
		if (choixDuJeu == 0) ecranMessages.setIcon(MESSAGES[2].getIcon()); 
	}
	
	
	
	/*														        *
	 * La METHODE "desactiverOK" désactive l'action du bouton "ok". *
	 *                                                              */   
	
	public void desactiverOK(Bouton ICONES[], Bouton OK, Bouton ecranInvites[]) {
		OK.setVisible(false);
		ecranInvites[0].setIcon(ICONES[22].getIcon());
		ecranInvites[1].setIcon(ICONES[22].getIcon());
	}
	

	/*														             *
	 * Les METHODES "supprimerEcouteur" désactive l'action ddes boutons. *
	 *                                                                   */  
	
	/* Supprime les écouteurs des boutons des tableaux à 1 dimension : zone "chiffres ou couleurs". */
	protected void supprimerEcouteur(int choixDuJeu, Bouton ICONES[]) {
		for (int i = 0 ; i < nbreChifCoul; i++) {
			ICONES[i + choixDuJeu].removeActionListener(ICONES[i + choixDuJeu].getActionListeners()[0]);
		}
	}
	
	
	/* Supprime les écouteurs des boutons des tableaux à 2 dimensions : "zoneJeu[][]", "combiSecrete[][]", et "zoneValidation[][]". */
	protected void supprimerEcouteur(Bouton tableau[][], int coordBoutons[]) {
		for (int i = 0; i < tailleCombi; i++) {
			tableau[coordBoutons[0]][i].removeActionListener(tableau[coordBoutons[0]][i].getActionListeners()[0]);
		}
	}

}
	