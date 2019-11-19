package representations;

import representations.RestrictedDomain;
import java.util.*;


/**
 * Cette classe implémente l'interface Constraint,
 * qui permet de générer des règles spécifiques.
 */
public class Rule implements Constraint{

    /**
     * Attribut premise, il contient ce qui impliquera la conclusion. Pour rappel, une règle est composée de prémisse(s) et de conclusion(s).
     */
    private Set<RestrictedDomain> premise;

    /**
     * Attribut conclusion, il contient l'implication de la prémisse.
     */
    private Set<RestrictedDomain> conclusion;

    /**
     * Constructeur de la classe qui prend deux paramètres en entrée.
     * @param premise Prémisse qui va contenir l'impliquant.
     * @param conclusion Conclusion qui va contenir l'impliquation.
     */
    public Rule(Set<RestrictedDomain> premise, Set<RestrictedDomain> conclusion){
        this.conclusion = conclusion;
        this.premise = premise;
    }

    /**
     * Surcharge de la méthode getScope(),
     * elle nous retourne un Set de Variable contenant les prémisses,
     * ainsi que les conclusions.
     * @return une liste des variables présentes pour cette règle
     */
    @Override
    public Set<Variable> getScope(){
        Set<Variable> scope = new HashSet<Variable>();
        for(RestrictedDomain pre : this.premise){
            scope.add(pre.getVariable());
        }
        for(RestrictedDomain conc : this.conclusion){
            scope.add(conc.getVariable());
        }
        return scope;
    }

    /**
     * redéfinition de la méthode isSatisfiedBy(),
     * qui permet de savoir si un patient satisfait une règle.
     * @param map une Map de Variable qui sera le patient.
     * @return Un boolean qui renvoie true si c'est satisfait, ou false dans l'autre cas.
     */
    @Override
    public boolean isSatisfiedBy(Map<Variable, String> map){
        for(RestrictedDomain pre : this.premise){
            if(!pre.getSubDomain().contains(map.get(pre.getVariable()))){
                return true;
            }
        }
        for(RestrictedDomain conc : this.conclusion){
            if(conc.getSubDomain().contains(map.get(conc.getVariable()))){
                return true;
                    }
                }
        return false;
        }

    /**
     * Méthode qui permet de filtrer et permet de faire faire moins de calculs au backtracking.
     * @param domain Un Set de RestrictedDomain.
     * @param map Une Map de Variable-String.
     * @return Un boolean.
     */
    @Override
    public boolean filter(Set<RestrictedDomain> domain, Map<Variable, String> map){
        boolean res = false;
        Set<RestrictedDomain> copy = new HashSet<RestrictedDomain>(domain);
        Map<Variable, String> second_map = new HashMap<Variable,String>();

        for(RestrictedDomain restricted:copy){
            if(this.getScope().contains(restricted.getVariable())){
                Set<String> subdomain=new HashSet<String>(restricted.getSubDomain());
                for(String val :subdomain){
                    second_map.put(restricted.getVariable(),val);
                    Set<Variable> list_var = this.getScope();
                    list_var.remove(restricted.getVariable());
                    Variable var = (Variable) list_var.toArray()[0];
                    if(map.containsKey(var)){
                        second_map.put(var,map.get(var));
                        if(!this.isSatisfiedBy(second_map)){
                            restricted.getSubDomain().remove(map.get(var));
                            res = true;
                        }
                        second_map.remove(var);
                    }else{
                        for(String val2 : var.getDomain()){
                            second_map.put(var,val2);
                            if(!this.isSatisfiedBy(second_map)){
                              restricted.getSubDomain().remove(val2);
                              res = true;
                            }
                            second_map.remove(var);
                        }
                    }
                    second_map.remove(restricted.getVariable());
                }
            }
        }
        return res;
    }

	/**
	 * Méthode qui permet d'obtenir une Map de la premisse
	 * @return une Map avec variable ainsi que son sous-domaine
	 */
    public Map<Variable,String> getPremiseMap(){
		Map<Variable,String> mapPremise= new HashMap<Variable,String>();
		for(RestrictedDomain res : this.premise){
			mapPremise.put(res.getVariable(),res.getSubDomain().iterator().next());
		}
		return mapPremise;
    }

	/**
	 * Méthode qui permet d'obtenir la conclusion
	 * @return la conclusion de la règle
	 */
    public Set<RestrictedDomain> getConclusion(){
      return this.conclusion;
    }

    /**
     * Surcharge de la méthode toString(),
     * permettant d'afficher comme il faut la Rule.
     * @return Un String représentant la chaîne de caractères qui sera renvoyée.
     */
    @Override
    public String toString(){
        String res = "";
        for(RestrictedDomain r:premise){
          res+=" "+r;
        }
        res+="=>";
        for(RestrictedDomain d:conclusion){
          res+=" "+d;
        }
        return res;
    }

}
