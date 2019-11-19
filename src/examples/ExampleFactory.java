package examples;
import java.util.*;

import representations.*;
import ppc.*;

/**
 * Cette classe représente une Factory permettant d'instancier des règles
 * avec des patiens.
 */
public class ExampleFactory{

    /**
     * Création des différents attributs.
     */

    /**
     * Attribut oui, pour le domaine oui
     */
    private Set<String> oui = new HashSet<String>(){{add("oui");}};

    /**
     * Attribut non, pour le domaine non
     */
    private Set<String> non = new HashSet<String>(){{add("non");}};

    /**
     * Attribut moyenne, pour le domaine moyenne
     */
    private Set<String> moyenne = new HashSet<String>(){{add("moyenne");}};

    /**
     * Attribut haute, pour le domaine haute
     */
    private Set<String> haute = new HashSet<String>(){{add("haute");}};

    /**
     * Attribut basse, pour le domaine basse
     */
    private Set<String> basse = new HashSet<String>(){{add("basse");}};

    /**
     * Attribut oui_non, pour le domaine oui_non
     */
    private Set<String> oui_non = new HashSet<String>(){{add("oui");add("non");}};

    /**
     * Attribut temperature, pour le domaine temperature
     */
    private Set<String> temperature = new HashSet<String>(){{add("haute");add("moyenne");add("basse");}};

    /**
     * Attribut intensite, pour le domaine intensite
     */
    private Set<String> intensite = new HashSet<String>(){{add("haute");add("moyenne");}};

	private Variable angine = new Variable("Angine" , oui_non);
	private Variable toux = new Variable("Toux" , oui_non);
	private Variable allergie = new Variable("Allergie", intensite);
	private Variable boutons = new Variable("Boutons", oui_non);
	private Variable hypothermie = new Variable("Hypothermie", oui_non);
	private Variable oedeme = new Variable("Oedème", oui_non);
	private Variable sirop = new Variable("Prise sirop", oui_non);
	private Variable fatigue = new Variable("Fatigue", oui_non);
	private Variable grippe = new Variable("Grippe", oui_non);
	private Variable virus = new Variable("Virus", oui_non);
	private Variable vaccination = new Variable("Vaccination", oui_non);
    private Variable fievre = new Variable("Fievre", temperature);

    /**
     * Création des Set pour la prémisse et la conclusion.
     */
    private Set<RestrictedDomain> premisse;
    private Set<RestrictedDomain> conclusion;

    /**
     * Création d'un Set pour le patient.
     */
    private Map<Variable, String> patient;

    /**
     * Initialisation d'une règle à vide.
     */
    private Rule rule = null;

    /**
     * Initialisation d'une disjunction à vide.
     */
    private Disjunction disjunction = null;

    /**
     * Initialisation d'une incompatibilityConstraint à vide.
     */
    private IncompatibilityConstraint incompatibilityConstraint = null;

	private String choice = "";

    /**
     * Constructeur de la classe.
     */
    public ExampleFactory(){

    }

    public ExampleFactory(String choice){
        this.choice = choice;
    }
        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 1 :
         * L'angine provoque une fièvre haute ou moyenne.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_1(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition = new RestrictedDomain(this.angine, oui);
            this.premisse.add(condition);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.fievre, intensite);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 2 :
         * L'angine provoque la toux.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_2(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition = new RestrictedDomain(this.angine, oui);
            this.premisse.add(condition);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.toux, oui);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 3 :
         * Une grippe, en l'absence de vaccination, provoque une fièvre haute.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_3(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.grippe, oui);
            RestrictedDomain condition2 = new RestrictedDomain(this.vaccination, non);
            this.premisse.add(condition1);
            this.premisse.add(condition2);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.fievre, haute);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 4 :
         * Une grippe, en l'absence de vaccination, provoque de la fatigue.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_4(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.grippe, oui);
            RestrictedDomain condition2 = new RestrictedDomain(this.vaccination, non);
            this.premisse.add(condition1);
            this.premisse.add(condition2);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.fatigue, oui);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 5 :
         * L'angine peut ou non être provoquée par un virus.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_5(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.angine, oui);
            this.premisse.add(condition1);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.virus, oui_non);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 6 :
         * Une grippe est toujours provoquée par un virus.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_6(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.grippe, oui);
            this.premisse.add(condition1);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.virus, oui);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 7 :
         * La prise de sirop avec une allergie moyenne au sucre provoque des boutons.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_7(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.sirop, oui);
            RestrictedDomain condition2 = new RestrictedDomain(this.allergie, moyenne);
            this.premisse.add(condition1);
            this.premisse.add(condition2);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.boutons, oui);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 8 :
         * La prise de sirop avec une allergie haute au sucre provoque un œdème.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_8(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.sirop, oui);
            RestrictedDomain condition2 = new RestrictedDomain(this.allergie, haute);
            this.premisse.add(condition1);
            this.premisse.add(condition2);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.oedeme, oui);

            this.conclusion.add(conclusion1);

            this.rule = new Rule(premisse, conclusion);
        }

        /**
         * Cette méthode va créer la prémisse et la conclusion pour la règle 9 :
         * On ne peut pas à la fois avoir une fièvre haute ou moyenne et être en hypothermie.
         * Elle va aussi créer un patient qui remplie la règle.
         */
        public void selectRule_9(){

            this.premisse = new HashSet<RestrictedDomain>();
            this.conclusion = new HashSet<RestrictedDomain>();

            RestrictedDomain condition1 = new RestrictedDomain(this.fievre, this.intensite);
            this.premisse.add(condition1);

            RestrictedDomain conclusion1 = new RestrictedDomain(this.hypothermie, this.oui);

            this.premisse.add(conclusion1);

            this.rule = new IncompatibilityConstraint(premisse);

        }

        public void creationPatient(){
			// Création du patient
            this.patient = new HashMap<Variable, String>();
            this.patient.put(this.fievre, "haute");
            this.patient.put(this.hypothermie, "oui");
		}

        /**
         * Méthode qui permet de vérifier si un patient satisfait une règle.
         * @return Une String affichant Bravo en cas de réussite, ou sinon Erreur.
         */
        public String Satisfied(){
            if(this.rule.isSatisfiedBy(this.patient) == true){
                return ("Bravo");
            }
            else{
                return ("Erreur");
            }
        }

        /**
         * Méthode qui permet de vérifier si un patient satisfait une règle.
         * @return Une String affichant Bravo en cas de réussite, ou sinon Erreur.
         */
        public String SatisfiedDisjunction(){
            if(this.disjunction.isSatisfiedBy(this.patient) == true){
                return ("Bravo");
            }
            else{
                return ("Erreur");
            }
        }

        /**
         * Méthode qui permet de vérifier si un patient satisfait une règle.
         * @return Une String affichant Bravo en cas de réussite, ou sinon Erreur.
         */
        public String SatisfiedIncompatibilityConstraint(){
            if(this.incompatibilityConstraint.isSatisfiedBy(this.patient) == true){
                return ("Bravo");
            }
            else{
                return ("Erreur");
            }
        }

        /**
         * Méthode permettant de retourner à l'écran la règle choisie,
         * ainsi que le patient.
         */
        public void getDisplay(){
            System.out.println("La regle : \n" + rule);
            System.out.println("Le patient : " +  patient);
        }

        /**
         * Méthode factory qui permet de trouver les solutions au problème.
         */
        public void backTracking(){

            Set<Rule> constraint = new HashSet<Rule>();

            // Mise en place des contraintes.
			selectRule_1();
			constraint.add(this.rule);
			selectRule_2();
            constraint.add(this.rule);
            /*
            selectRule_3();
            constraint.add(this.rule);
            selectRule_4();
            constraint.add(this.rule);
            selectRule_5();
            constraint.add(this.rule);
            selectRule_6();
            constraint.add(this.rule);
            selectRule_7();
            constraint.add(this.rule);
            selectRule_8();
            constraint.add(this.rule);
            selectRule_9();
            constraint.add(this.rule);
            */

            // Création du backtracking
			Backtracking backtrack = new Backtracking(constraint);
			Set<RestrictedDomain> listVar = backtrack.getVariables();
			Map<Variable,String> cas = new HashMap<Variable,String>();
			Set<Map<Variable,String>> list = new HashSet<Map<Variable,String>>();
			list.add(cas);
			Set<Map<Variable,String>> answer = backtrack.backtrack(list,listVar,this.choice);
			if(answer != null){
				int CountAnswer =1;
				for(Map i : answer){
					System.out.println("solution numéro: "+ CountAnswer + " = " + i);
                    CountAnswer++;
				}
			}else{
				System.out.println("Pas de solution possible");
			}
		}

    }
