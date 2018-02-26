package ast;

import visitor.ExpVisitor;

public class NumExp extends Exp {

    private long val;

    public NumExp(long v) {
        val = v;
    }

    @Override
    public String toString() {
        return "" + val;
    }

    public long getVal() {
        return val;
    }

    public void accept(ExpVisitor v) {
        v.visit(this);
    }
}
