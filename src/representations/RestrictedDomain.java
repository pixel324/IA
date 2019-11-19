package representations;

import java.util.*;

/**
 * Cette classe représente les domaines restreints,
 * composés d'une Variable ainsi que d'un sousDomaine (symptome).
 */
public class RestrictedDomain{

    /**
     * Attribut variable, Une Variable représentant le nom du symptome.
     */
    public Variable variable;

    /**
     * Attribut subDomain, Un Set de String, qui contient l'état du symptome.
     */
    public Set<String> subDomain;

    /**
     * Constructeur de la classe qui prend deux paramètres en entrée.
     * @param variable Une Variable qui va représenter le nom du symptome.
     * @param subDomain Un Set de String qui va être l'état du symptome.
     */
    public RestrictedDomain(Variable variable, Set<String> subDomain){
        this.variable = variable;
        this.subDomain = subDomain;
    }

    /**
     * Méthode qui permet de retourner la Variable.
     * @return Une Variable qui sera le nom du symptome.
     */
    public Variable getVariable(){
        return this.variable;
    }

    /**
     * Méthode qui permet de retourner le sous-domaine.
     * @return Un Set de String qui sera l'état du symptome.
     */
    public Set<String> getSubDomain(){
        return this.subDomain;
    }
    /**
     * Surcharge de la méthode toString(),
     * permettant d'afficher comme il faut le RestrictedDomain.
     * @return Un String représentant la chaîne de caractères qui sera renvoyée.
     */
    @Override
    public String toString(){
        String res = "";
        for(String s : this.subDomain){
            res += s + " ";
        }
        return this.variable+", "+res;
    }

}
