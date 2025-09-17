package liste;

/**
 * Représente un nœud d'une liste simplement chaînée.
 * Chaque nœud contient une valeur et une référence vers le nœud suivant.
 */

public class Noeud {
    private Object element;
    private Noeud suivant;


    /**
     * Construit un nouveau nœud avec une valeur et un lien vers le nœud suivant.
     *
     * @param e        valeur stockée dans ce nœud
     * @param suivant  référence vers le nœud suivant (peut être {@code null})
     */
    public Noeud(int e, Noeud suivant) {
        element = e;
        this.suivant = suivant;
    }

    /**
     * Retourne la valeur contenue dans ce nœud.
     *
     * @return l'élément stocké
     */

    public Object getElement() {
        return element;
    }


    /**
     * Modifie la valeur stockée dans ce nœud.
     *
     * @param element la nouvelle valeur à stocker
     */

    public void setElement(Object element) {
        this.element = element;
    }


    /**
     * Retourne le nœud suivant dans la liste.
     *
     * @return le nœud suivant, ou {@code null} s'il n'y en a pas
     */
    public Noeud getSuivant() {
        return suivant;
    }

    /**
     * Met à jour la référence vers le nœud suivant.
     *
     * @param suivant le nouveau nœud suivant (peut être {@code null})
     */
    public void setSuivant(Noeud suivant) {
        this.suivant = suivant;
    }

    /**
     * Retourne une représentation textuelle de ce nœud.
     *
     * @return une chaîne de la forme {@code Noeud(valeur)}
     */
    public String toString() {
        return "Noeud(" + element + ")";
    }
}
