package tree;

import java.util.List;

/** A statement of the intermediate representation.  See Exp. */
public abstract class Stm {
    /** A statement that does nothing; the canonicalizer removes it. */
    public static final Stm NOOP = new EVAL(new CONST(0));

    public abstract ExpList kids();
    public abstract Stm build(ExpList kids);

    public abstract List<Exp> subcomponents();
    public abstract Stm create(List<Exp> kids);

    @Override
    public String toString() { return TreePrint.toString(this); }
}
