package abstractions;

import connectors.ConnectorFactory;
import connectors.LoopConnector;
import syntaxelements.nonterminals.StatementSequence;

public class LoopNested extends ProgramComposite {

    public LoopNested() {
    }

    private ProgramSegment nestedLoop = null;
    private Loop mainLoop = null;
    private StatementSequence syntaxTree;

    @Override
    public String toString() {
        return syntaxTree.toString();
    }

    @Override
    public String toCString() {
        return syntaxTree.toCString();
    }

    /**
     * @param program
     */
    @Override
    public void combineWith(ProgramSegment program) {
        if (program instanceof Loop) {
            if (mainLoop == null) {
                mainLoop = (Loop) program;
                syntaxTree = mainLoop.getSyntaxTree();
            } else if (mainLoop != null && nestedLoop == null) {
                nestedLoop = program;
                LoopConnector connector = ConnectorFactory.getNesterConnector();
                syntaxTree = connector.connect(syntaxTree, nestedLoop.getSyntaxTree());
            } else {
                System.out.println("Cannot nest!!!");
            }
        } else {
            if (mainLoop == null) {
                System.out.println("Cannot nest in const expression!!!");
            } else if (mainLoop != null && nestedLoop == null) {
                nestedLoop = program;
                LoopConnector connector = ConnectorFactory.getNesterConnector();
                syntaxTree = connector.connect(syntaxTree, nestedLoop.getSyntaxTree());
            }

        }
    }

    @Override
    public StatementSequence getSyntaxTree() {
        return syntaxTree;
    }

}
