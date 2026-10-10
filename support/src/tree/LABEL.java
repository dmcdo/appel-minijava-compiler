package tree;

import java.util.Collections;
import java.util.List;

public class LABEL extends Stm {
    public final NameOfLabel label;

    public LABEL(NameOfLabel label) {
        this.label = label;
    }

    public LABEL(String... name) {
        this(new NameOfLabel(name));
    }

    public ExpList kids() { return null; }
    public Stm build(ExpList kids) { return this; }
    public List<Exp> subcomponents() { return Collections.emptyList(); }
    public Stm create(List<Exp> kids) { return this; }
}
