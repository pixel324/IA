Auteurs du projet :
	GALLIS ROBIN 21700872
	LOMET MATHEO 21703722
	MAURAND PIERRE 21702704
	MIALON LAURINE 21705826

Démarrage du programme :
	
	Notre fil rouge étant composé de plusieurs parties distinctes, nous avons donc crées plusieurs lanceurs afin de gagner du temps évitant ainsi de tout compiler à chaques fois.

	Afin de démarrer le lanceur désiré : 
		Étape 1 - Démarrer un temrinal à la racine des fichiers (normalement tout les lanceurs aparraissent).
		Étape 2 - Taper dans ce terminal la commande : sh <nom du lanceur désiré>.

Les différents lanceurs :
	
	ExampleDataLauncher.sh :

		Ce lanceur permet de donner les résultats de l'exemple du cours de M. Crémilleux.
		Nous allons donc avoir la fréquence, la réponse,la confiance ainsi que les règles.
		Ce lanceur ne nécéssite pas de paramètres supplémentaires.

	PlanningLauncher.sh :
		
		Ce lanceur permet d'obtenir la réponse au problème donné via des algorithmes de recherche.
		Nous allons donc apercevoir l'état initial, l'état final ainsi que les résultats
		des différents algorithmes (dfs,bfs,dijkstra,a*). Le nombre de noeuds explorés est aussi indiqué.
		Ce lanceur ne nécéssite pas de paramètres supplémentaires.

	BacktrackLauncher.sh :

		Ce lanceur permet d'obtenir les solutions d'un problème via un algorithme de backtrack.
		Seule les solutions seront renvoyés.
		Ce lanceur nécéssite des paramètres qui vous seront demandés lors du démarrage du 
		".sh".
		Ces paramètres permettent le tri par des heuristiques.
		Voici un arbre résumant le choix des paramètres.

		Ecrire 1 pour un heuristique qui trie les variables
			|
			| --> Ecrire 1 pour un choix de la variable la moins contrainte
			| --> Ecrire 2 pour un choix de la variable le plus contrainte
			| --> Ecrire 3 pour un choix de la variable avec le domaine le plus petit
			| --> Ecrire 4 pour un choix de la variable avec le domaine le plus grand
		Ecrire 2 pour un heuristique qui trie aléatoirement
		Ecrire 3 pour un heuristique qui trie les valeurs
			| --> Ecrire 1 pour un choix de la plus petite valeur
			| --> Ecrire 2 pour un choix de la plus grnade valeur

	DataLauncher.sh :

		Ce lanceur nous permet de visionner les résultats du DataMining sur les bases de données
		Il vous posera donc 3 questions afin de remplir les paramètres.
		La première vous demandera la fréquence des Itemset désirée.
		La deuxième vous demandera la confiance voulue.
		La dernière vous demandera le nom du fichier dans la base de donnée à choisir.

	JavadocGenerator.sh :

		Permet la génération de la javadoc de notre programme.
