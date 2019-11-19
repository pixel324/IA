package representations;

import java.util.*;

import representations.RestrictedDomain;

/**
 * Classe qui représente la disjunction c'est à dire les ||.
 * Cette classe hérite de Rule car toute disjonction est forcément une règle.
 */
public class Disjunction extends Rule{
    
    /**
     * Constructeur de la classe.
     * On surcharge le constructeur de la classe mère,
     * pour pouvoir utiliser seulement le deuxième paramètre.
     * On va donc faire hériter les attributs de rule.
     * @param conclusion Conclusion qui est le Set de RestrictedDomain.
     */
    public Disjunction(Set<RestrictedDomain> conclusion){
        super(new HashSet<RestrictedDomain>(), conclusion);
    }

}
