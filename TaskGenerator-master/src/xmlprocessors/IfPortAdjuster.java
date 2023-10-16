/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import java.util.Set;
import programSegment.ProgramType;
import programSegment.Segment;

/**
 *
 * @author djordje
 */
public class IfPortAdjuster extends PortAdjuster {

    private Segment ifTemplate;

    public IfPortAdjuster(Segment seg1, Segment seg2, Segment ifTemplate) {
        super(seg1, seg2);
        this.ifTemplate = ifTemplate;
    }

    @Override
    public void startProcessing() {
        //if and else branch have to be empty, in order to adjusting works
        ProgramType elseBranch = ifTemplate.getProgram().getIf().getElseBranch();
        ProgramType thenBranch = ifTemplate.getProgram().getIf().getThenBranch();
        ifTemplate.getProgram().getIf().setThenBranch(null);
        ifTemplate.getProgram().getIf().setElseBranch(null);
        Set<String> names = collectNames(ifTemplate);
        adaptInheritance(names, first);
        adaptInheritance(names, second);
        ifTemplate.getProgram().getIf().setThenBranch(thenBranch);
        ifTemplate.getProgram().getIf().setElseBranch(elseBranch);
    }

}
