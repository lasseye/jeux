/*
 * La classe "PanneauStandard" crée l'ensemble des panneaux de l'application (leur base).
 */

package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JWindow;



public class PanneauStandard extends JWindow {
	
	/* Récupère les dimensions de l'écran. */
	Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int hauteurEcran = (int)tailleEcran.getHeight();
	int largeurEcran = (int)tailleEcran.getWidth();
	
	
	
	/* Méthode - Construit la base des panneaux "invitation au jeu". */
	public PanneauStandard(int abscisse) {
		setBounds(((largeurEcran/2) + abscisse), ((hauteurEcran/2) - 420), 453, 780);
		getContentPane().setBackground(Color.DARK_GRAY);
		setLayout(null);
	}
	
}
