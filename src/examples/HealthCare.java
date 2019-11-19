package examples;
import java.util.*;
import representations.*;
import ppc.*;
import planning.*;

/**
 * Classe qui est un builder pour bfs/dfs/dijkstra/astar
 */
public class HealthCare{
	private Set<String> oui_non = new HashSet<String>(){{add("oui");add("non");}};
	private Set<String> temperature = new HashSet<String>(){{add("haute");add("moyenne");add("basse");add("none");}};

	private Set<String> non = new HashSet<String>(){{add("non");}};
	private Set<String> oui = new HashSet<String>(){{add("oui");}};
	private Set<String> moyenne = new HashSet<String>(){{add("moyenne");}};
	private Set<String> haute = new HashSet<String>(){{add("haute");}};
	private Set<String> basse = new HashSet<String>(){{add("basse");}};
	private Set<String> none = new HashSet<String>(){{add("none");}};

	private Variable angine = new Variable("Angine" , oui_non);
	private Variable grippe = new Variable("Grippe" , oui_non);
	private Variable variole = new Variable("Variole", oui_non);
	private Variable peste = new Variable("Peste", oui_non);
	private Variable fievre = new Variable("Fievre", temperature);
	private Variable boutons = new Variable("Boutons", temperature);
	private Variable toux = new Variable("Toux", temperature);

	private List<Variable> vars=new ArrayList<Variable>(){{add(fievre);add(boutons);add(toux);}};
	private Set<Variable> allvars =new HashSet<Variable>(){{add(fievre);add(boutons);add(toux);add(angine);add(grippe);add(variole);add(peste);}};
	private Set<Variable> vars2=new HashSet<Variable>(){{add(angine);add(grippe);add(variole);add(peste);}};
	private List<Set<String>> resdomains=new ArrayList<Set<String>>(){{add(moyenne);add(haute);add(basse);add(none);}};

	private Set<RestrictedDomain> premisse;
	private Set<RestrictedDomain> conclusion;
	private Action siropBoutons;
	private Action siropToux;
	private Action siropFievre;
	private Set<Rule> regles;
	private Action heal;

	private Set<Action> medicaments;
  /**
  * Constructeur de HealthCare qui crée toutes les actions : les sirops, les medicaments expérimentaux, et l'action heal.
  * @param n le nombre de médicaments expérimentaux à créer
  */
	public HealthCare(int n){
		this.siropBoutons=new Action(createSyrup(this.boutons),"siropBoutons",2);
		this.siropToux=new Action(createSyrup(this.toux),"siropToux",2);
		this.siropFievre=new Action(createSyrup(this.fievre),"siropFievre",2);

		this.medicaments=new HashSet<Action>();
		for(int i=0;i<n;i++){
			Set<Rule> rules=new HashSet<Rule>();
			int rd=((int) (Math.random()*2));
			this.initPreEff();
			this.premisse.add(new RestrictedDomain(this.fievre, temperature));
			this.conclusion.add(new RestrictedDomain(vars.get(rd),none));
			rules.add(new Rule(premisse, conclusion));
			Variable v0=vars.get(rd);
			vars.remove(rd);
			for(int j=0;j<2;j++){
				this.initPreEff();
				this.premisse.add(new RestrictedDomain(this.fievre, temperature));
				this.conclusion.add(new RestrictedDomain(vars.get(j),resdomains.get((int)(Math.random()*3))));
				rules.add(new Rule(premisse, conclusion));
			}
			vars.add(v0);
			this.medicaments.add(new Action(rules,"medicament "+i,1));
		}
		this.init();
		this.premisse.add(new RestrictedDomain(this.fievre,none));
		this.premisse.add(new RestrictedDomain(this.toux,none));
		this.premisse.add(new RestrictedDomain(this.boutons,none));
		this.conclusion.add(new RestrictedDomain(this.angine,non));
		this.regles.add(new Rule(premisse, conclusion));
		this.conclusion = new HashSet<RestrictedDomain>();
		this.conclusion.add(new RestrictedDomain(this.grippe,non));
		this.regles.add(new Rule(premisse, conclusion));
		this.conclusion = new HashSet<RestrictedDomain>();
		this.conclusion.add(new RestrictedDomain(this.variole,non));
		this.regles.add(new Rule(premisse, conclusion));
		this.conclusion = new HashSet<RestrictedDomain>();
		this.conclusion.add(new RestrictedDomain(this.peste,non));
		this.regles.add(new Rule(premisse, conclusion));
		this.heal=new Action(this.regles,"heal",1);
	}

	/**
	* Méthode qui Permet d'initialiser les règles, premisse et conclusion
	*/
	private void init(){
		this.regles=new HashSet<Rule>();
		this.initPreEff();
	}

	/**
	* Méthode qui Permet de réinitialiser la premisse et la conclusion
	*/
	private void initPreEff(){
		this.premisse = new HashSet<RestrictedDomain>();
		this.conclusion = new HashSet<RestrictedDomain>();
	}

	/**
	* Méthode qui Permet d'ajouter a la premisse
	* @param vars la variable représentant le symptôme
	* @param choix le domaine choisi pour le symptôme
	*/
	private void ajoutPre(Variable vars, Set<String> choix){
		this.premisse.add(new RestrictedDomain(vars, choix));
	}
	
	/**
	* Méthode qui Permet d'ajouter a la conclusion
	* @param vars la variable représentant le symptôme
	* @param choix le domaine choisi pour le symptôme
	*/
	private void ajoutEff(Variable vars, Set<String> choix){
		this.conclusion.add(new RestrictedDomain(vars, choix));
	}
	
	/**
	* Méthode qui crée un sirop agissant sur un symptôme.
	* @param vars la variable représentant le symptôme
	* @return les regles permettant de créer le sirop
	*/
	public Set<Rule> createSyrup(Variable vars){
		this.init();
		this.ajoutPre(vars, this.haute);
		this.ajoutEff(vars, this.moyenne);
		this.regles.add(new Rule(premisse, conclusion));
		this.initPreEff();
		this.ajoutPre(vars, this.moyenne);
		this.ajoutEff(vars, this.basse);
		this.regles.add(new Rule(premisse, conclusion));
		this.initPreEff();
		this.ajoutPre(vars, this.basse);
		this.ajoutEff(vars, this.none);
		this.regles.add(new Rule(premisse, conclusion));
		return this.regles;
	}
	
	/**
	* Méthode qui crée un état initial aléatoirement.
	* @return l'état initial créé
	*/
	public State createInitialState(){
		Map<Variable,String> mapstate=new HashMap<Variable,String>();
		for(Variable v:this.allvars){
			List<String> lvar=new ArrayList<String>(v.getDomain());
			mapstate.put(v,(lvar.get((int) (Math.random()*v.getDomain().size()))));
		}
		return new State(mapstate);
	}
  
	/**
	* Méthode qui crée l'état final (aucun symptômes et aucune maladie).
	* @return l'état final
	*/
	public State createFinalState(){
		Map<Variable,String> mapstate=new HashMap<Variable,String>();
		for(Variable v:vars){
			mapstate.put(v,"none");
		}
		for(Variable v:vars2){
			mapstate.put(v,"non");
		}
		return new State(mapstate);
	}
  
	/**
	* Méthode qui crée les états finaux et l'état final puis affiche les résultats du dfs, du bfs, du dijkstra et du A* et le nombre de noeuds explorés par chaque algorithme.
	*/
	public void test(){
		State a=this.createInitialState();
		State b=this.createFinalState();
		PlanningProblemWithCost p=new PlanningProblemWithCost(a,new HashSet<State>(){{add(b);}},new HashSet<Action>(this.medicaments){{add(siropToux);add(siropFievre);add(siropBoutons);add(heal);}});
		System.out.println("Etat initial : " + a);
		System.out.println("Etat final   : " + b + System.lineSeparator());
		for(Action m:this.medicaments){
			String res="";
			for(Rule r:m.getRules()){
				res+=r.getConclusion();
			}
			System.out.println(m+" : true => "+res);
		}
		System.out.println();
		System.out.println("dfs      :" + p.dfs(a,new Stack<Action>(),new HashSet<State>()));
		System.out.println("Nombre de noeuds explorés :" + p.getCount() + System.lineSeparator());
		p.reset();
		System.out.println("bfs      :" + p.bfs());
		System.out.println("Nombre de noeuds explorés :" + p.getCount() + System.lineSeparator());
		p.reset();
		System.out.println("dijkstra :" + p.dijkstra());
		System.out.println("Nombre de noeuds explorés :" + p.getCount() + System.lineSeparator());
		p.reset();
		System.out.println("a*       :" + p.ASTAR());
		System.out.println("Nombre de noeuds explorés :" + p.getCount());
		p.reset();
	}
}
