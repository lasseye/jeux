/*
 * La classe "TransfertVersValidation" est une sous-classe de "TransfertChoix". Elle gère uniquement le transfert
 * des icones "moins/égale/plus" ou "étoile pleine/ étoile vide/croix" vers la zone "analyse" (en mode "défendeur")
 */

package fr.laurent.lasseye;



public class TransfertVersValidation extends TransfertChoix {

	public TransfertVersValidation(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi,Bouton ICONES[], Bouton icone, Bouton OK,
		   Bouton ecranMessages, Bouton MESSAGES[], Bouton ecranInvites[], Bouton INVITES[], Bouton zoneJeu[][], int coordBoutonsJeu[],
		   Bouton choixJoueur[], int comptChoixJoueur[], Bouton zoneValidation[][], int coordBoutonsValidation[], Bouton combiSecrete[][], int comptCombiSecrete[]) {
			
			
		super(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, icone, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, zoneJeu,
			  coordBoutonsJeu, choixJoueur,comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiSecrete, comptCombiSecrete);
	
	
		
		/* 1. Tant que les choix du joueur n'atteignent pas la taille de la combinaison, effectue 3 opérations sur la zone "combinaison secrète" :
		           - Détecte les cases vides;
		           - Transfère l'icone;
		           - Vérifie que l'analyse des 2 jeux soit correct. */
			detecterCaseVide(zoneValidation, coordBoutonsValidation, 29);
			transfererIcone(zoneValidation, coordBoutonsValidation);
			poserEcouteur(zoneValidation, coordBoutonsValidation);
			
			
			/* a. "Le Nombre d'Or" - Vérifie que le joueur ait analysé toutes les propositions de l'ordinateur. */
			comptMoinsEgalePlus = 0;
			if (choixDuJeu == 0) {
				for (int i = 0; i < tailleCombi; i++) {
					if (zoneValidation[coordBoutonsValidation[0]][i].getIcon().toString() != ICONES[29].getIcon().toString()) {
						comptMoinsEgalePlus++;
					}
				}
			}		
			
			
			/* b. "Le Mastermind" - Vérifie l'ordre des étoiles - il faut d'abord des étoiles pleines (= 3),
			      ensuite des étoiles vides (= 2) puis des tirets (= 1). */
			comptEtoiles = 0;
			if (choixDuJeu == 10) {
				for (int i = 0; i < tailleCombi - 1; i++) {
					if (Integer.parseInt(zoneValidation[coordBoutonsValidation[0]][i].getIcon().toString()) 
						>= Integer.parseInt(zoneValidation[coordBoutonsValidation[0]][i + 1].getIcon().toString())) {
						comptEtoiles++;
					}
					/* Si l'ordre n'est pas respecté --> message   */
					else { 
						ecranMessages.setIcon(MESSAGES[8].getIcon()); // Affiche l'ordre à respecter.
						OK.setEnabled(false);
						
						logger.info(icone.getIcon() + " - ATTENTION, ORDRE A RESPECTER !"); // -------> log
						
					}	
				}
			}
				
			comptChoixJoueur[0] = comptChoixJoueur[0] + 1;
			
		
		/* 2. Active le bouton "ok" SI l'analyse est complète (pour "Le Nombre d'Or") OU que l'analyse du joueur est de la taille de la combinaison
		   ET que les symboles sont dans le bon ordre (pour "Le Mastermind"). */
		if(comptMoinsEgalePlus == tailleCombi || (comptChoixJoueur[0] == tailleCombi && comptEtoiles == tailleCombi - 1)) {
			activerOK();
		}
				
	}
	
}
