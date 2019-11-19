package planning;

import representations.*;
import java.util.*;

/**
 * Cette classe représente une action
 */
public class Action{

	/**
     * Attribut rules, il contient la liste des règles.
     */
	private Set<Rule> rules;

	/**
     * Attribut cost, le cout de l'action.
     */
	private int cost;

	/**
     * Attribut name, le nom de l'action.
     */
	private String name;

	/**
     * Constructeur de la classe qui prend deux paramètres en entrée.
     * @param rules la liste des règles.
     * @param name le nom de l'action.
     * @param cost le cout de l'action
     */
	public Action(Set<Rule> rules,String name,int cost){
		this.rules=rules;
		this.name=name;
		this.cost=cost;
	}

	/**
     * Méthode qui permet de savoir si un état est applicable à l'action.
     * @param state l'état qui doit correspondre.
     * @return si cet état est applicable.
     */
	public boolean isApplicable(State state){
		for(Rule r: this.rules){
			if(state.satisfies(new State(r.getPremiseMap()))){
				return true;
			}
		}
		return false;
	}

	/**
     * Méthode qui permet de faire un état avec les modifications.
     * @param state l'état de départ.
     * @return le nouvel état.
     */
	public State apply(State state){// à voir
		State state2=new State(state);
		if(isApplicable(state2)){//remis
			for(Rule r:this.rules){
				if(state.satisfies(new State(r.getPremiseMap()))){
					for(RestrictedDomain d:r.getConclusion()){
						state2.put(d.getVariable(),d.getSubDomain().iterator().next());
					}
				}
			}
		}
		return state2;
	}

    /**
     * Surcharge de la méthode toString(),
     * permettant d'afficher comme il faut l'action.
     * @return Un String représentant le nom de l'action.
     */
	public String toString(){
		return "Action: "+this.name;
	}

	/**
     * Méthode qui permet d'obtenir le nom de l'action
     * @return le nom de l'action.
     */
	public String getName(){
		return this.name;
	}

	/**
     * Méthode qui permet d'obtenir le cout de l'action
     * @return le cout de l'action.
     */
	public int getcost(){
		return this.cost;
	}
	/**
	* Méthode qui permet d'obtenir les règles de l'action
    * @return les règles de l'action.
	*/
	public Set<Rule> getRules(){
		return this.rules;
	}
}
