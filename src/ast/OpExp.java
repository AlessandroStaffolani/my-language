package ast;

abstract public class OpExp extends Exp {

    private Exp left;
    private Exp right;

    protected OpExp(Exp l, Exp r) {
        left = l;
        right = r;
    }

    public abstract String myOp();

    @Override
    public String toString() {
        return left.toString() + myOp() + right.toString();
    }

    public Exp left() {
        return  left;
    }

    public Exp right() {
        return right;
    }
}
