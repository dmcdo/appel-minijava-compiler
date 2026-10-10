package tree;

import java.util.Collections;
import java.util.List;

public class TEMP extends Exp {
    public final NameOfTemp temp;

    public TEMP(NameOfTemp temp) {
        this.temp = temp;
    }

    public TEMP(String name) {
        this(new NameOfTemp(name));
    }

    /** A fresh temporary. */
    public static TEMP generateTEMP() {
        return new TEMP(NameOfTemp.generateTemp());
    }

    public ExpList kids() { return null; }
    public Exp build(ExpList kids) { return this; }
    public List<Exp> subcomponents() { return Collections.emptyList(); }
    public Exp create(List<Exp> kids) { return this; }
}
