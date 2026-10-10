package tree;

import java.util.List;

public class ESEQ extends Exp {
    public final Stm stm;
    public final Exp exp;

    public ESEQ(Stm stm, Exp exp) {
        this.stm = stm;
        this.exp = exp;
    }

    /* The canonicalizer eliminates ESEQ before it would ask for these. */
    public ExpList kids() { throw new Error("kids() not applicable to ESEQ"); }
    public Exp build(ExpList kids) { throw new Error("build() not applicable to ESEQ"); }
    public List<Exp> subcomponents() { throw new Error("subcomponents() not applicable to ESEQ"); }
    public Exp create(List<Exp> kids) { throw new Error("create() not applicable to ESEQ"); }
}
