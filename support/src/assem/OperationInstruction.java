package assem;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tree.NameOfLabel;
import tree.NameOfTemp;

/** Any instruction other than a label or a register-to-register move. */
public class OperationInstruction extends Instruction {
    private final List<NameOfTemp> dst;
    private final List<NameOfTemp> src;
    private final List<NameOfLabel> jump;

    public OperationInstruction(String assem, List<NameOfTemp> dst, List<NameOfTemp> src, List<NameOfLabel> jump) {
        super(assem);
        this.dst = dst;
        this.src = src;
        this.jump = jump;
    }

    public OperationInstruction(String assem, List<NameOfTemp> dst, List<NameOfTemp> src) {
        this(assem, dst, src, null);
    }

    public OperationInstruction(String assem) {
        this(assem, Collections.<NameOfTemp>emptyList(), Collections.<NameOfTemp>emptyList(), null);
    }

    public OperationInstruction(String assem, NameOfTemp dst) {
        this(assem, Arrays.asList(dst), Collections.<NameOfTemp>emptyList(), null);
    }

    public OperationInstruction(String assem, NameOfTemp dst, NameOfTemp src) {
        this(assem, Arrays.asList(dst), Arrays.asList(src), null);
    }

    public OperationInstruction(String assem, NameOfTemp dst, NameOfTemp src1, NameOfTemp src2) {
        this(assem, Arrays.asList(dst), Arrays.asList(src1, src2), null);
    }

    public List<NameOfTemp> use() { return src; }
    public List<NameOfTemp> def() { return dst; }
    public List<NameOfLabel> jumps() { return jump; }
}
