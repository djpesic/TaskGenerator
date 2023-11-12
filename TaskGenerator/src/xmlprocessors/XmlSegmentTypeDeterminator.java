/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import java.util.List;
import programSegment.LoopType;
import programSegment.ProgramType;
import programSegment.Segment;
import programSegment.Sequence;

/**
 *
 * @author djordje
 */
public class XmlSegmentTypeDeterminator {

    //determines if segment is one expression or the sequence or more than one expression
    public static boolean isExpressionSequence(ProgramType progType) {
        if (progType.getConst() != null) {
            return true;
        }
        LoopType loopType = progType.getLoop();
        if (loopType != null) {
            Sequence seq = loopType.getSequence();
            if (seq != null) {
                List<ProgramType> progTypes = seq.getProgram();
                for (ProgramType type : progTypes) {
                    if (!(isExpressionSequence(type))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    // checks if the segment is one loop with expression sequence inside
    public static boolean isSimpleLoop(ProgramType progType) {
        if (progType.getConst() != null) {
            return false;
        }

        return containsOnlyExpressions(progType);
    }

    public static boolean isComplexLoop(ProgramType progType) {
        if (progType.getConst() != null) {
            return false;
        }

        return !containsOnlyExpressions(progType);
    }

    // Checks if the segment is one or more expressions
    public static boolean containsOnlyExpressions(ProgramType progType) {
        if (progType.getConst() != null) {
            return true;
        }
        if (progType.getIf() != null) {
            ProgramType thenBranch = progType.getIf().getThenBranch();
            ProgramType elseBranch = progType.getIf().getElseBranch();
            boolean retThen = false, retElse = false;
            if (thenBranch != null) {
                retThen = containsOnlyExpressions(thenBranch);
            }
            if (elseBranch != null) {
                retElse = containsOnlyExpressions(elseBranch);
            }
            return (retThen && retElse);
        }
        if (progType.getLoop() != null) {
            if (progType.getLoop().getSequence() != null) {
                for (ProgramType prog : progType.getLoop().getSequence().getProgram()) {
                    if (!(containsOnlyExpressions(prog))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    // checks if the segment is a single empty while loop
    public static boolean isEmptyWhileRepeatLoop(ProgramType programType) {
        if (programType.getConst() != null) {
            return false;
        }
        if (programType.getIf() != null) {
            return false;
        }
        if (programType.getLoop() != null) {
            if (programType.getLoop().getNested() != null) {
                return false;
            }
            if (programType.getLoop().getSequence() != null) {
                return false;
            }
            if (programType.getLoop().getSimpleLoop() != null) {
                if (programType.getLoop().getSimpleLoop().getWhile() != null) {
                    return true;
                }
                if (programType.getLoop().getSimpleLoop().getRepeat() != null) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
