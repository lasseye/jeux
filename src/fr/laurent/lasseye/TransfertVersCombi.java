/*
 * La classe "TransfertVersCombi" est une sous-classe de "TransfertChoix". Elle gère uniquement le transfert
 * des icones "chiffres ou couleurs" vers la zone "combinaison secrète" (en mode "Défendeur").
 */

package fr.laurent.lasseye;

public class TransfertVersCombi extends TransfertChoix{

	public TransfertVersCombi(int choixDuJeu, int choixDuMode, int comptModeDuel, int tailleCombi,Bouton ICONES[], Bouton icone, Bouton OK,
		   Bouton ecranMessages, Bouton MESSAGES[], Bouton ecranInvites[], Bouton INVITES[], Bouton zoneJeu[][], int coordBoutonsJeu[],
		   Bouton choixJoueur[], int comptChoixJoueur[], Bouton zoneValidation[][], int coordBoutonsValidation[], Bouton combiSecrete[][], int comptCombiSecrete[]) {
		
		
		super(choixDuJeu, choixDuMode, comptModeDuel, tailleCombi, ICONES, icone, OK, ecranMessages, MESSAGES, ecranInvites, INVITES, zoneJeu,
			  coordBoutonsJeu, choixJoueur,comptChoixJoueur, zoneValidation, coordBoutonsValidation, combiSecrete, comptCombiSecrete);
		
		
		
		/* 1. Tant que les choix du joueur n'atteignent pas la taille de la combinaison, effectue 3 opérations sur la zone "combinaison secrète" :
		           - Détecte les cases vides;
		           - Détecte les doublons;
		           - Transfère l'icone (si pas de doublon) ou affiche un message (en cas de doublon). */
		
			detecterCaseVide(combiSecrete, coordBoutonsJeu, 21);
			if (choixDuJeu == 10) detecterDoublon(icone, combiSecrete, coordBoutonsJeu);
		
			if (comptDoublon == 0) {
				transfererIcone(combiSecrete, coordBoutonsJeu);
				poserEcouteur(combiSecrete, coordBoutonsJeu);
			}
			else { 
				afficherMessage();
				comptChoixJoueur[0] = comptChoixJoueur[0] - 1;
				
				logger.info(icone.getIcon() + " - ATTENTION, DOUBLON !"); // -------> log
				
			} 
		
		comptChoixJoueur[0] = comptChoixJoueur[0] + 1;
		
	
		/* 2. Active le bouton "ok" dès que la taille de la "combinaison secrète" est atteinte. */
		if(comptChoixJoueur[0] == tailleCombi)	{
			activerOK();
		}
	
	}

}
