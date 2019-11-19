package planning;

import java.util.*;

import planning.Action;
import representations.*;
/*Résultats : Avec nos résultats, l'algorithme dijkstra parcoure en moyenne 30 noeuds alors que le A* en parcoure entre 20 et 25 en moyenne
De ce fait ces résultats sont cohérents car pour le A* on va orienter la recherche, ce qui va evidemment diminier le nombre de noeuds*/
/**
 * Classe qui représente le problème avec cout.
 */
public class PlanningProblemWithCost extends PlanningProblem{
	
	/**
     * Constructeur de la classe qui prend 3 paramètres en entrée.
     * @param initialState l'état initial.
     * @param finalState le ou les états à obtenir à la fin.
     * @param actions toutes les actions possibles.
     */
    public PlanningProblemWithCost(State initialState,Set<State> finalState,Set<Action> actions){
        super(initialState,finalState,actions);
    }
	
	/**
	* Méthode dijkstra qui permet de trouver la meilleur solution en tenant compte du coût d'une action par la méthode de Dijkstra.
	* @return la liste des actions réalisés (chemin vers la solution) sous forme d'une pile.
	*/
    public Stack dijkstra(){
		//initialisation
        Map<State,Integer> distance = new HashMap<State,Integer>();
        Map<State,State> father = new HashMap<State,State>();
        List<State> open = new ArrayList<State>();
        Map<State,Action> plan = new HashMap<State,Action>();
        Set<State> goals =new HashSet<State>();
        open.add(this.initialState);
        distance.put(this.initialState,0);
        father.put(this.initialState,null);
        while(open.size()!=0){
       //faire le argmin
       this.count++;
			 State state = argmin(open,distance);
             open.remove(state);
             //regarde si il satisfait nos but finaux :
             if(satisfiesFinal(state)==true){
				           goals.add(state);
			       }
             for(Action a : this.actions){
                if(a.isApplicable(state)){
                    State next = a.apply(state);
                    //ajout du next si il n'existe pas dans la liste des distances :
		                if(!distance.containsKey(next)){
                        distance.put(next,Integer.MAX_VALUE);
                    }
                    //obtenir la valeur de la distance de next :
                    if(distance.get(next) > (distance.get(state)+(a.getcost()))){
                        distance.put(next,(distance.get(state)+a.getcost()));
                        father.put(next,state);
                        plan.put(next,a);
                        open.add(next);
                    }
                }
            }
        }
        return get_dijkstra_plan(father,plan,goals,distance);
    }
    
	/**
     * Méthode qui permet de mettre en forme le chemin vers la solution pour le dijkstra.
     * @param father les liens entre les états testés
     * @param actions liens entre les états et l'action réalisée
     * @param goals les états finaux atteints
	 * @param distance les distances parcourues pour arriver a l'etat
     * @return la liste des actions réalisées (chemin vers la solution)
     */
    public Stack<Action> get_dijkstra_plan(Map<State,State> father,Map<State,Action> actions,Set<State> goals,Map<State,Integer> distance){
		return get_bfs_plan(father,actions,argmin(new ArrayList<State>(goals),distance));
    }
	
	/**
	* Méthode qui renvoie une approximation du coût necessaire pour atteindre le but.
	* @param state l'état actuel
	* @return l'approximation du coût
	*/
  	private int simpleHeuristic(State state){
      int nbLevel =0;
      Map<Variable,String> map=state.getMap();
      for(Variable v:map.keySet()){
        if(map.get(v).equals("haute")){
          nbLevel+=3;
        }else if(map.get(v).equals("moyenne")){
          nbLevel+=2;
        }else if(map.get(v).equals("basse") || map.get(v).equals("oui")){
          nbLevel++;
        }
      }
  		return nbLevel;
  	}
	
	/**
	* Méthode qui renvoie une approximation du coût necessaire pour atteindre le but, plus informée que simpleHeuristic.
	* @param state l'état actuel
	* @return l'approximation du coût
	*/
    private int informedHeuristic(State state){
      return this.simpleHeuristic(state) * state.getMap().size();
    }
	
	/**
	* Méthode A* qui permet de trouver la meilleur solution en tenant compte du coût d'une action, cette méthode est plus optimisée que dijkstra.
	* @return la liste des actions réalisés (chemin vers la solution) sous forme d'une pile.
	*/
    public Stack ASTAR(){
		//initialisation
        Map<State,Integer> distance = new HashMap<State,Integer>();
        Map<State,State> father = new HashMap<State,State>();
        List<State> open = new ArrayList<State>();
        Map<State,Integer> value = new HashMap<State,Integer>();
        Map<State,Action> plan = new HashMap<State,Action>();
        value.put(this.initialState,this.informedHeuristic(this.initialState));
        open.add(this.initialState);
        distance.put(this.initialState,0);
        father.put(this.initialState,null);

        while(open.size()!=0){
      //faire le argmin
      this.count++;
			State state = argmin(open,distance);

            //regarde si il satisfait nos buts finaux :
            if(satisfiesFinal(state)==true){
				return get_bfs_plan(father,plan,state);
			}

            open.remove(state);

            for(Action a : this.actions){
                if(a.isApplicable(state)){
                    State next = a.apply(state);
                    //ajout du next si il n'existe pas dans la liste des distances :
		                if(!distance.containsKey(next)){
                        distance.put(next,Integer.MAX_VALUE);
                    }
                    //obtenir la valeur de la distance de next :
                    if(distance.get(next) > (distance.get(state)+(a.getcost()))){
                        distance.put(next,(distance.get(state)+a.getcost()));
                        father.put(next,state);
                        plan.put(next,a);
                        open.add(next);
                    }
                }
            }
        }
        return null;
    }
	/**
	* Méthode qui permet de trouver de trouver l'etat appartenant à la liste ayant la plus petite distance.
	* @param open la liste dans laquelle on cherche l'état avec la plus petite distance
	* @param distance les distances parcourues pour arriver à l'etat
	* @return l'état avec la plus petite distance appartenant à la liste
	*/
    public State argmin(List<State> open,Map<State,Integer> distance){
		State state = new State();
        Integer tmp = Integer.MAX_VALUE;
        for(State si : distance.keySet()){
			if(open.contains(si)){
				int i = distance.get(si);
				if(i<tmp){
					tmp = i;
					state = si;
				}
            }
        }
        return state;
	}
	
	/**
	* Méthode qui permet de savoir si l'état satisfait l'un des états finaux.
	* @param state l'état dont on veut savoir si il satisfait l'un des états finaux
	* @return true si l'état satisfait l'un des états finaux, false sinon
	*/
	public boolean satisfiesFinal(State state){
		boolean good = true;
        for(State s : this.finalState){
            if(!state.satisfies(s)){
                good=false;
            }
        }
        return good;
	}
}
