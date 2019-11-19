package datamining;

import java.util.*;
import representations.*;

/**
 * Classe qui représente notre data
 */
public class BooleanDatabase{
	
	/**
     * Attribut vars, il contient la liste des variables de notre data
     */
	private List<Variable> vars;
	
	/**
     * Attribut transacts, il contient la liste de Map de Variable-String qui correspond aux transactions réalisées
     */
	private List<Map<Variable,String>> transacts;
	
	/**
     * Constructeur de la classe.
     * @param vars la liste des variables de notre data
     * @param transacts les transactions réalisées
     */
	public BooleanDatabase(List<Variable> vars,List<Map<Variable,String>> transacts){
		this.vars=vars;
		this.transacts=transacts;
	}

	/**
     * Méthode pour obtenir la liste des variables
     * @return la liste des variables
     */
	public List<Variable> getVars(){
		return this.vars;
	}

	/**
     * Méthode pour obtenir la liste des transactions
     * @return la liste des transactions
     */
	public List<Map<Variable,String>> getTransacts(){
		return this.transacts;
	}
}
