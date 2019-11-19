package datamining;

import java.util.*;
import representations.*;

/**
 * Classe qui représente la confiance de l'association des règles
 */
public class AssociationRuleMiner{
	
	/**
     * Attribut itemSet, il contient les listes de variables suivies de la fréquence de la liste
     */
	private Map<Set<Variable>, Integer> itemSet;
	
	/**
     * Constructeur de la classe.
     * @param itemSet la Map de Set de variable et d'Integer qui représente les listes de variables suivies de la fréquence
     */
	public AssociationRuleMiner(Map<Set<Variable>, Integer> itemSet){
		this.itemSet=itemSet;
	}
	
	/**
     * Méthode d'association des règles pour obtenir la confiance de notre Attribut itemSet
     * @param minfr la fréquence minimum
     * @return une Map de Rule-Double qui correspond à la confiance de la règle
     */
	public Map<Rule,Double> associationRule(Double minfr){
		Map<Rule,Double> mapRule=new HashMap<Rule,Double>();
		for(Set<Variable> set:this.itemSet.keySet()){
			Map<Set<Variable>, Integer> tmp = new HashMap<Set<Variable>, Integer>(this.itemSet);
			tmp.remove(set);
			for(Set<Variable> setVar:tmp.keySet()){
				boolean lock = false;
				for(Variable var : set){
					if(setVar.contains(var)){
						lock = true;
					}
				}
				if(!lock){
					Set<Variable> test = new HashSet<Variable>(set);
					test.addAll(setVar);
					if(itemSet.keySet().contains(test)){
						Set<RestrictedDomain> premise=new HashSet<RestrictedDomain>();
						Set<RestrictedDomain> conclusion=new HashSet<RestrictedDomain>();
						for(Variable var : set){
							premise.add(new RestrictedDomain(var,new HashSet<String>(){{add("1");}}));
						}
						for(Variable var : setVar){
							conclusion.add(new RestrictedDomain(var,new HashSet<String>(){{add("1");}}));
						}
						Rule rule=new Rule(premise,conclusion);
						Double trust = this.itemSet.get(test)/Double.valueOf(this.itemSet.get(set));
						if(trust>=minfr){
							mapRule.put(rule,trust);
						}
						
					}
				}
			}
		}
		return mapRule;
	}
}
