package tree;

import java.util.Collections;
import java.util.List;

public class NAME extends Exp {
    public final NameOfLabel label;

    public NAME(NameOfLabel label) {
        this.label = label;
    }

    public NAME(String... name) {
        this(new NameOfLabel(name));
    }

    public ExpList kids() { return null; }
    public Exp build(ExpList kids) { return this; }
    public List<Exp> subcomponents() { return Collections.emptyList(); }
    public Exp create(List<Exp> kids) { return this; }
}
