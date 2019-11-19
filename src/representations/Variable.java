package representations;

import java.util.*;

/**
 * Cette classe représente une Variable qui se compose,
 * d'un nom de type String et d'un domaine,
 * qui est composé d'un Set de String.
 */
public class Variable{

    /**
     * Attribut name, Le nom de la Variable.
     */
    public String name;

    /**
     * Attribut domain, Le domaine composé d'un Set de String.
     */
    public Set<String> domain;

    /**
     * Constructeur de la classe.
     * @param name Nom de la Variable.
     * @param domain Set de String pour le domaine.
     */
    public Variable(String name, Set<String> domain){
        this.name = name;
        this.domain = domain;
    }
    
    @Override
    public boolean equals(Object compare){
        if(compare instanceof Variable)
        {
            Variable variable = (Variable) compare;
            return this.name.equals(variable.getName());
        }
        return false;
    }
    
    /**
     * Getteur permettant de retourner le nom de la variable.
     * @return Le String correspondant au nom.
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Getteur qui permet de retourner le domaine de la variable.
     * @return Un Set de String qui correspond au domaine.
     */
    public Set<String> getDomain(){
        return this.domain;
    }
    
    /**
     * Setteur permettant de retourner le domaine de la variable.
     * @param element l'élément à ajouter au sous-domaine
     */
    public void setDomain(String element){
        this.domain.add(element);
    }

    /**
     * Redéfinition de la méthode ToString
     * permettant d'afficher la variable.
     * Effectivement cela est identique au getteur de this.name . Mais cela nous permet tout de même d'avoir un affichage avec un syso(variable v)
     * @return Un String représentant la chaîne de caractères qui sera renvoyée.
     */
    @Override
    public String toString(){
        return this.name;
    }
}
