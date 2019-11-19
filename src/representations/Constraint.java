package representations;

import java.util.*;

/**
 * Interface Constraint possédant deux méthodes.
 */
public interface Constraint{

    /**
     * Méthode qui permet de retourner la portée de la Variable.
     * @return un Set de Variable qui est cette portée. Il contiendra donc toutes les variables.
     */
    public Set<Variable> getScope();

    /**
     * Méthode de type boolean, qui regarde si un patient satisfait une règle.
     * @param map Map de Variable (Un patient), pour voir si il satisfait la contrainte.
     * @return boolean , Un boolean qui renvoie si il satisfait ou non la contrainte.
     */
    public boolean isSatisfiedBy(Map<Variable, String> map);

    /**
     * Méthode qui renvoie un booléen si elle a filtré
     * @param domain Liste des domaines déjà utilisés.
     * @param mapVerif Liste à vérifier.
     * @return Un boolean.
     */
    public boolean filter(Set<RestrictedDomain> domain, Map<Variable, String> mapVerif);

}
