package examples;
import java.util.*;
import representations.*;
import ppc.*;
import planning.*;

/**
 * Cette classe représente un lanceur pour les algorithmes de balayage
 */
public class PlanningMain{
	
	/**
     * Méthode qui permet de lancer la factory des algorithmes de balayage
     * @param args les arguments au lancement
     */
	public static void main(String [] args){
		HealthCare h=new HealthCare(3);
		h.test();
	}
}
