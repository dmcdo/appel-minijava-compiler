package canon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tree.*;

/**
 * Orders basic blocks into traces (Appel, section 8.3) so that every CJUMP
 * is immediately followed by its false label, and drops a JUMP whose target
 * comes right after it.
 */
public final class TraceSchedule {
    private final List<Stm> stms = new ArrayList<>();
    /* The blocks not yet placed, by label. */
    private final Map<NameOfLabel, List<Stm>> table = new HashMap<>();

    private TraceSchedule(BasicBlocks b) {
        for (List<Stm> block : b.blocks)
            table.put(((LABEL)block.get(0)).label, block);

        for (List<Stm> block : b.blocks)
            if (table.containsKey(((LABEL)block.get(0)).label))
                trace(block);
    }

    private void trace(List<Stm> block) {
        while (block != null) {
            table.remove(((LABEL)block.get(0)).label);

            int last = block.size() - 1;
            stms.addAll(block.subList(0, last));
            Stm s = block.get(last);
            block = null;

            if (s instanceof JUMP) {
                JUMP j = (JUMP)s;
                List<Stm> target = j.targets.size() == 1 ? table.get(j.targets.get(0)) : null;

                if (target != null)
                    block = target;
                else
                    stms.add(s);
            }
            else if (s instanceof CJUMP) {
                CJUMP j = (CJUMP)s;
                List<Stm> t = table.get(j.iftrue);
                List<Stm> f = table.get(j.iffalse);

                if (f != null) {
                    stms.add(s);
                    block = f;
                }
                else if (t != null) {
                    stms.add(new CJUMP(CJUMP.notRel(j.relop), j.left, j.right, j.iffalse, j.iftrue));
                    block = t;
                }
                else {
                    NameOfLabel ff = NameOfLabel.generateLabel("BB");
                    stms.add(new CJUMP(j.relop, j.left, j.right, j.iftrue, ff));
                    stms.add(new LABEL(ff));
                    stms.add(new JUMP(j.iffalse));
                }
            }
            else {
                throw new Error("Bad basic block in TraceSchedule");
            }
        }
    }

    public static List<Stm> trace(BasicBlocks b) {
        return new TraceSchedule(b).stms;
    }
}
