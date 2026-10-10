package tree;

import java.util.List;

/**
 * An expression of the intermediate representation.
 *
 * kids()/build() and subcomponents()/create() are two views (linked list and
 * java.util.List) of the same thing: the subexpressions that are evaluated,
 * and a way to rebuild the node around new ones.
 */
public abstract class Exp {
    public abstract ExpList kids();
    public abstract Exp build(ExpList kids);

    public abstract List<Exp> subcomponents();
    public abstract Exp create(List<Exp> kids);

    @Override
    public String toString() { return TreePrint.toString(this); }
}
