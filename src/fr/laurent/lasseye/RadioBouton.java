/*
 * La classe "RadioBouton" crée l'ensemble des radioboutons de l'application.
 */

package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;



public class RadioBouton extends JRadioButton {

	/* Constructeur - Crée les radioboutons. 
	public RadioBouton(String string) {
		this.setText(string);
	}*/
	
	
	
	/* Méthode - Formate les radiosboutons. */ 
	public RadioBouton formaterRadioBouton(int abscisse, int ordonnee, int largeur, int hauteur) {
		setBounds(abscisse, ordonnee, largeur, hauteur);
	    //setFont(police1);
		setForeground(Color.WHITE);
		return this;
	}
	
}
