package assem;

import java.util.Arrays;
import java.util.List;

import tree.NameOfLabel;
import tree.NameOfTemp;

/** A move between two temporaries, which a register allocator may coalesce away. */
public class MoveInstruction extends Instruction {
    public final NameOfTemp dst;
    public final NameOfTemp src;

    public MoveInstruction(String assem, NameOfTemp dst, NameOfTemp src) {
        super(assem);
        this.dst = dst;
        this.src = src;
    }

    public List<NameOfTemp> use() { return Arrays.asList(src); }
    public List<NameOfTemp> def() { return Arrays.asList(dst); }
    public List<NameOfLabel> jumps() { return null; }
}
