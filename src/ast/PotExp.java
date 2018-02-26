package ast;

import visitor.ExpVisitor;

public class PotExp extends OpExp {

    public PotExp(Exp l, Exp r) {
        super(l, r);
    }

    public String myOp() {
        return "^";
    }

    public void accept(ExpVisitor v) {
        v.visit(this);
    }
}
