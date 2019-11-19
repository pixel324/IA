package planning;

import java.util.*;
import representations.*;

/**
 * Cette classe représente un état
 */
public class State{
	
	/**
     * Attribut map, il contient les éléments de notre état.
     */
	private Map<Variable,String> map;

	/**
     * Constructeur de la classe qui prend un paramètre en entrée.
     * @param map les éléments de notre état.
     */
	public State(Map<Variable, String> map){
		this.map =new HashMap<Variable, String>(map);
	}

	/**
     * Constructeur de la classe qui prend un paramètre en entrée.
     * @param state un état.
     */
	public State(State state){
		this.map =new HashMap<Variable, String>(state.getMap());
	}

	/**
     * Constructeur de la classe qui prend un paramètre en entrée.
     */
	public State(){
		this.map=new HashMap<Variable, String>();
	}
	
	/**
     * Méthode qui permet de savoir si un autre état nous satisfait
     * @param finalS un état à tester.
     * @return si l'état est satisfait
     */
	public boolean satisfies(State finalS){
		for(Variable v:finalS.getMap().keySet()){
			if(!(this.getMap().containsKey(v)) || this.map.get(v)!=finalS.getMap().get(v)){
				return false;
			}
		}
		return true;
	}
	
	/**
     * Méthode qui permet d'obtenir notre liste d'éléments
     * @return notre map
     */
	public Map<Variable, String> getMap(){
		return this.map;
	}

	/**
     * Méthode qui permet d'ajouter un élément à notre état
     * @param v une variable
     * @param val une valeur de sous-domaine
     */
	public void put(Variable v,String val){
		this.map.put(v,val);
	}
	
    /**
     * Surcharge de la méthode toString(),
     * permettant d'afficher comme il faut l'état.
     * @return Un String représentant la map des éléments constitutifs de l'état.
     */
	public String toString(){
		return this.map.toString();
	}
	
	/**
     * Méthode qui permet de comparé deux éléments
     * @param o l'objet à comparer
     * @return si l'objet est identique à notre état
     */
	public boolean equals(Object o){
		if(!(o instanceof State)){
			return false;
		}
		State s=(State) o;
		return this.map.equals(s.map);
	}

	/**
	*Surcharge de la méthode hashCode.
	*@return le hashCode de la map.
	*/
	public int hashCode(){
		return this.map.hashCode();
	}
}
