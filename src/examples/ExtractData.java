package examples;

import java.util.*;
import representations.*;
import datamining.*;

/**
 * Cette classe représente un lanceur pour les algorithmes de manipulation et utilisation des data
 */
public class ExtractData{

	/**
     * Méthode qui permet de lancer les manipulations de data
     * @param args les arguments au lancement
     */
	public static void main(String [] args){
		int frequence = 0;
		Double confiance = 0.0;
		String chemin = "";
		boolean verrou=false;
		ReadingDatabase readingMode = new ReadingDatabase();
		
		if(args.length!=3){
			System.out.println("Le nombre d'arguments est faux !!!");
			System.out.println("merci de mettre sous la forme :");
			System.out.println("<frequence> <confiance> <nom du fichier>");
			System.out.println("(le fichier dois se trouver dans le dossier bases)");
			verrou=true;
		}else{
			chemin = args[2];
			try{
				frequence = Integer.parseInt(args[0]);
				confiance = Double.parseDouble(args[1]);
			}catch(Exception e){
				System.out.println("Erreur dans la fréquence ou la confiance !!");
				verrou=true;
			}
			if(!readingMode.fileExist("bases/"+chemin)){
				System.out.println("impossible de lire le fichier (a mettre dans le dossier bases)");
				verrou=true;
			}
		}
		if(verrou==false){
			Database db = readingMode.transformDb("bases/"+chemin);
			BooleanDatabase booleanDb = db.transformToBooleanDatabase();
			FrequentItemsetMiner freq = new FrequentItemsetMiner(booleanDb);
			Map<Set<Variable>,Integer> result = freq.frequentItemsets(frequence);
			AssociationRuleMiner arm=new AssociationRuleMiner(result);
			Map<Rule,Double> map=arm.associationRule(confiance);
			System.out.println("regles = ");
			for(Rule r:map.keySet()){
				System.out.println(r+"   "+map.get(r));
			}
		}
	}
}
