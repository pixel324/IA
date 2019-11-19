package planning;

import java.util.*;
import representations.*;
/*Résultats : Pour le dfs , avec notre exemple, il explore en moyenne 25 noeuds alors que le bfs en explore 5-6 en moyenne.
Cela est donc cohérent avec notre implémentation et les principes de ces deux algorithmes*/
/**
 * Classe qui représente le problème sans cout.
 */
public class PlanningProblem{

	/**
     * Attribut initialState, il contient l'état initial.
     */
	protected State initialState;

	/**
     * Attribut finalState, il contient l'état à obtenir à la fin.
     */
	protected Set<State> finalState;

	/**
     * Attribut actions, il contient toutes les actions possibles.
     */
	protected Set<Action> actions;

	/**
     * Attribut count, il contient le nombre de passages.
     */
	protected int count=0;

	/**
     * Constructeur de la classe qui prend 3 paramètres en entrée.
     * @param initialState l'état initial.
     * @param finalState le ou les états à obtenir à la fin.
     * @param actions toutes les actions possibles.
     */
	public PlanningProblem(State initialState,Set<State> finalState,Set<Action> actions){
		this.initialState=initialState;
		this.finalState=finalState;
		this.actions=actions;
	}

	/**
     * Méthode qui permet de connaitre le coût de l'algorythme lancé
     * @return coût de l'algorythme.
     */
	public int getCount(){
		return this.count;
	}

	/**
     * Méthode qui permet de réinitialiser le coût d'un algorythme
     */
	public void reset(){
		this.count = 0;
	}
	/**
     * Méthode dfs qui permet de faire un balayage en profondeur
     * @param state l'état que nous testons
     * @param plan la liste des actions réalisées
     * @param closed la liste des états réalisés
     * @return la liste des actions réalisées (chemin vers la solution)
     */
	public Stack<Action> dfs(State state,Stack<Action> plan,Set<State> closed){
		for(State s:this.finalState){
			if(state.satisfies(s)){
				return plan;
			}
		}
		for(Action a:this.actions){
			this.count++;
			if(a.isApplicable(state)){
				State next=a.apply(state);
				if(!closed.contains(next)){
					plan.push(a);
					closed.add(next);
					Stack<Action> subplan=dfs(next,plan,closed);
					if(subplan!=null){
						return subplan;
					}
					else{
						plan.pop();
					}
				}
			}
		}
		return null;
	}

	/**
     * Méthode bfs qui permet de faire un balayage en largeur
     * @return la liste des actions réalisées (chemin vers la solution)
     */
	public Stack<Action> bfs(){
		Map<State,State> father = new HashMap<State,State>();
		Map<State,Action> plan = new HashMap<State,Action>();
		ArrayList<State> closed = new ArrayList<State>();
		ArrayList<State> open = new ArrayList<State>();
		open.add(this.initialState);
		father.put(this.initialState,null);
		while(open.size()!=0){
			this.count++;
			State state = open.get(open.size()-1);
			open.remove(open.size()-1);
			closed.add(state);
			for(Action e : this.actions){
				if(e.isApplicable(state)){
					State next = e.apply(state);
					if(!closed.contains(next) && !open.contains(next)){
						father.put(next,state);
						plan.put(next,e);
						for(State s : this.finalState){
							if(next.satisfies(s)){
								return get_bfs_plan(father,plan,s);
							}
						}
						open.add(next);
					}
				}
			}
		}
		return null;
	}

	/**
     * Méthode qui permet de mettre en forme le chemin vers la solution pour le bfs.
     * @param father les liens entre les états testés
     * @param plan liens entre les états et l'action réalisée
     * @param goal l'état final
     * @return la liste des actions réalisées (chemin vers la solution)
     */
	public Stack<Action> get_bfs_plan(Map<State,State> father,Map<State,Action> plan,State goal){
		Stack<Action> stack=new Stack<Action>();
		while(goal != null){
			stack.push(plan.get(goal));
			goal = father.get(goal);
		}
		Stack<Action> stack2 = new Stack<Action>();
		while(!stack.empty()){
			if(stack.peek()!=null){
				stack2.push(stack.pop());
			}else{
				stack.pop();
			}
		}
		return stack2;
	}

    /**
     * Surcharge de la méthode toString(),
     * permettant d'afficher comme il faut le problème.
     * @return Un String représentant l'état initial.
     */
	public String toString(){
		return "initialstate "+this.initialState.toString();
	}
}
