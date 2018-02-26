package ast;

import visitor.ExpAssignVisitor;
import visitor.ExpVisitor;

public class RIdentExp extends Exp {

    private String name;
    private long value;

    public RIdentExp(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public long getVal() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(ExpVisitor v) {
        ((ExpAssignVisitor)v).visit(this);
    }
}
