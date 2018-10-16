Cette application propose 2 jeux de logique basés sur la recherche d'une combinaison secrète :
	- 1 combinaison constituée de chiffres --> "Le Nombre d'Or";
	- 1 combinaison constituée de couleurs --> "Le Mastermind".

Pour importer le projet sur Eclipse, veuillez suivre les instructions du PDF ci-joint : en cours d'écriture.

Pour lancer l'application, ouvrez la classe "LancementJeu" - elle contient la méthode "main()" - puis cliquez sur le bouton "Run"
dans la barre d'outils de Eclipse.

ATTENTION : L'application dispose d'un fichier "config.properties" qui contient 4 paramètres :
	1. le nombre de chiffres ou de couleurs disponibles : de 4 à 10 (7 par défaut);
	2. la taille de la combinaison : 4 ou 5 (4 par défaut);
	3. le nombre d'essais : de 5 à 10 (10 par défaut);
	4. le mode développeur : false ou true (false par défaut = la combinaiosn secrète est cachée).
Ces paramètres permettent de modifier l'état du jeu.
Vérifiez que le chemin d'accès à ce fichier soit correctement "balisé" à la ligne 101 de la classe "ConfigurationJeu". Dans le cas
contraire, l'application propose les valeurs par défaut (7 chiffres ou couleurs disponibles, une combinaison secrète de 4 éléments,
10 essais possibles et le mode développeur inactif).


Au lancement du jeu, une fenêtre "Accueil" vous permet de choisir entre 2 jeux ("Le Nombre d'Or" ou "Le Mastermind") et 3 modes 
("Challenger", "Défendeur" et "Duel"). Par défaut, l'application propose "Le Mastermind" en mode "Challenger".

"Le Nombre d'Or" présente une combinaison secrète faite de chiffres ; "Le Mastermind", une combinaison faite de couleurs.

En mode "Challenger", vous devez trouver la combinaison de l'ordinateur en fonction de l'analyse qu'il fait de vos propositions.
En mode "Défendeur", l'ordinateur doit trouver votre combinaison en fonction de l'analyse que vous faites de ces propositions.
En mode "Duel", on alterne les deux modes ("Challenger" d'abord puis "Défendeur").


EXPLICATIONS PARTICULIERES :
"Le Nombre d'Or" : Il est possible de choisir plusieurs fois le même chiffre.
"Le Mastermind" : Il est IMPOSSIBLE de choisir plusieurs fois la même couleur (en cas d'oubli, l'application vous le rappelle).

Pour "Le Nombre d'Or", l'analyse se fait dans l'ordre des chiffres : analyse en 1 correspond au chiffre 1, analyse en 2 correspond
au chiffre 2, etc.
Pour "Le Mastermind", l'analyse présente d'abord les couleurs placées, puis les couleurs présentes et enfin les couleurs absentes.
L'analyse en 1 ne correspond pas forcément à la couleur 1.
L'ORDINATEUR RESPECTE CETTE LOGIQUE POUR ANALYSER VOS PROPOSITIONS - VOUS DEVEZ EN FAIRE DE MÊME !


Depuis la fenêtre "Jeu", vous pouvez soit revenir à la fenêtre "Accueil" pour changer de jeux et de modes, soit rejouer au même jeu
dans le même mode, soit quitter l'application.

Bon jeu !

Laurent

