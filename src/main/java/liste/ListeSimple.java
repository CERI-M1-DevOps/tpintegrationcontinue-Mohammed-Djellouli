package liste;

/**
 * Liste simplement chaînée d'objets (implantation minimale).
 * Les insertions se font en tête et la taille est maintenue.
 */
public class ListeSimple {
    private long size;
    Noeud tete;

    /**
     * Retourne le nombre d'éléments actuellement présents dans la liste.
     *
     * @return la taille de la liste (nombre de nœuds), toujours ≥ 0
     */
    public long getSize() {
        return size;
    }


    /**
     * Ajoute un nouvel élément en tête de liste.
     * Incrémente la taille de la liste.
     *
     * @param element valeur entière à insérer comme nouvel élément (nouvelle tête)
     */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /**
     * Remplace la première occurrence trouvée de {@code element} par {@code nouvelleValeur}.
     * Si l'élément n'est pas présent, la liste reste inchangée.
     *
     * @param element        valeur recherchée (première occurrence ciblée)
     * @param nouvelleValeur valeur de remplacement appliquée à cette occurrence
     * @return rien (effet de bord : modification éventuelle d'un nœud de la liste)
     */
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /**
     * Remplace toutes les occurrences de {@code element} par {@code nouvelleValeur}.
     * Si aucune occurrence n'est trouvée, la liste reste inchangée.
     *
     * @param element        valeur recherchée
     * @param nouvelleValeur valeur de remplacement appliquée à chaque occurrence
     */
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /**
     * Retourne une représentation textuelle de la liste.
     * Exemple : {@code ListeSimple(Noeud(3), Noeud(2), Noeud(1))}.
     *
     * @return chaîne représentant la liste dans l'ordre du chaînage depuis la tête
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Supprime la première occurrence de {@code element} si elle existe.
     * Met à jour la tête si nécessaire et décrémente la taille.
     *
     * @param element valeur à supprimer (première occurrence uniquement)
     */
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /**
     * Supprime toutes les occurrences de {@code element} dans la liste.
     * Met à jour la tête et décrémente la taille pour chaque suppression.
     *
     * @param element valeur à supprimer (toutes les occurrences)
     */
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /**
     * Implémentation récursive de {@link #supprimeTous(int)}.
     * Reconstruit la liste sans les nœuds dont l'élément vaut {@code element}.
     *
     * @param element valeur à supprimer
     * @param tete    nœud courant (sous-liste à traiter)
     * @return la sous-liste traitée (tête potentiellement modifiée)
     */
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /**
     * Retourne l'avant-dernier nœud de la liste si elle contient au moins deux éléments.
     *
     * @return l'avant-dernier nœud, ou {@code null} si la liste a moins de deux éléments
     */
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /**
     * Inverse la liste en place (le dernier devient la nouvelle tête).
     */
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /**
     * Retourne le nœud précédent du nœud {@code r} au sein de cette liste.
     * Hypothèse : {@code r} appartient à la liste et la liste n'est pas vide.
     *
     * @param r nœud dont on cherche le précédent
     * @return le nœud précédent de {@code r}
     * @throws NullPointerException si {@code r} est {@code null}
     */
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }


    /**
     * Échange en place deux nœuds {@code r1} et {@code r2} de la liste.
     * Gère les cas où un des nœuds est la tête. Ne fait rien si {@code r1 == r2}.
     *
     * @param r1 premier nœud à échanger
     * @param r2 second nœud à échanger
     */

    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }

}