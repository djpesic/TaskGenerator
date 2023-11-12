package abstractions;

import connectors.ConnectorFactory;
import connectors.LoopConnector;
import java.util.LinkedList;
import java.util.List;
import syntaxelements.nonterminals.StatementSequence;

public class ProgramSequence extends ProgramComposite {

    public ProgramSequence() {

    }

    private List<ProgramSegment> sequence = new LinkedList<>();
    private StatementSequence syntaxTree;

    @Override
    public String toString() {
        return syntaxTree.toString();
    }

    @Override
    public String toCString() {
        return syntaxTree.toCString();
    }

    @Override
    public void combineWith(ProgramSegment program) {
        sequence.add(program);
        StatementSequence tree = program.getSyntaxTree();
        LoopConnector connector = ConnectorFactory.getSequencerConnector();
        syntaxTree = connector.connect(syntaxTree, tree);

    }

    @Override
    public StatementSequence getSyntaxTree() {
        return syntaxTree;
    }

}
