package canon;

import java.util.ArrayList;
import java.util.List;

import tree.*;

/**
 * Splits a list of canonical statements into basic blocks (Appel, section
 * 8.2).  Every block starts with a LABEL and ends with a JUMP or CJUMP.
 */
public final class BasicBlocks {
    public final List<List<Stm>> blocks = new ArrayList<>();

    /**
     * Where control goes after the last statement.  No LABEL is ever made
     * for it: the jump to it marks the end of the code and has to be dropped
     * or replaced by whoever emits the epilogue.
     */
    public final NameOfLabel done = new NameOfLabel("BB", "nowhere");

    private List<Stm> current = null;

    private BasicBlocks(List<Stm> stms) {
        for (Stm s : stms) {
            if (s instanceof LABEL) {
                // A label ends the block before it, which falls through.
                if (current != null)
                    close(new JUMP(((LABEL)s).label));
                open(s);
            }
            else {
                if (current == null)
                    open(new LABEL(NameOfLabel.generateLabel("BB")));

                if (s instanceof JUMP || s instanceof CJUMP)
                    close(s);
                else
                    current.add(s);
            }
        }

        if (current != null)
            close(new JUMP(done));
    }

    private void open(Stm label) {
        current = new ArrayList<>();
        current.add(label);
        blocks.add(current);
    }

    private void close(Stm jump) {
        current.add(jump);
        current = null;
    }

    public static BasicBlocks makeBlocks(List<Stm> stms) {
        return new BasicBlocks(stms);
    }
}
