package datamining;

import java.util.*;
import representations.*;

/**
 * Classe qui représente la fréquence de notre data
 */
public class FrequentItemsetMiner{

	/**
     * Attribut dataBase, il contient notre data avec les variables et transactions
     */
	private BooleanDatabase dataBase;

	/**
     * Constructeur de la classe.
     * @param dataBase une data avec les variables et transactions
     */
	public FrequentItemsetMiner(BooleanDatabase dataBase){
		this.dataBase=dataBase;
	}

	/**
	 * Méthode pour obtenir une Map de Set Variable et un Integer pour avoir la fréquence
     * @param minfr la fréquence minimum
     * @return une Map de Set de Variable et un Integer qui correspond à la fréquence de la Set de Variable
     */
	public Map<Set<Variable>,Integer> frequentItemsets(int minfr){
		Map<Set<Variable>,Integer> freqMin = new HashMap<Set<Variable>,Integer>();
		freqMin.put(new HashSet<Variable>(),this.dataBase.getTransacts().size());
		Set<Set<Variable>> elementDeb = new HashSet<Set<Variable>>(freqMin.keySet());
		for(Variable v : this.dataBase.getVars()){
			Set<Variable> elementVar = new HashSet<Variable>(){{add(v);}};
			int fr = this.calcFrequence(elementVar);
			if(fr>=minfr){
				Set<Set<Variable>> choiceList = new HashSet<Set<Variable>>(freqMin.keySet());
				for(Set<Variable> elements : choiceList){
					Set<Variable> listVar = new HashSet<Variable>(elementVar);
					listVar.addAll(elements);
					int fr2 = this.calcFrequence(listVar);
					if(fr2>=minfr){
						freqMin.put(listVar,fr2);
					}
				}
			}
		}
		freqMin.keySet().removeAll(elementDeb);
		return freqMin;
	}

	/**
	 * Méthode pour obtenir la fréquence d'une Set de Variable
     * @param variableA la Set de Variable dont il faut calculer la fréquence
     * @return la fréquence de variableA
     */
	public int calcFrequence(Set<Variable> variableA){
		int i = 0;
		for(Map<Variable,String> map :this.dataBase.getTransacts()){
			boolean lock = false;
			for(Variable v : variableA){
				if(map.get(v).equals("0")){
					lock = true;
				}
			}
			if(lock == false){
				i++;
			}
		}
		return i;
	}
}
