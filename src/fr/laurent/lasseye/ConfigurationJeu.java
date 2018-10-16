/*
 * La classe "ConfigurationJeu" récupère les données de configuration (4 propriétés) puis recalcule 
 * (si nécessaire) les paramètres de l'application. Ces paramètres concernent essentiellement
 * l'agencement des éléments des fenêtres "jeu" (en mode "Challenger" ou en mode "Défendeur").
 */

package fr.laurent.lasseye;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class ConfigurationJeu extends ElementsJeu {
	
	/* Attributs - Propriétés récupérées dans le fichier "config.properties". */
	protected static String propriete_1;
	protected static String propriete_2;
	protected static String propriete_3;
	protected static String propriete_4;
	

	
	/* Constructeur - Lance la récupération des 4 propriétés. */
	public ConfigurationJeu() throws IOException {
		
		propriete_1 = ConfigurationJeu.recupererPropriete("nbre_chif_coul");
		if (propriete_1 != null) {
			if (!propriete_1.equals("4") && !propriete_1.equals("5") && !propriete_1.equals("6") && !propriete_1.equals("7") 
				&& !propriete_1.equals("8") && !propriete_1.equals("9") && !propriete_1.equals("10")) {
				propriete_1 = "7";
			}
			else {
				ConfigurationJeu.recalculerParametres_1(propriete_1);
			}
		}	
		else {
			ConfigurationJeu.recalculerParametres_1("7");	
		}
		
		
		propriete_2 = ConfigurationJeu.recupererPropriete("taille_combinaison");
		if (propriete_2 != null) {
			if (!propriete_2.equals("4") && !propriete_2.equals("5")) {
				propriete_2 = "4";
			}
			else {
				ConfigurationJeu.recalculerParametres_2(propriete_2);
			}
		}	
		else {
			ConfigurationJeu.recalculerParametres_2("4");	
		}
		
		
		propriete_3 = ConfigurationJeu.recupererPropriete("nbre_essais");
		if (propriete_3 != null) {
			if (!propriete_3.equals("5") && !propriete_3.equals("6") && !propriete_3.equals("7")
				&& !propriete_3.equals("8") && !propriete_3.equals("9") && !propriete_3.equals("10")) {
				propriete_3 = "10";
			}
			else {
				ConfigurationJeu.recalculerParametres_3(propriete_3);
			}
		}	
		else {
			ConfigurationJeu.recalculerParametres_3("10");	
		}
		
		
		propriete_4 = ConfigurationJeu.recupererPropriete("mode_developpeur");
		if (propriete_4 != null) {
			if (!propriete_4.equals("true") && !propriete_4.equals("false")) {
				propriete_4 = "false";
			}
			else {
				ConfigurationJeu.recalculerParametres_4(propriete_4);
			}
		}	
		else {
			ConfigurationJeu.recalculerParametres_4("false");	
		}
		
		logger.info("Nbre de chiffres/couleurs = " + propriete_1);
		logger.info("Taille de la combinaison = " + propriete_2);
		logger.info("Nbre d'essais = " + propriete_3);
		logger.info("Mode développeur = " + propriete_4);
		
	}
	
	
	
	/* Méthode 1 - Récupère les propriétés du fichier "config.properties". */
	public static String recupererPropriete(String clePropriete) throws IOException {
		Properties listeDesProprietes = new Properties();
		FileInputStream fichierDesProprietes = null;
		try {
			fichierDesProprietes = new FileInputStream
			   ("/Users/laurentlasseye/eclipse-workspace/Jeux_de_logique/src/fr/laurent/lasseye/" + "config.properties");
				listeDesProprietes.load(fichierDesProprietes);
				fichierDesProprietes.close();
		} catch (FileNotFoundException e) {
			logger.info("ATTENTION LE CHEMIN VERS LE FICHIER CONFIG.PROPERTIES N'EST PAS VALIDE !");
		}
		return listeDesProprietes.getProperty(clePropriete);
	}
	
			
	/* Méthode 2 - Recalcule l'abcisse de la zone "chiffres ou 
	   couleurs" en fonction du nombre d'icones disponibles. */ 
	public static void recalculerParametres_1(String propriete_1) {	
		nbreChifCoul = Integer.valueOf(propriete_1);
		for (int j = 0; j < nbreChifCoul - 4; j++) {
			abscisseChifCoul = abscisseChifCoul - 20;
		}
	}	
	
	
	/* Méthode 3 - Recalcule l'abscisse des zones "curseur", "essais", "jeu",
	   "solution" et "analyse" en fonction de la taille de la combinaison. */
	public static void recalculerParametres_2(String propriete_2) {		
		tailleCombi = Integer.valueOf(propriete_2);
		for (int i = 0; i < tailleCombi - 3; i++) {
			abscisseCurseur = abscisseCurseur - 12;
			abscisseEssais = abscisseEssais - 12;
			abscisseJeu = abscisseJeu - 35;
			abscisseSolution = abscisseSolution - 15;
			abscisseAnalyse = abscisseAnalyse -18;
		}
	}	
	
	
	/* Méthode 4 - Recalcule l'ordonnée des zones "chiffres ou couleurs", "ecran message", "curseur",
	   "essais", "jeu", "analyse" et du bouton "ok" en fonction du nombre d'essais. */ 
	public static void recalculerParametres_3(String propriete_3) {
		nbreEssais = Integer.valueOf(propriete_3);
		for (int k = 0; k < nbreEssais - 5; k++) {
			ordonneeChifCoul = ordonneeChifCoul - 5;
			ordonneeGO = ordonneeGO - 7;
			ordonneeZoneMessage = ordonneeZoneMessage - 9;
			ordonneeCurseur = ordonneeCurseur - 15;
			ordonneeEssais = ordonneeEssais - 15;
			ordonneeJeu = ordonneeJeu - 15;
			ordonneeAnalyse = ordonneeAnalyse - 15;
			ecartCEJA = ecartCEJA - 3;
		}
	}
	
	
	/* Méthode 5 - Renseigne sur l'état du mode "développeur" (true ou false) */ 
	public static void recalculerParametres_4(String propriete_4) {	
		etat = Boolean.valueOf(propriete_4);
	}
	
}
	