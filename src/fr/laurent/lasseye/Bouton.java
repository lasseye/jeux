/*
 * La classe "BoutonStandard" crée l'ensemble des boutons de l'application.
 */

package fr.laurent.lasseye;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class Bouton extends JButton{
	
	/* Constructeur - Crée les boutons de type "texte". 
	public Bouton(String string) {
		setText(string);
	}
*/
	
	
	/* Constructeur - Crée les boutons de type "icone". */
	public Bouton(ImageIcon imageIcon) {
		setIcon(imageIcon);
	}
	
	
	
	/* Méthode - Formate les boutons de type "texte" et de type "icone". */
	public Bouton formaterBouton(int abscisse, int ordonnee, int largeur, int hauteur, Color couleurBordure) {
		setBounds(abscisse, ordonnee, largeur, hauteur);
		setBorder(BorderFactory.createLineBorder(couleurBordure));
		//setFont(police);
		//setForeground(couleurPolice);
		return this;
	}
	
}
