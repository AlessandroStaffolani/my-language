package ast;

import visitor.ExpAssignVisitor;
import visitor.ExpVisitor;

public class LIdentExp extends Exp {

    private String name;

    public LIdentExp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(ExpVisitor v) {
        ((ExpAssignVisitor)v).visit(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
