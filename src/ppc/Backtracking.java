package ppc;

import java.util.*;
import representations.*;

/**
 * Cette classe représente le Backtracking qui permet de
 * trouver toutes les solutions possibles, en retournant en arrière
 * si jamais elle ne satisfait pas les contraintes.
 */
public class Backtracking{

	/**
     * Attribut variables, qui est un Set<Variable>.
     */
	private Set<Variable> variables;

	/**
     * Attribut contraintes, qui est un Set<Rule>.
     */
	private Set<Rule> contraintes;

	/**
     * Constructeur de la classe.
     * @param contraintes Set de Rule qui contient toutes les règles.
     */
	public Backtracking(Set<Rule> contraintes){
		this.contraintes = contraintes;
		this.variables = new HashSet<Variable>();
		for(Rule regle : contraintes){
			this.variables.addAll(regle.getScope());
		}
	}

	/**
	 * Méthode pour otenir une Set des Variables
     * @return la Set des Variables
     */
	public Set<RestrictedDomain> getVariables(){
		Set<RestrictedDomain> res=new HashSet<RestrictedDomain>();
		for(Variable var:this.variables){
			res.add(new RestrictedDomain(var,var.getDomain()));
		}
		return res;
	}

	/**
	 * Méthode qui permet de trouver l'arc-consistance si il existe.
	 * @param mapVerif2 un Map de Variable-String qui contient les valeurs à tester pour l'arc-consistance.
	 * @return Une Set de RestrictedDomain qui donnera l'arc-consistance.
	 */
	public Set<RestrictedDomain> arc_consistance(Map<Variable, String> mapVerif2){
		Map<Variable, String> mapVerif=new HashMap<Variable, String>(mapVerif2);
		Set<RestrictedDomain> liste = new HashSet<RestrictedDomain>();
		Set<Variable> varlist=new HashSet<Variable>(this.variables);
		for(Variable variable : varlist){
			if(!mapVerif.containsKey(variable)){
				liste.add(new RestrictedDomain(variable,new HashSet<String>(variable.getDomain())));
			}
		}
		for(Rule rule : this.contraintes){
			rule.filter(liste, mapVerif);
		}
		return (liste);
	}

	/**
	 * Méthode qui permet de trouver les différentes solutions,
	 * @param assignationPartielle un Set de Map de Variable-String qui contient l'assignation partielle.
	 * @param listeVar un Set de Variable contenant les Variables non-assignées.
	 * @return La liste de toutes les solutions possibles.
	 */
	public Set<Map<Variable,String>> backtrack(Set<Map<Variable,String>> assignationPartielle,Set<RestrictedDomain> listeVar, String action){
		Set<Map<Variable,String>> assignationTotal = new HashSet<Map<Variable,String>>();
		Set<RestrictedDomain> lar = new HashSet<RestrictedDomain>(listeVar);
		// trier listeVar par heuristique
		if(action.contains("tri")){
			lar = new HashSet<RestrictedDomain>(triDansVariable(lar,action));
		//trier aleatoirement heuristique
		}else if(action.contains("alea")){
			lar = new HashSet<RestrictedDomain>(triAleatoire(lar));
		}
		for(RestrictedDomain variable : listeVar){
			lar.remove(variable);
			for(Map<Variable,String> assignation : assignationPartielle){
				//trie valeur heuristique
				if(action.contains("valeurs")){
					lar = new HashSet<RestrictedDomain>(triDansValeur(lar,action));
				}
				for(String sousDomain : variable.getSubDomain()){
					Set<RestrictedDomain> domain = arc_consistance(assignation);
					Map<Variable,String> assign2 = new HashMap<Variable,String>(assignation);
					assign2.put(variable.getVariable(),sousDomain);
					boolean bool = true;
					for(Rule rule : this.contraintes){
						if(!rule.isSatisfiedBy(assign2)){
							bool = false;
						}
					}
					if(bool!=false){
						if(lar.size()!=0){
							Set<Map<Variable,String>> test2 = new HashSet<Map<Variable,String>>();
							test2.add(assign2);
							Set<Map<Variable,String>> test = backtrack(test2,domain,action);
							if(test!=null){
								assignationTotal.addAll(test);
							}
						}else{
							assignationTotal.add(assign2);
						}
					}
				}
			}
			lar.add(variable);
		}
		if(assignationTotal.size() != 0){
			return assignationTotal;
		}
		return null;
	}

	/**
	 * Méthode qui permet de mettre une valeur à chaque variable
	 * @param set la liste de variable
	 * @param demande le type de liste
	 * @return La Map des variables qui ont obtenu une valeur
	 */
	public Map<RestrictedDomain,Integer> nombreDansVariable(Set<RestrictedDomain> set, String demande){
		Map<RestrictedDomain,Integer> tableauNombreDansVariable = new HashMap<RestrictedDomain,Integer>();
		for(RestrictedDomain d: set){
			if(tableauNombreDansVariable.get(d)==null){
				tableauNombreDansVariable.put(d,0);
			}
			if(demande.contains("contrainte")){
				for(Rule r:this.contraintes){
					if(r.getScope().contains(d.getVariable())){
						tableauNombreDansVariable.put(d,tableauNombreDansVariable.get(d)+1);
					}
				}
			}
			if(demande.contains("domaine")){
				tableauNombreDansVariable.put(d,d.getSubDomain().size());
			}
			if(demande.contains("valeur")){
				Variable v = d.getVariable();
				int val = 1;
				for(String valeur : v.getDomain()){
					if(d.getSubDomain().contains(valeur)){
						tableauNombreDansVariable.put(d,val);
					}
					val++;
				}
			}
		}
		return tableauNombreDansVariable;
	}

	/**
	 * Méthode qui permet de mettre de faire un tri des variables
	 * @param set la liste de variable
	 * @param s le type de liste
	 * @return La Liste des variables trier comme demandé
	 */
	public List<RestrictedDomain> triDansVariable(Set<RestrictedDomain> set,String s){
		Map<RestrictedDomain,Integer> m = nombreDansVariable(set,s);
		List<RestrictedDomain> res = new ArrayList<RestrictedDomain>();
		while(m.size()!=0){
			RestrictedDomain PlusFort = null;
			int nbFois = Integer.MIN_VALUE;
			if(s.contains("MoinsVariable")){
				nbFois = Integer.MAX_VALUE;
			}
			for(Map.Entry dom : m.entrySet()){
				if(s.contains("PlusVariable")){
					if(((int)dom.getValue())>nbFois){
						nbFois = m.get(dom.getKey());
						PlusFort = (RestrictedDomain)dom.getKey();
					}
				}
				if(s.contains("MoinsVariable")){
					if(((int)dom.getValue())<nbFois){
						nbFois = m.get(dom.getKey());
						PlusFort = (RestrictedDomain)dom.getKey();
					}
				}
			}
			res.add(PlusFort);
			m.remove(PlusFort);
		}
		return res;
	}

	/**
	 * Méthode qui permet de mettre de faire un tri dans les valeurs
	 * @param set la liste de variable
	 * @param s le type de liste
	 * @return La Liste des variables trier comme demandé
	 */
	public List<RestrictedDomain> triDansValeur(Set<RestrictedDomain> set,String s){
		Map<RestrictedDomain,Integer> m = nombreDansVariable(set,"valeur");
		List<RestrictedDomain> res = new ArrayList<RestrictedDomain>();
		while(m.size()!=0){
			RestrictedDomain PlusFort = null;
			int nbFois = Integer.MIN_VALUE;
			if(s.contains("MoinsValeur")){
				nbFois = Integer.MAX_VALUE;
			}
			for(Map.Entry dom : m.entrySet()){
				if(s.contains("PlusValeur")){
					if(((int)dom.getValue())>nbFois){
						nbFois = m.get(dom.getKey());
						PlusFort = (RestrictedDomain)dom.getKey();
					}
				}
				if(s.contains("MoinsValeur")){
					if(((int)dom.getValue())<nbFois){
						nbFois = m.get(dom.getKey());
						PlusFort = (RestrictedDomain)dom.getKey();
					}
				}
			}
			res.add(PlusFort);
			m.remove(PlusFort);
		}
		return res;
	}
	
	/**
	 * Méthode qui permet de mettre de faire un tri aléatoire
	 * @param set1 la liste de variable
	 * @return La Liste des variables trier aléatoirement
	 */
	public Set<RestrictedDomain> triAleatoire(Set<RestrictedDomain>set1){
		List<RestrictedDomain> set = new ArrayList<RestrictedDomain>(set1);
		Collections.shuffle(set);
		Set<RestrictedDomain> returnSet = new HashSet<RestrictedDomain>(set);
		return returnSet;
	}
}
