package assem;

import java.util.List;

import tree.NameOfLabel;
import tree.NameOfTemp;

/** A point in the program that can be jumped to. */
public class LabelInstruction extends Instruction {
    public final NameOfLabel label;

    public LabelInstruction(NameOfLabel label) {
        this(label + ":", label);
    }

    public LabelInstruction(String assem, NameOfLabel label) {
        super(assem);
        this.label = label;
    }

    public List<NameOfTemp> use() { return null; }
    public List<NameOfTemp> def() { return null; }
    public List<NameOfLabel> jumps() { return null; }
}
