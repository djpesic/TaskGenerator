/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import abstractions.IfSegment;
import abstractions.ProgramSegment;
import abstractions.ProgramSequence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import programSegment.*;

/**
 *
 * @author djordje Finds the deepest element in the syntax tree and its direct
 * ancestor. Considered elements: simple loops and whole expressions. The search
 * does not go inside expressions.
 */
public class TwoMostDeepFinder extends XmlProcessorAdapter {

    private ConstType foundConst;
    private SimpleLoop foundSimpleLoop;
    private ProgramType foundConstAncestor;
    private LoopType foundSimpleLoopAncestor;
    private ProgramType root;
    int level = 0;
    int maxLevel = 0;

    List<Integer> levels = new ArrayList<>();

    // true if only one maxLevel (global maximum)
    public boolean maxLevelSingle() {
        Collections.sort(levels);
        if (levels.size() > 1) {
            int last = levels.get(levels.size() - 1);
            int subLast = levels.get(levels.size() - 2);
            if (last == subLast) {
                return false;
            }
        }
        return true;
    }

    public TwoMostDeepFinder(ProgramType root) {
        this.root = root;
    }

    public LoopType getFoundSimpleLoopAncestor() {
        return foundSimpleLoopAncestor;
    }

    public ConstType getFoundConst() {
        return foundConst;
    }

    public SimpleLoop getFoundSimpleLoop() {
        return foundSimpleLoop;
    }

    public ProgramType getFoundConstAncestor() {
        return foundConstAncestor;
    }

    @Override
    public ProgramSegment processProgramSegment(ProgramType programType) {
        if (programType.getConst() != null) {
            if (level >= maxLevel && level > 0) {
                foundConst = programType.getConst();
                foundConstAncestor = programType;
                foundSimpleLoop = null;
                foundSimpleLoopAncestor = null;
                maxLevel = level;
            }
        } else if (programType.getIf() != null) {
            level++;
            processIf(programType.getIf());
            level--;
        } else if (programType.getLoop() != null) {
            LoopType lType = programType.getLoop();
            processLoopType(lType);
        }
        return null;
    }

    @Override
    public IfSegment processIf(IfType ifType) {
        ProgramType thenBranch = ifType.getThenBranch();
        ProgramType elseBranch = ifType.getElseBranch();
        if (thenBranch != null) {
            level++;
            processProgramSegment(thenBranch);
            level--;
        }
        if (elseBranch != null) {
            level++;
            processProgramSegment(elseBranch);
            level--;
        }
        return null;
    }

    @Override
    public ProgramSegment processNested(Nested nested) {
        LoopType outer = nested.getOuter();
        ProgramType inner = nested.getInner();
        if (outer != null) {
            level++;
            processLoopType(outer);
            level--;
        }
        if (inner != null) {
            level++;
            processProgramSegment(inner);
            level--;
        }
        return null;
    }

    @Override
    public ProgramSequence processSequence(Sequence sequence) {
        List<ProgramType> seq = sequence.getProgram();
        seq.forEach((progType) -> {
            level++;
            processProgramSegment(progType);
            level--;
        });
        return null;
    }

    @Override
    public void startProcessing() {
        processProgramSegment(root);
    }

    @Override
    public LoopType processLoopType(LoopType loopType) {

        if (loopType.getNested() != null) {
            level++;
            processNested(loopType.getNested());
            level--;
        } else if (loopType.getSequence() != null) {
            level++;
            processSequence(loopType.getSequence());
            level--;
        } else if (loopType.getSimpleLoop() != null) {
            if (level >= maxLevel) {
                foundSimpleLoop = loopType.getSimpleLoop();
                foundSimpleLoopAncestor = loopType;
                foundConst = null;
                foundConstAncestor = null;
                maxLevel = level;
                levels.add(maxLevel);
            }
        }
        return null;
    }
}
