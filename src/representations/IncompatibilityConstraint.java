package representations;

import java.util.*;

import representations.RestrictedDomain;

/**
 * Classe qui représente les contraintes d'incompatibilités, c'est à dire les et.
 * Cette classe hérite de Rule.
 */
public class IncompatibilityConstraint extends Rule{

    /**
     * Constructeur de la classe.
     * On surcharge le constructeur de la classe mère,
     * pour pouvoir utiliser seulement le premier paramètre.
     * @param premise Premisse qui est le Set de RestrictedDomain.
     */
    public IncompatibilityConstraint(Set<RestrictedDomain> premise){
        super(premise, new HashSet<RestrictedDomain>());
    }

}
