package ast;

import visitor.ExpVisitor;

public class PlusExp extends OpExp {

    public PlusExp(Exp l, Exp r) {
        super(l, r);
    }

    public String myOp() {
        return "+";
    }

    public void accept(ExpVisitor v) {
        v.visit(this);
    }
}
