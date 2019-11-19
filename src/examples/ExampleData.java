package examples;

import java.util.*;
import representations.*;
import datamining.*;

/**
 * Cette classe représente un lanceur pour les algorithmes de manipulation et utilisation des data
 */
public class ExampleData{

	/**
     * Méthode qui permet de lancer les manipulations de data
     * @param args les arguments au lancement
     */
	public static void main(String [] args){
		System.out.println("Lancement du cas du cours :");
		Set<String> domaine = new HashSet<String>();
		domaine.add("0");
		domaine.add("1");
		List<Variable> vars = new ArrayList<Variable>();
		Variable A = new Variable("A",domaine);
		Variable B = new Variable("B",domaine);
		Variable C = new Variable("C",domaine);
		Variable D = new Variable("D",domaine);
		Variable E = new Variable("E",domaine);
		vars.add(A); vars.add(B); vars.add(C); vars.add(D); vars.add(E);
		
		List<Map<Variable,String>> transacts = new ArrayList<Map<Variable,String>>();
		
		Map<Variable,String> tip1 = new HashMap<Variable,String>();
		tip1.put(A,"1");tip1.put(B,"1");tip1.put(C,"1");tip1.put(D,"1");tip1.put(E,"1");transacts.add(tip1);
		
		Map<Variable,String> tip2 = new HashMap<Variable,String>();
		tip2.put(A,"1");tip2.put(B,"0");tip2.put(C,"1");tip2.put(D,"0");tip2.put(E,"0");transacts.add(tip2);
		
		Map<Variable,String> tip3 = new HashMap<Variable,String>();
		tip3.put(A,"1");tip3.put(B,"1");tip3.put(C,"1");tip3.put(D,"1");tip3.put(E,"0");transacts.add(tip3);
		
		Map<Variable,String> tip4 = new HashMap<Variable,String>();
		tip4.put(A,"0");tip4.put(B,"1");tip4.put(C,"1");tip4.put(D,"0");tip4.put(E,"0");transacts.add(tip4);
		
		Map<Variable,String> tip5 = new HashMap<Variable,String>();
		tip5.put(A,"1");tip5.put(B,"1");tip5.put(C,"1");tip5.put(D,"0");tip5.put(E,"0");transacts.add(tip5);
		
		Map<Variable,String> tip6 = new HashMap<Variable,String>();
		tip6.put(A,"0");tip6.put(B,"0");tip6.put(C,"0");tip6.put(D,"0");tip6.put(E,"1");transacts.add(tip6);
		
		BooleanDatabase booldata = new BooleanDatabase(vars,transacts);
		FrequentItemsetMiner freq = new FrequentItemsetMiner(booldata);
		System.out.println("frequence = 3");
		Map<Set<Variable>,Integer> result = freq.frequentItemsets(3);
		AssociationRuleMiner arm=new AssociationRuleMiner(result);
		System.out.println("réponse = "+result);
		System.out.println("confiance = 0");
		Map<Rule,Double> map=arm.associationRule(0.0);
		System.out.println("regles = ");
		for(Rule r:map.keySet()){
			System.out.println(r+"   "+map.get(r));
		}
	}
}
