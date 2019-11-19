package datamining;

import java.util.*;
import representations.*;

public class Database{

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
	public Database(List<Variable> vars,List<Map<Variable,String>> transacts){
		this.vars=vars;
		this.transacts=transacts;
	}
	
	public BooleanDatabase transformToBooleanDatabase(){
		List<Variable> listVar = new ArrayList<Variable>(this.vars);
		List<Map<Variable,String>> transactList = new ArrayList<Map<Variable,String>>(this.transacts);
		for(Variable var : this.vars){
			if(var.getDomain().size()>2){
				for(String situation : var.getDomain()){
					Set<String> domain = new HashSet<String>(){{add("1");add("0");}};
					Variable newsVariable = new Variable(var.getName()+":"+situation,domain);
					listVar.add(newsVariable);
					int position=0;
					for(Map<Variable,String> map : this.transacts){
						if(map.get(var).equals(situation)){
							transactList.get(position).put(newsVariable,"1");
						}else{
							transactList.get(position).put(newsVariable,"0");
						}
						position+=1;
					}
					
				}
				listVar.remove(var);
			}
		}
		return new BooleanDatabase(listVar,transactList);
	}
	
}
