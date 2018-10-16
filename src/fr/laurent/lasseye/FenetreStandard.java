/*
 * La classe "FenetreStandard" crée l'ensemble des fenêtres de l'application (leur base).
 */

package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;



public class FenetreStandard extends JFrame {

	/* Récupère les dimensions de l'écran. */
	Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int hauteurEcran = (int)tailleEcran.getHeight();
	int largeurEcran = (int)tailleEcran.getWidth();
	
	
	/* Méthode 1 - Construit la base de la fenêtre "accueil" et des fenêtres "jeu" en mode "Challenger" et en mode "Défendeur". */
	public JFrame construireFenetreStandard(String titreFenetre) {
		setSize(453, 780);
		setTitle(titreFenetre);
		setFont(new Font("Impact", Font.BOLD, 32));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		return this;
	}
	
	
	/* Méthode 2 - Construit la bases des fenêtres "jeu" en mode "Duel". */
	public JFrame construireFenetreStandard(String titreFenetre, int choixModeJeu, int comptModeDuel) {
		if (choixModeJeu == 0 || choixModeJeu == 1) setBounds(((largeurEcran/2) - 226), ((hauteurEcran/2) - 419), 453, 780);
		if (choixModeJeu == 2 && comptModeDuel == 0) setBounds(((largeurEcran/2) + 10), ((hauteurEcran/2) - 419), 453, 780);
		if (choixModeJeu == 2 && comptModeDuel == 1) setBounds(((largeurEcran/2) - 463), ((hauteurEcran/2) - 419), 453, 780);
		setTitle(titreFenetre);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLayout(null);
		return this;
	}
	
	
	/* Méthode 3 - Construit les fenêtres "invitation à jouer". 
	public JFrame construireFenetreStandard(String titreFenetre, int abscisse) {
		setBounds(((largeurEcran/2) + abscisse), ((hauteurEcran/2) - 558), 453, 130);
		setTitle(titreFenetre);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		setResizable(false);
		setLayout(null);
		return this;
	}*/
	
}
