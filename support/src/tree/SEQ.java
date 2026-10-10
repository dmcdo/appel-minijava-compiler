package tree;

import java.util.List;

public class SEQ extends Stm {
    public final Stm left, right;

    public SEQ(Stm left, Stm right) {
        this.left = left;
        this.right = right;
    }

    /* The canonicalizer eliminates SEQ before it would ask for these. */
    public ExpList kids() { throw new Error("kids() not applicable to SEQ"); }
    public Stm build(ExpList kids) { throw new Error("build() not applicable to SEQ"); }
    public List<Exp> subcomponents() { throw new Error("subcomponents() not applicable to SEQ"); }
    public Stm create(List<Exp> kids) { throw new Error("create() not applicable to SEQ"); }
}
