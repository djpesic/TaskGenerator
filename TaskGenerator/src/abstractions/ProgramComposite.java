package abstractions;

public abstract class ProgramComposite extends ProgramSegment {

    @Override
    public abstract String toString();

    @Override
    public abstract void combineWith(ProgramSegment program);

}
