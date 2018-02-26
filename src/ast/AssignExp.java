package ast;

import visitor.ExpAssignVisitor;
import visitor.ExpVisitor;

public class AssignExp extends OpExp {


    public AssignExp(Exp l, Exp r) {
        super(l, r);
    }

    public String myOp() {
        return "=";
    }

    public void accept(ExpVisitor v) {
        ((ExpAssignVisitor)v).visit(this);
    }
}
